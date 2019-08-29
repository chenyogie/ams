<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="关键字"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getPermissions">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="permissions" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column type="index" width="60">
            </el-table-column>
            <el-table-column prop="sn" label="编号" width="160" sortable>
            </el-table-column>
            <el-table-column prop="identifying" label="标识" width="120" sortable>
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120" sortable>
            </el-table-column>
            <el-table-column prop="url" label="资源路径" min-width="180" sortable>
            </el-table-column>
            <el-table-column prop="status" label="状态" min-width="120" :formatter="formatStatus" sortable>
            </el-table-column>
            <el-table-column prop="parent_id.name" label="上级权限" min-width="180" :formatter="formatPer" sortable>
            </el-table-column>
            <el-table-column prop="menus" label="菜单" min-width="300" :formatter="formatMenu" sortable>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button type="success" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col>
            <el-button type="danger">批量删除</el-button>
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="pageSizes"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    style="float:right;">
            </el-pagination>
        </el-col>
       <!-- <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20"
                           :total="total" style="float:right;">
            </el-pagination>
        </el-col>-->

        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync="centerDialogVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="编号" prop="sn">
                    <el-input v-model="editForm.sn" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="标识" prop="identifying">
                    <el-input v-model="editForm.identifying" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="上级权限" prop="parent_id">
                    <!--<el-input v-model="editForm.parent_id.name" auto-complete="off"></el-input>-->
                    <el-select v-model="editForm.parent_id" placeholder="请选择">
                        <el-option
                                v-for="item in permissions"
                                :key="item.identifying"
                                :label="item.name"
                                :value="item.identifying">
                            <span style="float: left">{{ item.identifying }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.name }}</span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-radio-group v-model="editForm.status">
                        <el-radio class="radio" :label="-1">禁用</el-radio>
                        <el-radio class="radio" :label="0">启用</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="菜单" prop="menus" :formatter="formatMenu" >
                    <el-transfer filterable
                                 v-model="selectMenus"
                                 :titles="['所有菜单', '已有菜单']"
                                 :props="{key:'id',label:'name'}"
                                 style="width: auto"
                                 clearQuery="left"
                                 :data="dataMenus">
                    </el-transfer>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="centerDialogVisible = false">取消</el-button>
                <el-button type="primary" @click.native="save" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <!--<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="addForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="addForm.sex">
                        <el-radio class="radio" :label="1">男</el-radio>
                        <el-radio class="radio" :label="0">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input-number v-model="addForm.age" :min="0" :max="200"></el-input-number>
                </el-form-item>
                <el-form-item label="生日">
                    <el-date-picker type="date" placeholder="选择日期" v-model="addForm.birth"></el-date-picker>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input type="textarea" v-model="addForm.addr"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>-->


    </section>

</template>

<script>
    export default {
        data() {
            return {
                filters: {
                    keyWords: ''
                },
                permissions: [],
                total: 0,
                currentPage: 1,
                pageSizes:[2,5,10,20],
                pageSize:5,

                selectMenus:[],
                dataMenus:[],

                listLoading: false,
                sels: [],//列表选中列

                centerDialogVisible: false,
                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ]
                },
                dialogVisible: false,
                editMenuVisible: true,
                //编辑界面数据
                editForm: {
                    sn: '',
                    identifying:0,
                    name: '',
                    url: '',
                    status: -1,
                    parent_id:[],
                    menus: '',
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ]
                },
                //新增界面数据
                addForm: {
                    sn: '',
                    identifying:0,
                    name: '',
                    url: '',
                    status: -1,
                    parent_id:[],
                    menus: '',
                }

            }
        },
        methods: {
            dialogClose(){
                this.dialogVisible = false;
                this.editMenuVisible = true;
            },
            handleClose(done) {
                this.$confirm('确认关闭？').then(_ => {
                    done();
                }).catch(_ => {
                });
            },
            editMenu() {
                this. dialogVisible = true;
                this.editMenuVisible = false;
            },
            //显示转换
            formatStatus: function (row, column) {
                return row.status == 0 ? '启用' : row.status == -1 ? '禁用' : '未知';
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.getPermissions();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getPermissions();
            },
            //获取权限列表
            getPermissions() {
                let para = {
                    "keyWords": this.filters.keyWords,
                    "currentPage":this.currentPage,
                    "pageSize":this.pageSize
                };
                this.listLoading = true;
                //NProgress.start();
                this.$http.post("/permission",para).then((res) => {
                    console.log(res);
                    this.total = res.data.total;
                    this.permissions = res.data.rows;
                    this.listLoading = false;
                    //NProgress.done();
                });
                this.getTPermissions();
            },
            getTPermissions() {
                let para = {
                };
                this.listLoading = true;
                //NProgress.start();
                this.$http.patch("/permission",para).then((res) => {
                    console.log(res);
                    this.permissions = res.data.data;
                    this.listLoading = false;
                    //NProgress.done();
                });
                console.log("11111111"+this.permissions);
            },
            formatMenu(row,cellValue, column,index){
                if (row.menus){
                    var show = "";
                    for (var item in row.menus){
                        if (item != row.menus.length - 1){
                            show += row.menus[item].name + ",";
                        }else {
                            show += row.menus[item].name ;
                        }
                    }
                    return show;
                }
            },
            formatPer(row,cellValue, column,index){
                /*console.log(row);
                console.log(cellValue);
                console.log(column);
                console.log(index);*/
                if (row.parent_id){
                    var show = "";
                    return  show += row.parent_id.name ;;
                }
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {id: row.id};
                    this.$http.delete("/permissoin",para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getPermissions();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.centerDialogVisible = true;
                this.editForm = Object.assign({}, row);
                if(row.menus){
                    for (var m of row.menus){
                        this.selectMenus.push(m.id);
                    }
                    this.$http.patch("/menu")
                        .then(result => {
                            this.dataMenus = result.data;
                        });
                }
            },
            //显示新增界面
            handleAdd: function () {

                this.addForm = {
                    sn: '',
                    identifying:0,
                    name: '',
                    url: '',
                    status: -1,
                    parent_id:null,
                    permissions:this.$http.patch("/permission")
                        .then(result => {
                            this.permissions = result.data;
                        }),
                    menus:this.$http.patch("/menu")
                        .then(result => {
                            this.dataMenus = result.data;
                        }),
                };
                this.centerDialogVisible = true;
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            editUser(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.editFormVisible = false;
                                this.getPermissions();
                            });
                        });
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.addForm);
                            para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            addUser(para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getPermissions();
                            });
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {ids: ids};
                    batchRemoveUser(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getPermissions();
                    });
                }).catch(() => {

                });
            },
            save: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            this.$http.put("/permission", para).then(result => {
                                this.$message({
                                    message: "操作成功！",
                                    type: 'success'
                                });
                            });
                            this.editLoading = false;
                            this.editForm = {
                                sn: '',
                                identifying:0,
                                name: '',
                                url: '',
                                status: -1,
                                parent_id:[],
                                menus: '',
                            };
                            this.centerDialogVisible = false;
                            this.getPermissions();
                        });
                    }
                });
            },
        },
        mounted() {
            this.getPermissions();

        }
    }

</script>

<style scoped>

</style>