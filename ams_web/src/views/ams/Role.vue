<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filter">
                <el-form-item>
                    <el-input v-model="filter.keyWords" placeholder="关键字"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getRoles">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="roles" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column type="index" width="60">
            </el-table-column>
            <el-table-column prop="sn" label="编号" width="140" sortable>
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120" sortable>
            </el-table-column>
            <el-table-column prop="identifying" label="标识" width="100" sortable>
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100" :formatter="formatStatus" sortable>
            </el-table-column>
            <el-table-column prop="permissions" label="已有权限" width="360" :formatter="formatPermission" sortable>
            </el-table-column>
            <el-table-column prop="menus" label="拥有菜单" width="360" :formatter="formatMenu" sortable>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <!--<el-col :span="24" class="toolbar">
            <el-button type="danger">批量删除</el-button>
            <el-pagination layout="prev, pager, next" :page-size="2" :total="total" style="float:right;">
            </el-pagination>
        </el-col>-->

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

        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync="centerDialogVisible" :close-on-click-modal="false">
            <el-form :model="roleEdit" label-width="80px" :rules="formRules" ref="roleEdit">
                <el-form-item label="编号">
                    <el-input v-model="roleEdit.sn"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="roleEdit.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="标识">
                    <el-input v-model="roleEdit.identifying"></el-input>
                </el-form-item>
                <el-form-item label="状态">
                    <el-radio-group v-model="roleEdit.status">
                        <el-radio class="radio" :label="-1">禁用</el-radio>
                        <el-radio class="radio" :label="0">启用</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="权限" prop="permissions" :formatter="formatPermission">
                    <el-transfer filterable
                                 v-model="selectPermissions"
                                 :titles="['所有权限', '已有权限']"
                                 :props="{key:'id',label:'name'}"
                                 style="width: auto"
                                 clearQuery="left"
                                 :data="dataPermissions">
                    </el-transfer>
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
                <el-button type="primary" :loading="editLoading" @click="save">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <!--<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">-->
        <!--<el-form :model="roleAdd" label-width="80px" :rules="addFormRules" ref="roleAdd">-->
        <!--<el-form-item label="编号" prop="sn">-->
        <!--<el-input v-model="roleAdd.sn"></el-input>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="姓名" prop="name">-->
        <!--<el-input v-model="roleAdd.name" auto-complete="off"></el-input>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="标识" prop="identifying">-->
        <!--<el-input v-model="roleAdd.identifying" ></el-input>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="状态">-->
        <!--<el-radio-group v-model="roleAdd.status">-->
        <!--<el-radio class="radio" :label="-1">禁用</el-radio>-->
        <!--<el-radio class="radio" :label="0">启用</el-radio>-->
        <!--</el-radio-group>-->
        <!--</el-form-item>-->
        <!--</el-form>-->
        <!--<div slot="footer" class="dialog-footer">-->
        <!--<el-button @click.native="addFormVisible = false">取消</el-button>-->
        <!--<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>-->
        <!--</div>-->
        <!--</el-dialog>-->
    </section>
</template>

