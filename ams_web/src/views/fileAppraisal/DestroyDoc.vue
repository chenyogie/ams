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
                    <el-input placeholder="档案编号" v-model="keyWords"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="add">登记</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="destroyDocs" :v-loading="listLoading" highlight-current-row
                  :default-sort = "{prop: 'date', order: 'descending'}" style="width: 100%;">
            <!--多选框
            <el-table-column type="selection" width="55">
            </el-table-column>-->
            <!--索引值-->
            <el-table-column type="index" label="销毁编号" width="120">
            </el-table-column>

            <el-table-column prop="doc.num" label="档案编号" width="160" sortable>
            </el-table-column>

            <el-table-column prop="user.username" label="操作用户" width="160" sortable>
            </el-table-column>

            <el-table-column prop="destroydate"  label="销毁日期" sortable>
            </el-table-column>

            <el-table-column prop="dictionarydetail.name" label="销毁原因" width="120">
            </el-table-column>

            <el-table-column prop="remark" label="备注" width="180">
            </el-table-column>

            <el-table-column label="操作" width="100">
                <template scope="scope">
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
            <el-dialog width="30%" title="销毁登记" :visible.sync="innerVisible" append-to-body>
                <el-form :model="destroyDoc" size="medium" ref="destroyDoc">
                    <el-form-item label="销毁原因：" prop="dictionarydetail.name">
                        <el-select  v-model="destroyReason.id" style="width: 220px;" size="medium" clearable placeholder="请选择原因">
                            <el-option
                                    v-for="item in destroyReason"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                <span style="float: left">{{ item.name }}</span>
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="备注信息：" prop="remark">
                        <el-input
                                type="textarea"
                                :rows="5"
                                placeholder="请输入内容"
                                v-model="destroyDoc.remark">
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
                            <span style="float: left">{{ item.name }}</span>
                        </el-option>
                    </el-select>
                    <el-form-item>
                        <el-input placeholder="档案编号" v-model="innerKeyWords"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="searchInner">查询</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="">批量销毁</el-button>
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
                        <el-button v-if="scope.row.f_status==0" type="danger" @click="destroy(scope.row)" size="small">销毁</el-button>
                        <el-button v-else size="small" disabled>销毁</el-button>
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
                destroyReason:[],
                destroyDocs:[],
                records:[],
                formVisible:false,
                innerVisible:false,
                listLoading:false,
                record:{

                },
                destroyDoc:{
                    id:null,
                    doc:{

                    },
                    user:{

                    },
                    destroydate:null,
                    dictionarydetail:{

                    },
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
                this.getDestroyDocs();
            },
            currentInnerChange(innerCurrentPage){
                this.innerCurrentPage = innerCurrentPage;
                this.getRecords();
            },
            getDestroyDocs(){
                let param = {"currentPage":this.currentPage,"keyWords":this.keyWords
                            ,"beginDate": this.beginDate,"endDate": this.endDate,"pageSize": 6};
                this.listLoading = true;
                this.$http.post("/destroyDoc",param).then(result=>{
                    this.destroyDocs = result.data.rows;
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
                this.getDestroyDocs();
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
                    this.$http.delete("/destroyDoc/"+id)
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
                            this.getDestroyDocs();
                        })
                });
            },
            add(){
                this.docStates=[
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
                ]
                this.innerKeyWords="",
                //打开dialog
                this.formVisible =true;
                this.getRecords();
            },destroy(row){
                this.destroyReason=[
                    {
                        id:4,
                        name:"已废弃"
                    },
                    {
                        id:5,
                        name:"已过期"
                    }
                ]
                let para = Object.assign({},row);
                this.destroyDoc.doc = para;
                this.destroyDoc.user = {"id":2,"username":"四夕"}
                //打开dialog
                this.innerVisible =true;
              },
                submit(){
                    this.$confirm('确认销毁吗？', '警告', {type:"warning"}).then(() => {
                        this.destroyDoc.dictionarydetail.id = this.destroyReason.id;
                        this.destroyDoc.doc.f_status = -2;
                        //拷贝对象的值到新对象
                        let para = Object.assign({}, this.destroyDoc);
                        this.$http.put("/destroyDoc",para).then((res) => {
                            this.$message({
                                message: '操作成功!',
                                type: 'success'
                            });
                            //重置表单
                            this.$refs['destroyDoc'].resetFields();
                            //关闭对话框
                            this.innerVisible = false;
                            //刷新数据
                            this.getRecords();
                        });
                    })
                }
            },
        mounted(){
            this.getDestroyDocs();
        }
    }
</script>

<style scoped>

</style>