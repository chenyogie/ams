<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true">
                <el-form-item class="block">
                    <el-date-picker
                            v-model="beginDate"
                            type="datetime"
                            placeholder="选择开始日期时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item class="block">
                    <el-date-picker
                            v-model="endDate"
                            type="datetime"
                            placeholder="选择结束日期时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-select  v-model="fileAbnormalState.centralVal" style="width: 116px;" size="medium" clearable placeholder="请选择类型">
                        <el-option
                                v-for="item in fileAbnormalState"
                                :key="item.centralVal"
                                :label="item.name"
                                :value="item.centralVal">
                            <span style="float: left">{{ item.name }}</span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="档案编号" v-model="keyWords"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="add">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="problematicDocs" :v-loading="listLoading" highlight-current-row
                  :default-sort = "{prop: 'date', order: 'descending'}" style="width: 100%;">
            <!--多选框
            <el-table-column type="selection" width="55">
            </el-table-column>-->
            <!--索引值-->
            <el-table-column type="index" label="编号" width="100">
            </el-table-column>

            <el-table-column prop="doc.num" label="档案编号" width="160" sortable>
            </el-table-column>

            <el-table-column prop="dictionarydetail.name" label="类型" width="100" sortable>
                <template scope="scope">
                    <span v-if="scope.row.dictionarydetail.id==1" style="color:red">已损坏</span>
                    <span v-else-if="scope.row.dictionarydetail.id==2" style="color: indigo">已丢失</span>
                    <span v-else-if="scope.row.dictionarydetail.id==3" style="color: green">已找回</span>
                </template>
            </el-table-column>

            <el-table-column prop="user.username"  label="操作用户"  width="120" sortable>
            </el-table-column>

            <el-table-column prop="accidentdate" label="操作日期" sortable>
            </el-table-column>

            <el-table-column prop="remark" label="备注" width="200">
            </el-table-column>

            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button v-if="scope.row.dictionarydetail.id == 2" type="success" size="small" @click="edit(scope.row)">找回</el-button>
                    <el-button v-else size="small" disabled>找回</el-button>
                    <el-button type="danger" @click="del(scope.row)" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <br/>
        <!--分页条-->
        <el-pagination
                background
                layout="prev, pager, next"
                @current-change="currentChange"
                :page-size="6"
                :total="total"
                style="float: right"
        >
        </el-pagination>
        <el-dialog :visible.sync="formVisible" width="900px" :close-on-click-modal="false" :before-close="diaClose">
            <el-dialog width="30%" title="损坏丢失登记" :visible.sync="innerVisible" append-to-body>
                <el-form :model="problematicDoc" size="medium" ref="problematicDoc">
                    <el-form-item label="登记类型：" prop="dictionarydetail.name">
                        <el-select  v-model="docStatesData.centralVal" style="width: 220px;" size="medium" clearable placeholder="请选择类型">
                            <el-option
                                    v-for="item in docStatesData"
                                    :key="item.centralVal"
                                    :label="item.name"
                                    :value="item.centralVal">
                                <span style="float: left">{{ item.name }}</span>
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="备注信息：" prop="remark">
                        <el-input
                                type="textarea"
                                :rows="5"
                                placeholder="请输入内容"
                                v-model="problematicDoc.remark">
                        </el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="innerVisible = false">取消</el-button>
                    <el-button type="primary" @click="submit">提交</el-button>
                </div>
            </el-dialog>
            <!--工具条-->
            <el-col :span="24" style="padding-bottom: 0px; padding-left: 20px">
                <el-form :inline="true">
                    <el-select v-model="docStates.centralVal" style="width: 116px;" clearable placeholder="档案状态">
                        <el-option
                                v-for="item in docStates"
                                :key="item.centralVal"
                                :label="item.name"
                                :value="item.centralVal">
                            <span style="float: left">{{ item.name}}</span>
                        </el-option>
                    </el-select>
                    <el-form-item>
                        <el-input placeholder="档案编号" v-model="innerKeyWords"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="searchInner">查询</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="">批量登记</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-table :data="records" :v-loading="listLoading" highlight-current-row
                      :default-sort = "{prop: 'date', order: 'descending'}" style="width: 100%;">
                <!--多选框
                <el-table-column type="selection" width="55">
                </el-table-column>-->
                <!--索引值-->
                <el-table-column type="index" label="编号" width="80">
                </el-table-column>

                <el-table-column prop="num" label="档案编号" width="130" sortable>
                </el-table-column>

                <el-table-column prop="headline" label="档案标题"  sortable>
                </el-table-column>

                <el-table-column prop="expirationdate"  label="过期时间" width="160" sortable>
                </el-table-column>

                <el-table-column prop="f_status"  label="档案状态"  width="120" sortable>
                    <template scope="scope">
                        <span v-if="scope.row.f_status==0" style="color:green">使用中</span>
                        <span v-else-if="scope.row.f_status==-1" style="color: gray">已删除</span>
                        <span v-else-if="scope.row.f_status==-2" style="color: black">已销毁</span>
                        <span v-else-if="scope.row.f_status==-3" style="color: red">已损坏</span>
                        <span v-else-if="scope.row.f_status==-4" style="color: indigo">已丢失</span>
                    </template>
                </el-table-column>

                <el-table-column label="操作" width="100">
                    <template scope="scope">
                        <el-button v-if="scope.row.f_status==0" type="danger" @click="register(scope.row)" size="small">登记</el-button>
                        <el-button v-else size="small" disabled>登记</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <br/>
            <!--分页条-->
            <el-pagination
                    background
                    layout="prev, pager, next"
                    @current-change="currentInnerChange"
                    :page-size="5"
                    :total="innerTotal"
                    style="float: right"
            >
            </el-pagination>
            <br/>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        data(){
            return{
                beginDate: null,
                endDate: null,
                keyWords:"",
                innerKeyWords:"",
                total:0,
                innerTotal:0,
                currentPage:1,
                innerCurrentPage:1,
                docStatesData:[],
                docStates:[
                    {
                        centralVal:0,
                        name:"使用中"
                    },
                    {
                        centralVal:-1,
                        name:"已删除"
                    },
                    {
                        centralVal:-2,
                        name:"已销毁"
                    },
                    {
                        centralVal:-3,
                        name:"已损坏"
                    },
                    {
                        centralVal:-4,
                        name:"已丢失"
                    }
                ],
                fileAbnormalState:[
                    {
                        centralVal:-3,
                        name:"已损坏"
                    },
                    {
                        centralVal:-4,
                        name:"已丢失"
                    },
                    {
                        centralVal:1,
                        name:"已找回"
                    }
                ],
                problematicDocs:[],
                records:[],
                formVisible:false,
                innerVisible:false,
                listLoading:false,
                problematicDoc:{
                    id:null,
                    doc:{

                    },
                    dictionarydetail:{

                    },
                    user:{

                    },
                    accidentdate:null,
                    remark:''
                }
            }
        },
        methods:{
            diaClose(done){
                this.docStates={};
                this.innerCurrentPage=1;
                done();
            },
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.getProblematicDocs();
            },
            currentInnerChange(innerCurrentPage){
                this.innerCurrentPage = innerCurrentPage;
                this.getRecords();
            },
            getProblematicDocs(){
                let param = {"currentPage":this.currentPage,"keyWords":this.keyWords
                            ,"beginDate": this.beginDate,"endDate": this.endDate,
                    "status":this.fileAbnormalState.centralVal,"pageSize": 6};
                this.listLoading = true;
                this.$http.post("/problematicDoc",param).then(result=>{
                    this.problematicDocs = result.data.rows;
                    this.total=result.data.total;
                    this.listLoading = false;
                })
            },
            getRecords(){
                let param = {"currentPage":this.innerCurrentPage,"pageSize": 5};
                this.listLoading = true;
                this.$http.patch("/record/page",param).then(result=>{
                    this.records = result.data.rows;
                    this.innerTotal=result.data.total;
                    this.listLoading = false;
                })
            },
            search(){
                this.getProblematicDocs();
            },
            searchInner(){
                let param = {"recStates":this.docStates.centralVal,"keyWords":this.innerKeyWords};
                this.listLoading = true;
                this.$http.patch("/record/page",param).then(result=>{
                    this.records = result.data.rows;
                    this.innerTotal=result.data.total;
                    this.listLoading = false;
                })
            },
            del(row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    var id = row.id;
                    this.listLoading = true;
                    this.$http.delete("/problematicDoc/"+id)
                        .then(result=>{
                            this.listLoading = false;
                            //操作后的提示
                            if(result.data.success){
                                this.$message({
                                    message: '删除成功',
                                    type: 'success'
                                });
                            }else{
                                this.$message({
                                    message: result.data.message,
                                    type: 'error'
                                });
                            }
                            //刷新数据
                            this.getProblematicDocs();
                        })
                });
            },
            edit(row){
                this.$prompt('请输入备注', {
                    confirmButtonText: '确定',
                    inputType:"textarea",
                    cancelButtonText: '取消',
                }).then(({ value }) => {
                    this.$confirm('确认找回吗？', '警告', {type:"warning"}).then(() => {
                        //拷贝对象的值到新对象
                        this.problematicDoc = row;
                        this.problematicDoc.user = {"id":1,"username":"航仔"}
                        this.problematicDoc.dictionarydetail.id = 3;
                        this.problematicDoc.doc.f_status = 0;
                        this.problematicDoc.remark = value;
                        //拷贝对象的值到新对象
                        let para = Object.assign({},this.problematicDoc);
                        console.debug(para);
                        this.$http.put("/problematicDoc",para).then((res) => {
                            this.$message({
                                message: '操作成功!',
                                type: 'success'
                            });
                            //重置表单
                            this.$refs['problematicDoc'].resetFields();
                            //关闭对话框
                            this.formVisible = false;
                            //刷新数据
                            this.getProblematicDocs();
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消找回'
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消找回'
                    });
                });
            },
            add(){
                //打开dialog
                this.docStates=[{
                    centralVal:0,
                    name:"使用中"
                },
                    {
                        centralVal:-1,
                        name:"已删除"
                    },
                    {
                        centralVal:-2,
                        name:"已销毁"
                    },
                    {
                        centralVal:-3,
                        name:"已损坏"
                    },
                    {
                        centralVal:-4,
                        name:"已丢失"
                    }],
                this.innerKeyWords="",
                this.formVisible =true;
                this.getRecords();
            },register(row){
                this.docStatesData=[
                    {
                        centralVal:-3,
                        name:"已损坏"
                    },
                    {
                        centralVal:-4,
                        name:"已丢失"
                    }
                ]
                let para = Object.assign({},row);
                //打开dialog
                this.problematicDoc.doc = para;
                this.problematicDoc.user = {"id":2,"username":"四夕"}
                this.innerVisible =true;
                     },
                submit(){
                    this.$confirm('确认登记吗？', '警告', {type:"warning"}).then(() => {
                        var docStatesId = 0;
                        if(this.docStatesData.centralVal==-3){
                            docStatesId = 1;
                        }else if(this.docStatesData.centralVal==-4){
                            docStatesId = 2;
                        }
                        this.problematicDoc.dictionarydetail.id = docStatesId;
                        this.problematicDoc.doc.f_status = this.docStatesData.centralVal;
                        //拷贝对象的值到新对象
                        let para = Object.assign({}, this.problematicDoc);
                        console.debug(para);
                        this.$http.put("/problematicDoc",para).then((res) => {
                            this.$message({
                                message: '操作成功!',
                                type: 'success'
                            });
                            //重置表单
                            this.$refs['problematicDoc'].resetFields();
                            //关闭对话框
                            this.innerVisible = false;
                            //刷新数据
                            this.getRecords();
                        });
                    })
                }
            },
        mounted() {
            this.getProblematicDocs();
        }
    }
</script>

<style scoped>

</style>