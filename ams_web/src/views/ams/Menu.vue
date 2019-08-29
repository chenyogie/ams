npm<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.keyWords" placeholder="姓名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getMenus">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="menus" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="sn" label="编号" width="120" sortable>
			</el-table-column>
			<el-table-column prop="name" label="菜单名" width="180" sortable>
			</el-table-column>
			<el-table-column prop="url" label="资源路径" width="180"  sortable>
			</el-table-column>
			<el-table-column prop="icon" label="图标" width="180" sortable>
			</el-table-column>
			<el-table-column prop="parent_id.name" label="上级菜单" width="120" :formatter="formatMune" sortable>
			</el-table-column>
			<el-table-column prop="status" label="状态" min-width="120" :formatter="formatStatus" sortable>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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

		<!--编辑界面-->
		<el-dialog title="编辑" :visible.sync="centerDialogVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="编号" prop="sn">
					<el-input v-model="editForm.sn" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="姓名" prop="name">
					<el-input v-model="editForm.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="图标" prop="icon">
					<el-input v-model="editForm.icon" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="状态">
					<el-radio-group v-model="editForm.status">
						<el-radio class="radio" :label="-1">禁用</el-radio>
						<el-radio class="radio" :label="0">启用</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="上级菜单" prop="parent_id">
					<!--<el-input v-model="editForm.parent_id.name" auto-complete="off"></el-input>-->
					<el-select v-model="editForm.parent_id"    placeholder="请选择">
						<el-option
								v-for="item in parent_id"
								:key="item.sn"
								:label="item.name"
								:value="item.sn">
							<span style="float: left">{{ item.sn }}</span>
							<span style="float: right; color: #8492a6; font-size: 13px">{{ item.name }}</span>
						</el-option>
					</el-select>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="centerDialogVisible = false">取消</el-button>
				<el-button type="primary" @click.native="save" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
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
		</el-dialog>
	</section>
</template>

<script>
	/*import util from '../../common/js/util'
	//import NProgress from 'nprogress'
	import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';*/

	export default {
		data() {
			return {
				filters: {
					keyWords: ''
				},
				menus: [],
                parent_id:[],
				total: 0,
				currentPage: 1,
                pageSizes:[2,5,10,20],
                pageSize:5,
				listLoading: false,
				sels: [],//列表选中列

                centerDialogVisible:false,
				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//编辑界面数据
				editForm: {
					sn: 0,
					name: '',
					icon: "",
					status: 0,
                    parent_id:[]
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
                    sn: 0,
                    name: '',
                    icon: "",
                    status: 0,
                    parent_id:[]
				}

			}
		},
		methods: {
            //显示转换
            formatStatus: function (row, column) {
                return row.status == 0 ? '启用' : row.status == -1 ? '禁用' : '未知';
            },
            formatMune(row){
			    if(row.parent_id){
                    var show = "";
                    return  show += row.parent_id.name ;;
				}
			},
            handleSizeChange(val) {
                this.pageSize = val;
                this.getMenus();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getMenus();
            },
			//获取用户列表
			getMenus() {
				let para = {
				    "pageSize":this.pageSize,
                    "currentPage": this.currentPage,
					"keyWords": this.filters.keyWords
				};
				this.listLoading = true;
				//NProgress.start();
				this.$http.post("/menu",para).then((res) => {
                    console.log(res);
					this.total = res.data.total;
					this.menus = res.data.rows;
					this.listLoading = false;
					//NProgress.done();
				});
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { "id": row.id };
					this.$http.delete("/menu",para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getMenus();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.centerDialogVisible = true;
				this.editForm = Object.assign({}, row);
			},
			//显示新增界面
			handleAdd: function () {
				this.addForm = {
                    sn: 0,
                    name: '',
                    icon: "",
                    status: 0,
                    parent_id:this.$http.patch("/menu").then(result => {
                        this.parent_id = result.data;
					})
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
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getMenus();
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
								this.addFormVisible = false;
								this.getMenus();
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
					let para = { ids: ids };
					batchRemoveUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getMenus();
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
                            this.$http.put("/menu", para).then(result => {
                                this.$message({
                                    message: "操作成功！",
                                    type: 'success'
                                });
                            });
                            this.editLoading = false;
                            this.editForm = {
                                sn: 0,
                                name: '',
                                icon: "",
                                status: 0,
                                parent_id:[]
                            };
                            this.centerDialogVisible = false;
                            this.getMenus();
                        });
                    }
                });
            },
		},
		mounted() {
			this.getMenus();
		}
	}

</script>

<style scoped>

</style>