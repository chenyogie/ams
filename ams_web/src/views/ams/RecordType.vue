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
                            placeholder="选择日期"
                            :picker-options="pickerOptions">
                    </el-date-picker>
                    <el-form-item>
                        <el-input  v-model="filters.keyWords" placeholder="关键字"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getrecordType" >查询</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click=" handleAdd">新增</el-button>
                    </el-form-item>

                </div>

            </el-form>
        </el-col>
        <!--列表-->
        <el-table
                :data="recordType"
                style="width: 100%"
                border
        >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column
                    prop="name"
                    label="档案名称"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="sncode"
                    label="档案编码">
            </el-table-column>

            <el-table-column
                    fixed="right"
                    label="操作"
                    width="180">
                <template slot-scope="scope">
                    <el-button @click="handleEdit(scope.$index, scope.row)" type="success" size="small" style="color: #f9c855" >修改</el-button>
                    <el-button type="danger" @click="del(scope.row)"size="small" >删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" >批量删除</el-button>
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="10" :total="total" style="float:right;">
            </el-pagination>
        </el-col>
        <!--x修改界面-->
        <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="编码">
                    <el-input v-model="editForm.sncode"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>
        <!--新增界面-->
        <el-dialog title="新增"  :visible.sync="addFormVisible" :close-on-click-modal="false">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
            <el-form-item label="档案名称">
                <el-input v-model="addForm.name" :min="0" :max="100"></el-input>
            </el-form-item>
            <el-form-item label="档案编码">
                <el-input v-model="addForm.sncode" :min="0" :max="100"></el-input>
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
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            let para = Object.assign({}, this.addForm);

                            this.$http.post("/recordType/",para).then((res)=>{
                                console.log(res+"sss")
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getrecordType();
                            })
                        });
                    }
                });
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    name: '',
                  sncode:''
                };
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            let para = Object.assign({}, this.editForm);
                            this.$http.put("/recordType",para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getrecordType();
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
                    this.$http.delete("/recordType/"+uid).then((result) => {
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
                        this.getrecordType();
                    });
                }).catch(() => {

                });
            },
            handleCurrentChange(val){
                console.log(val)
                this.page = val;
                this.getrecordType();

            },
            handleClick(row) {
                console.log(row);
            },
            getrecordType(){
                let para = {
                    page: this.page,
                    keyWords: this.filters.keyWords
                };
                this.$http.patch("/recordType",para)
                    .then((res=>{
                        this.total = res.data.total;
                        this.recordType= res.data.rows;
                    }))

            }
        },
        name: 'table',
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
                filters:{
                    keyWords: ''
                },
                sels:[],
                page: 1,
                total:100,
                recordType: [

                ],
                //新增界面数据
                addForm: {
                    name: '',
                    sncode:''
                },
                editForm:{
                    "name":"",
                    "sncode":""
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
                    ]
                },
                //新增界面是否显示
                addFormVisible: false,
                //修改界面是否显示
                editFormVisible: false,

            }

        },
        mounted(){
            this.getrecordType();
        }
    }
</script>
<style scoped>
</style>
