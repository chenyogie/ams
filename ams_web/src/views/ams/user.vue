<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true">
                <el-form-item>
                    <el-input placeholder="关键字"></el-input>
                </el-form-item>
                    <el-form-item>
                    <el-button type="primary">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="add">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>
        <!--列表v-loading="listLoading"-->
        <el-table :data="users" v-loading="listLoading" highlight-current-row  style="width: 100%;">
            <!--多选框-->
            <el-table-column type="selection" width="55">
            </el-table-column>
            <!--索引值,为什么不用id,id不序号-->
            <el-table-column type="index" width="60">
            </el-table-column>
            <!--其他都设置值,只有一个不设置值就自动适应了-->
            <el-table-column prop="username" label="名称">
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button size="small"  @click="edit(scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="del(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--添加或编辑对话框-->
        <el-dialog title="添加或修改" :visible="formVisible" :close-on-click-modal="false">
            <el-form :model="user" label-width="80px" :rules="formRules" ref="user">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="user.username" auto-complete="off"></el-input>

                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="formVisible = false">取消</el-button>
                <el-button type="primary" @click="save" >提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        data() {
            return {
                formVisible:false,//对话框默认不显示,只有点击添加或修改的时候显示
                listLoading:false,
                users:[],
                //初始值
                user:{
                    id:null,
                    username:'',
                },
                formRules: {
                    username: [
                        { required: true, message: '请输入名称!', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            save(){
                this.$refs.user.validate((valid) => {
                    //校验表单成功后才做一下操作
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            //拷贝后面对象的值到新对象,防止后面代码改动引起模型变化
                            let para = Object.assign({}, this.user);
                            //判断是否有id有就是修改,否则就是添加
                            var did = this.user.id;
                            if(did){ //修改
                                this.$http.post("/user/"+did,para).then((res) => {
                                    this.$message({
                                        message: '修改成功!',
                                        type: 'success'
                                    });
                                    //重置表单
                                    this.$refs['user'].resetFields();
                                    //关闭对话框
                                    this.formVisible = false;
                                    //刷新数据
                                    this.getusers();
                                });
                            }else{
                                this.$http.put("/user",para).then((res) => {
                                    this.$message({
                                        message: '添加成功!',
                                        type: 'success'
                                    });
                                    this.$refs['user'].resetFields();
                                    this.formVisible = false;
                                    this.getusers();
                                });
                            }

                        });
                    }
                })
            },
            edit(row){
                //回显
                this.user = row; //里面本来就有id,相当于回显了id
                //显示
                this.formVisible =true;
            },
            add(){
                //清空数据
                this.user={
                    id:null,
                    username:''

                }
                //打开dialog
                this.formVisible =true;
            },
            getusers(){
                //发送Ajax请求后台获取数据  axios
                let para = {};
                this.listLoading = true; //显示加载圈
                this.$http.post("/user",para)
                    .then((result)=>{
                        this.users = result.data.rows;
                        this.listLoading = false;  //关闭加载圈
                    });
            },
            del(row){
                console.log(row);
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    var id = row.id;
                    this.listLoading = true;
                    this.$http.delete("/user/"+id)
                        .then(result=>{
                            this.listLoading = false;
                            //做提示
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
                            this.getusers();
                        })
                });

            }
        },
        mounted() {
            this.getusers();
        }
    }

</script>

<style scoped>

</style>