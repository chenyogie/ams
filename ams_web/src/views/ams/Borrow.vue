<!--借阅归还，借阅查询-->
<template>
    <section>
        <!--高级查询区-->
        <el-col :span="24" class="toolbar" style="padding-bottom:0px;margin-bottom: 0px">
            <el-form :inline="true">
                <el-form-item label="归还日期">
                    <el-date-picker
                            v-model="begindate"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="-">
                    <el-date-picker
                            v-model="enddate"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="search">搜索</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--表格-->
        <el-table :data="borrows" v-loading="listLoading" highlight-current-row style="width: 100%;">
            <el-table-column type="selection" width="40">
            </el-table-column>
            <el-table-column type="index" label="序号" width="55">
            </el-table-column>
            <el-table-column prop="borrowNum" label="借阅编号" width="100">
            </el-table-column>
            <el-table-column prop="num" label="档案编号" width="120">
            </el-table-column>
            <el-table-column prop="headline" label="档案名称" width="150">
            </el-table-column>
            <el-table-column prop="typename" label="档案分类" width="100">
            </el-table-column>
            <el-table-column prop="location" label="档案存放点" width="200">
            </el-table-column>
            <el-table-column prop="name" label="借阅人" width="100">
            </el-table-column>
            <el-table-column prop="" label="借阅状态" width="100">
                <template slot-scope="scope">
                    <span v-if="getStatus(scope.row.requiredate,scope.row.status)=='逾期'"
                          style="color: red">
                        逾期</span>
                    <span v-if="getStatus(scope.row.requiredate,scope.row.status)=='近3天到期'"
                          style="color: darkorange">
                        近3天到期</span>
                    <span v-if="getStatus(scope.row.requiredate,scope.row.status)=='正常'"
                          style="color: black">
                        正常</span>
                    <span v-if="getStatus(scope.row.requiredate,scope.row.status)=='已归还'"
                          style="color: darkblue">
                        已归还</span>
                </template>
            </el-table-column>
            <el-table-column prop="date" label="借阅日期" width="100">
            </el-table-column>
            <el-table-column prop="requiredate" label="应还日期">
            </el-table-column>
            <el-table-column label="操作" width="100">
                <template scope="scope">
                    <div v-if="scope.row.status==0?true:false">
                        <el-button type="primary" size="small" @click="showRevertDialog(scope.row)">确认归还</el-button>
                    </div>
                    <div v-else>
                        <el-tag type="success">已归还</el-tag>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                @current-change="pageChange"
                :total="total">
        </el-pagination>

        <!--归还的表单-->
        <el-dialog title="归还档案" :visible.sync="revertVisible" :close-on-click-modal="false" width="490px">
            <el-form :model="revertForm" label-width="100px" :rules="revertFormRules">
                <el-form-item label="借阅编号" prop="borrow.borrowNum">
                    <el-input v-model="revertForm.borrow.borrowNum" :disabled="true" auto-complete="off"
                              style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="归还日期" prop="date">
                    <el-date-picker type="date" placeholder="选择日期" v-model="revertForm.date"
                                    style="width: 300px"></el-date-picker>
                </el-form-item>
                <el-form-item label="备注信息" prop="note">
                    <el-input type="textarea" v-model="revertForm.note" style="width: 300px"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="revertVisible = false">取消</el-button>
                <el-button type="primary" @click.native="onSubmit">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        name: "Borrow.vue",
        data() {
            return {
                listLoading: false,
                total: 0,
                currentPage: 1,
                borrows: [],
                begindate: '',
                enddate: '',
                restaurants: [],
                revertForm: {
                    borrow: {},
                    date: new Date(),
                    note: ''
                },
                revertVisible: false,
                revertFormRules: {}
            }
        },
        methods: {
            getBorrows(param) {
                this.listLoading = true;
                param.currentPage = this.currentPage;
                this.$http.patch('/borrow', param).then((result) => {
                    this.borrows = result.data.rows;
                    this.total = result.data.total;
                    this.listLoading = false;
                })
            },
            getStatus(date,status) {
                let startDate = Date.parse(date.replace('/-/g', '/'));
                let endDate = new Date();
                let diffDate = (endDate - startDate);
                let days = diffDate / (1 * 24 * 60 * 60 * 1000);
                if(status==1){
                    return "已归还";
                }
                if (days>=0){
                    return "逾期";
                }else if(days>=-3 && days <0){
                    return "近3天到期";
                }else{
                    return "正常";
                }
            },
            search(){
                let param = {begindate:this.begindate, enddate:this.enddate};
                this.$http.patch('/borrow', param).then((result) => {
                    this.borrows = result.data.rows;
                    this.total = result.data.total;
                    this.listLoading = false;
                })
            },
            showRevertDialog(row){
                this.revertForm = {
                    date: new Date(),
                    note: '',
                    borrow: {
                        id: row.id,
                        borrowNum:row.borrowNum
                    }
                };
                this.revertVisible = true;
            },
            onSubmit() {
                let param = Object.assign({}, this.revertForm);
                this.$http.put("/revert", param).then((result) => {
                    this.revertVisible = false;
                    if (result.data.success) {
                        this.$message({
                            message: "档案归还成功！",
                            type: 'success'
                        });
                    } else {
                        this.$message({
                            message: result.data.message,
                            type: 'error'
                        });
                    }
                    //重新加载页面
                    this.getBorrows({});
                });
            },
            pageChange(val){
                this.currentPage = val;
                this.getBorrows({});
            }
        },
        mounted() {
            let param = {};
            this.getBorrows(param);
        }
    }
</script>

<style scoped>

</style>