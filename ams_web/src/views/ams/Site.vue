<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true">
                <el-form-item>
                    <el-input v-model="filters.keyWords" placeholder="关键字"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getsite">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click=" handleAdd">新增</el-button>
                </el-form-item>

            </el-form>
        </el-col>
        <!--列表-->
        <el-table
                :data="site"
                style="width: 100%"
                border
        >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column
                    prop="name"
                    label="档案地点名称"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="location"
                    label="具体地址">
            </el-table-column>
            <el-table-column
                    prop="linkman"
                    label="联系人">
            </el-table-column>
            <el-table-column
                    prop="remark"
                    label="备注">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="180">
                <template slot-scope="scope">
                    <el-button @click="handleEdit(scope.$index, scope.row)" type="success" size="small">修改</el-button>
                    <el-button type="danger" @click="del(scope.row)"size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="message">批量删除</el-button>
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
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
            <el-form-item label="档案名称">
                <el-input v-model="addForm.name" :min="0" :max="100"></el-input>
            </el-form-item>
            <el-form-item label="具体地址">
                <el-input v-model="addForm.location" :min="0" :max="100"></el-input>
            </el-form-item>
            <el-form-item label="联系人">
                <el-input v-model="addForm.linkman" :min="0" :max="100"></el-input>
            </el-form-item>
            <el-form-item label="备注">
                <el-input v-model="addForm.remark" :min="0" :max="100"></el-input>
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

                            this.$http.patch("/site/",para).then((res)=>{
                                console.log(res+"sss")
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getsite();
                            })
                        });
                    }
                });
            },
            //批量删除
            message:function () {
                alert("该功能暂不可用，请联系开发人员")
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
                            console.log(para)
                            this.$http.put("/site",para).then((res) => {
                                this.editLoading = false;
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getsite();
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
                    this.$http.delete("/site/"+uid).then((result) => {
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
                        this.getsite();
                    });
                }).catch(() => {

                });
            },
            handleCurrentChange(val){
                console.log(val)
                this.page = val;
                this.getsite();

            },
            handleClick(row) {
                console.log(row);
            },
            getsite(){
                let para = {
                    page: this.page,
                    keyWords: this.filters.keyWords
                };
                this.$http.patch("/site",para)
                    .then((res=>{
                        console.log(res.data)
                        this.total = res.data.total;
                        this.site = res.data.rows;
                    }))

            }
        },
        name: 'table',
        data () {
            return {
                filters:{
                    keyWords: ''
                },
                page: 1,
                total:100,
               site: [

                ],
                //新增界面数据
                addForm: {
                    locaton:'',
                    name: '',
                    linkman:'',
                    remark:''
                },
                editForm:{
                    locaton:'',
                    name: '',
                    linkman:'',
                    remark:''
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
            this.getsite();
        }
    }
</script>
<style scoped>
</style>