<script>
    export default {
        data() {
            return {
                filter:{
                    keyWords: ''
                },
                roles: [],
                total: 0,
                currentPage: 1,
                pageSize:2,
                pageSizes:[2, 5, 10, 20],
                listLoading: false,
                sels: [],//列表选中列

                selectPermissions:[],
                dataPermissions:[],
                selectMenus:[],
                dataMenus:[],

                centerDialogVisible: false,//编辑界面是否显示

                editLoading: false,
                formRules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ],
                    sn: [
                        {required: true, message: '编号', trigger: 'blur'}
                    ],
                    identifying: [
                        {required: true, message: '标识', trigger: 'blur'}
                    ]
                },
                //编辑界面数据
                roleEdit: {
                    sn: '',
                    name: '',
                    identifying: '',
                    status: -1,
                    permissions:[],
                    menus:[]

                },

                //新增界面是否显示
                addLoading: false,
                addFormRules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ]
                },
                //新增界面数据
                roleAdd: {
                    sn: '',
                    name: '',
                    identifying: '',
                    status: -1,
                    permissions:[],
                    menus:[]
                }

            }
        },
        methods: {
            handleSizeChange(val) {
                this.pageSize = val;
                this.getRoles();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getRoles();
            },
            search() {
                var param = {
                    keyWords: this.filter.keyWords,
                    currentPage:this.currentPage,
                    pageSize:this.pageSizes
                };
                this.$http.post("/role", param).then(result => {
                    console.log(result);

                })
            },
            //显示转换
            formatStatus: function (row, column) {
                return row.status == 0 ? '启用' : row.status == -1 ? '禁用' : '未知';
            },
            formatPermission(row,cellValue, column,index){
                /*console.log(row);
                console.log(cellValue);
                console.log(column);
                console.log(index);*/
                if (row.permissions){
                    var show = "";
                    for (var item in row.permissions){
                        if (item != row.permissions.length - 1){
                            show += row.permissions[item].name + ",";
                        }else {
                            show += row.permissions[item].name ;
                        }
                    }
                    return show;
                }
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
            filterMethod(query, item) {
            },
            //获取角色列表
            getRoles() {
                let param = {
                    "keyWords": this.filter.keyWords,
                    "currentPage":this.currentPage,
                    "pageSize":this.pageSize
                };
                this.listLoading = true;
                this.$http.post("/role", param)
                    .then(result => {
                        this.total = result.data.total;
                        this.roles = result.data.rows;
                        this.listLoading = false;
                    });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {id: row.id};
                    this.$http.delete("/role/delete", para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getRoles();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.roleEdit = this.roles;
                this.roleEdit = Object.assign({}, row);
                if (row.permissions){
                    for (var p of row.permissions){
                        this.selectPermissions.push(p.id);
                    }
                    this.$http.patch("/permission")
                        .then(result => {
                            this.dataPermissions = result.data;
                        });
                }
                if(row.menus){
                    for (var m of row.menus){
                        this.selectMenus.push(m.id);
                    }
                    this.$http.patch("/menu")
                        .then(result => {
                            this.dataMenus = result.data;
                        });
                }
                this.centerDialogVisible = true;
            },
            //显示新增界面
            handleAdd: function () {
                this.roleEdit = {
                    sn: '',
                    name: '',
                    identifying: '',
                    status: -1,
                    permissions:this.$http.patch("/permission")
                        .then(result => {
                            this.dataPermissions = result.data;
                        }),
                    menus:this.$http.patch("/menu")
                        .then(result => {
                            this.dataMenus = result.data;
                        })
                };
                this.centerDialogVisible = true;
                this.getRoles();
            },
            //编辑
            save: function () {
                this.$refs.roleEdit.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.roleEdit);
                            if(this.selectMenus){
                                var temp = [];
                                for(var index in this.selectMenus){
                                    temp.push({"id":this.selectMenus[index]});
                                }
                                para.menus = temp;
                            }
                            if (this.selectPermissions){
                                var temp = [];
                                for(var index in this.selectPermissions){
                                    temp.push({"id": this.selectPermissions[index]});
                                }
                                para.permissions = temp;
                            }
                            this.$http.put("/role", para).then(result => {
                                this.$message({
                                    message: "操作成功！",
                                    type: 'success'
                                });
                            });

                            this.editLoading = false;
                            this.roleEdit =  {
                                sn: '',
                                name: '',
                                identifying: '',
                                status: -1,
                                permissions:[],
                                menus:[]

                            };
                            this.centerDialogVisible = false;
                            this.getRoles();
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
                        this.getUsers();
                    });
                }).catch(() => {

                });
            },
           /* handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            }*/
        },
        mounted() {
            this.getRoles();
        }
    }

</script>

<style scoped>

</style>