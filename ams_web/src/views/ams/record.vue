<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true">
                <div class="block">
                    <el-date-picker
                            v-model="value2"
                            align="right"
                            type="date"
                            placeholder="请选择开始的日期"
                            :picker-options="pickerOptions">
                    </el-date-picker>
                    <el-date-picker
                            v-model="value1"
                            align="right"
                            type="date"
                            placeholder="请选择结束的日期"
                            :picker-options="pickerOptions">
                    </el-date-picker>
                <el-form-item>
                    <el-input placeholder="请输入查询的关键字" v-model=" filters.keyWords"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getrecord" >查询档案</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd" >档案录入</el-button>
                </el-form-item>
                </div>
            </el-form>
        </el-col>
        <!--列表-->
        <el-table
                :data="record"
                style="width: 100%"
                border
        >

            <el-table-column
                    prop="num" label="档案编号">
            </el-table-column>
            <el-table-column
                    prop="site.location"label="档案名称"width="180">
            </el-table-column>
            <el-table-column
                    prop="headline" label="档案标题">
            </el-table-column>
            <el-table-column
                    prop="createuser.name" label="建表人">
            </el-table-column>
            <el-table-column
                    prop="createDate" label="建表时间">
            </el-table-column>
            <el-table-column
                    prop="updateDate" label="修改表时间">
            </el-table-column>
            <el-table-column
                    prop="borrowstatus" label="档案借阅状态">
            </el-table-column>
            <el-table-column
                    prop="expirationdate" label="过期时间">
            </el-table-column>
            <el-table-column
                    prop="content" label="内容">
            </el-table-column>
            <el-table-column
                    prop="introduce" label="简介">
            </el-table-column>
            <el-table-column
                    prop="c_department.name" label="来文部门">
            </el-table-column>
            <el-table-column
                    prop="g_department.name" label="发文部门">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button type="danger" @click="del(scope.row)"size="small" >删除档案</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="removeMany">批量删除</el-button>
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="10" :total="total" style="float:right;">
            </el-pagination>
        </el-col>
        <!--x修改界面-->
        <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="具体地址">
                    <el-input v-model="editForm.location"></el-input>
                </el-form-item>
                <el-form-item label="联系人">
                    <el-input v-model="editForm.linkman"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="editForm.remark"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>
        <!--新增界面-->
        <el-dialog title="新增"  :visible.sync="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="40px" :rules="addFormRules" ref="addForm">
                <el-select v-model="addForm.site" placeholder="选择存档地点">
                    <el-option
                            v-for="item in addForm.sites"
                            :key="item.location"
                            :label="item.location"
                            :value="item">
                    </el-option>
                </el-select>
                <el-form-item label="档案编号" label-width="100px">
                    <el-input v-model="addForm.num" ></el-input>
                </el-form-item>
                <el-form-item label="档案标题" label-width="100px">
                    <el-input v-model="addForm.headline" ></el-input>
                </el-form-item>

                <el-form-item label="提前通知天数"label-width="100px">
                    <el-input v-model="addForm.advancedate" ></el-input>
                </el-form-item>
                <el-form-item label="内容" >
                    <el-input
                            type="textarea"
                            :rows="3"
                            placeholder="请输入内容"
                            v-model="addForm.content" > </el-input>
                </el-form-item>
                <el-form-item label="简介"label-width="100px" >
                    <el-input v-model="addForm.introduce" ype="textarea" :rows="3" placeholder="请输入简介"> </el-input>
                </el-form-item>
                <el-form label-width="100px">
                    <el-checkbox  label="100px"      v-model="addForm.oftenuse">是否常用</el-checkbox>
                </el-form>
                <el-form-item label="文件编号"label-width="100px">
                    <el-input v-model="addForm. f_num" ></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<script>
    export default {
        methods: {
            //显示新增界面
            handleAdd: function () {
                this.getsiteName(),
                this.addFormVisible = true;
                this.addForm = {
                    site:'',
                    sites:'',
                    //档案地点
                    //档案编号
                    num:'',
                    //档案标题
                    headline:'',
                    //建表人
                    createuser:{},
                    //建表时间
                    createDate:'',
                    //修改时间
                    updateDate:'',
                    //修改表人员
                    updateuser:{},
                    //借阅状态
                    borrowstatus:'',
                    //过期时间
                    expirationdate:'',
                    //是否提醒
                    expirationnotice:'',
                    //提前通知天数
                    advancedate:'',
                    //档案内容
                    content:'',
                    //简介
                    introduce:'',
                    //是否常用
                    oftenuse:'',
                    //文件编号
                    f_num:'',
                    //来文部门
                    c_department:{},
                    //发文部门
                    g_department:{},
                    //批准时间
                    ratify:'',
                    //鉴定时间
                    authenticate:'',
                    //产品批号
                    product_num:'',
                    //原料批号
                    raw_num:'',
                    //档案数量
                    f_count:'',
                    //档案页数
                    f_page:'',
                    //专利号
                    patent:'',
                    //年度检验人员
                    checkUser:{},
                    //档案状态
                    f_status:'',
                    //档案类型
                    type:{},
                    //发文人
                    ogm_user:{}
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            let para = Object.assign({}, this.editForm);
                            console.log(para)
                            this.$http.put("/record",para).then((res) => {
                                this.editLoading = false;
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getrecord();
                            });
                        });
                    }
                });
            },
            /*删除*/
            del(row){
                let uid = row.id;
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let param = { id: row.id };
                    this.$http.delete("/record/"+uid).then((result) => {
                        console.log(result)
                        if(result.data.success){
                            this.$message({
                                message: result.data.message,
                                type: 'success'
                            });
                        }else{
                            this.$message({
                                message: result.data.message,
                                type: 'error'
                            });
                        }
                        this.getrecord();
                    });
                }).catch(() => {

                });
            },
            handleCurrentChange(val){
                this.page = val;
                this.getrecord();

        },
            removeMany(){
              alert("功能未完善，暂未开放")
            },
            getsiteName(){
                let para = {
                    page: this.page,
                    keyWords: this.filters.keyWords
                };
                this.$http.patch("/site",para)
                    .then((res=>{
                        this.addForm.sites = res.data.rows;
                    }))

            },
            getrecord(){
                let para = {
                    page: this.page,
                   keyWords: this.filters.keyWords
                };
                this.$http.patch("/record",para)
                    .then((res=>{
                        console.log(res.data)
                        this.total = res.data.total;
                        this.record = res.data.rows;
                    }))

            },
            //获取员工列表
            getusers(){
                this.$http.patch("/user",{})
                    .then((res=>{
                        console.log(res.data)
                        this.addForm.createuser=res.data;
                    }))
            },
            //添加
            addSubmit(){
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            let para = Object.assign({}, this.addForm);
                            console.log(para)
                            this.$http.post("/record",para).then((res) => {
                                this.editLoading = false;
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.editFormVisible = false;
                                this.getrecord();
                            });
                        });
                    }
                });
            }
        },


        data () {
            return {
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                value1: '',
                value2: '',
                value: '',
                filters:{
                    keyword: ''
                },
                page: 1,
                total:100,
                record: [

                ],
                siteNme:[],
                //新增界面数据
                addForm: {
                    siteName:'',
                    //档案地点
                    site:[],
                    //档案编号
                    num:'',
                    //档案标题
                    headline:'',
                    //建表人
                    createuser:[],
                    //建表时间
                    createDate:'',
                    //修改时间
                    updateDate:'',
                    //修改表人员
                    updateuser:'',
                    //借阅状态
                    borrowstatus:'',
                    //过期时间
                    expirationdate:'',
                    //是否提醒
                    expirationnotice:'',
                    //提前通知天数
                    advancedate:'',
                    //档案内容
                    content:'',
                    //简介
                    introduce:'',
                    //是否常用
                    oftenuse:'',
                    //文件编号
                    f_num:'',
                    //来文部门
                    c_department:'',
                    //发文部门
                    g_department:'',
                    //批准时间
                    ratify:'',
                    //鉴定时间
                    authenticate:'',
                    //产品批号
                    product_num:'',
                    //原料批号
                    raw_num:'',
                    //档案数量
                    f_count:'',
                    //档案页数
                    f_page:'',
                    //专利号
                    patent:'',
                    //年度检验人员
                    checkUser:'',
                    //档案状态
                    f_status:'',
                    //档案类型
                    type:'',
                    //发文人
                    ogm_user:''
                },
                editForm:{
                    //档案地点
                    site:'',
                    //档案编号
                    num:'',
                    //档案标题
                    headline:'',
                    //建表人
                    createuser:'',
                    //建表时间
                    createDate:'',
                    //修改时间
                    updateDate:'',
                    //修改表人员
                    updateuser:'',
                    //借阅状态
                    borrowstatus:'',
                    //过期时间
                    expirationdate:'',
                    //是否提醒
                    expirationnotice:'',
                    //提前通知天数
                    advancedate:'',
                    //档案内容
                    content:'',
                    //简介
                    introduce:'',
                    //是否常用
                    oftenuse:'',
                    //文件编号
                    f_num:'',
                    //来文部门
                    c_department:'',
                    //发文部门
                    g_department:'',
                    //批准时间
                    ratify:'',
                    //鉴定时间
                    authenticate:'',
                    //产品批号
                    product_num:'',
                    //原料批号
                    raw_num:'',
                    //档案数量
                    f_count:'',
                    //档案页数
                    f_page:'',
                    //专利号
                    patent:'',
                    //年度检验人员
                    checkUser:'',
                    //档案状态
                    f_status:'',
                    //档案类型
                    type:'',
                    //发文人
                    ogm_user:''
                },
                editFormRules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ]
                },
                editLoading: false,
                addLoading: false,
                //校验规则
                addFormRules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    num:[{
                        required: true, message: '请输入档案编号', trigger: 'blur'
                    }]
                },
                //新增界面是否显示
                addFormVisible: false,
                //修改界面是否显示
                editFormVisible: false,
            }
        },
        mounted(){
            this.getusers()
            this.getrecord();
            this.getsiteName()
        }
    }
</script>
<style scoped>
    .el-dropdown-link {
        cursor: pointer;
        color: gold;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
</style>
