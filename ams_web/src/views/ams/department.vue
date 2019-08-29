<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :model="filters" :inline="true">
                <el-form-item>
                    <el-input v-model="filters.keyword" placeholder="关键字"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getDepartments" icon="el-icon-search">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="add">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>
        <!--列表v-loading="listLoading"-->
        <el-table :data="departments" v-loading="listLoading" highlight-current-row  style="width: 100%;">
            <!--多选框-->
            <el-table-column type="selection" width="55">
            </el-table-column>
            <!--索引值,为什么不用id,id不序号-->
            <!--<el-table-column type="index" width="60">
            </el-table-column>-->
            <!--其他都设置值,只有一个不设置值就自动适应了-->
            <el-table-column prop="id" label="id" width="40">
            </el-table-column>
            <el-table-column prop="identification" label="部门标识" width="120">
            </el-table-column>
            <el-table-column prop="name" label="部门名称" width="120">
            </el-table-column>
            <el-table-column prop="phone" label="联系电话" width="120">
            </el-table-column>
            <el-table-column prop="fax" label="部门传真" width="120">
            </el-table-column>
            <el-table-column prop="deptPath" label="部门路径" width="120">
            </el-table-column>
            <el-table-column prop="superior.name" label="上级部门" width="120">
            </el-table-column>
            <el-table-column prop="principal.username" label="部门主管" width="120">
            </el-table-column>
            <el-table-column prop="remark" label="备注">
            </el-table-column>
            <el-table-column prop="status" label="状态" width="60" :formatter="statusFormatter">
            </el-table-column>

            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button size="small"  @click="edit(scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="del(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--添加或编辑对话框:rules="formRules"验证规则，:visible.sync:id-->
        <el-dialog title="添加或修改" :visible.sync="formVisible" :close-on-click-modal="false">
            <el-form :model="department" label-width="110px" ref="department">
                <el-form-item label="部门标识：" prop="identification">
                    <el-input v-model="department.identification" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门名称：" prop="name">
                    <el-input v-model="department.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系电话：" prop="phone">
                    <el-input v-model="department.phone" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门传真：" prop="fax">
                    <el-input v-model="department.fax" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门路径：" prop="deptPath">
                    <el-input v-model="department.deptPath" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="上级部门：" prop="superior">
                    <el-select v-model="department.superior" placeholder="请选择">
                        <el-option
                                v-for="item in departments"
                                :key="item.id"
                                :label="item.name"
                                :value="item">
                            <span style="float: left">{{ item.id }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.name }}</span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <!--<el-form-item label="部门主管：" prop="principal">
                    <el-input v-model="department.principal" auto-complete="off"></el-input>
                </el-form-item>-->
                <el-form-item label="部门主管：" prop="principal">
                    <el-select v-model="department.principal" placeholder="请选择">
                        <el-option
                                v-for="item in users"
                                :key="item.id"
                                :label="item.username"
                                :value="item">
                            <span style="float: left">{{ item.id }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.username }}</span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备注：" prop="remark">
                    <el-input v-model="department.remark" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="状态：" prop="status">
                    <el-radio-group v-model="department.status" >
                        <el-radio checked="checked" :label="0">正常</el-radio>
                        <el-radio  :label="-1">停用</el-radio>
                    </el-radio-group>
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
                //查询对象
                filters:{
                    keyword:''
                },
                page:1,//当前页,要传递到后台的
                total:0, //分页总数
                users:[],
                departments:[],
                form:{
                    id:null,
                    users:[],
                    departments:[],
                    username:'',
                    name:''
                },
                //初始值
                department:{
                    id:null,
                    identification:'',
                    name:'',
                    phone:'',
                    fax:'',
                    deptPath:'',
                    superior:null,
                    principal:null,
                    remark:'',
                    status:0
                },
                //验证
                formRules: {
                    name: [
                        { required: true, message: '请输入名称!', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            add(){
                //清空数据
                this.department={
                    id:null,
                    identification:'',
                    name:'',
                    phone:'',
                    fax:'',
                    deptPath:'',
                    superior:null,
                    principal:null,
                    remark:'',
                    status:0
                }
                //打开dialog
                this.formVisible =true;
                this.getusers();
            },
            statusFormatter(row, column, cellValue, index){

                if(cellValue===0){
                    return "正常";
                }else{
                    return "停用";
                }
            },
            handleCurrentChange(curentPage){
                this.page = curentPage;
                this.getDepartments();
            },
            save(){
                this.$refs.department.validate((valid) => {
                    //校验表单成功后才做一下操作
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            //拷贝后面对象的值到新对象,防止后面代码改动引起模型变化
                            let para = Object.assign({}, this.department);
                            this.$http.put("/department",para).then((res) => {
                                this.$message({
                                    message: '操作成功!',
                                    type: 'success'
                                });
                                //重置表单
                                this.$refs['department'].resetFields();
                                //关闭对话框
                                this.formVisible = false;
                                //刷新数据
                                this.getDepartments();
                            });
                        });
                    }
                })
            },
            edit(row){
                let departmentTmp = Object.assign({}, row); //解决对话框改值后列表会被改值.
                this.department = departmentTmp; //里面本来就有id,相当于回显了id
                //显示
                this.formVisible =true;
            },
            getusers(){
                //发送请求到后台获取数据
                this.$http.patch("/user") //$.Post(.....)
                    .then(result=>{
                        this.users = result.data;
                    });
            },
            getDepartments(){
                //发送Ajax请求后台获取数据  axios
                //为查询全部增加一个分页和高级查询的参数
                let para = {
                    "page":this.page,
                    "keyword":this.filters.keyword
                };
                this.listLoading = true; //显示加载圈
                this.$http.post("/department",para)
                    .then(result=>{
                        console.log(result);
                        //console.log(result.data);
                        this.total = result.data.total;
                        this.departments = result.data.rows;
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
                    this.$http.delete("/department/"+id)
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
                            this.getDepartments();
                        })
                });

            }
        },
        mounted() {
            this.getDepartments()
        }
    }

</script>

<style scoped>

</style>