<!--借阅归还，借阅查询-->
<template>
    <section>
        <!--高级查询区-->
        <el-col :span="24" class="toolbar" style="padding-bottom:0px;margin-bottom: 0px">
            <el-form :inline="true">
                <el-form-item label="借阅编号：">
                    <el-autocomplete
                            v-model="borrowNum"
                            :fetch-suggestions="querySearchAsync"
                            placeholder="请输入借阅编号"
                            @select="handleSelect"
                    >
                        <template slot-scope="{ item }">
                            <div class="name">{{ item.borrowNum }}</div>
                            <span class="addr">{{ item.headline }}</span>
                        </template>
                    </el-autocomplete>
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
            <el-table-column prop="num" label="档案编号" width="120">
            </el-table-column>
            <el-table-column prop="headline" label="档案名称" width="210">
            </el-table-column>
            <el-table-column prop="name" label="借阅人" width="100">
            </el-table-column>
            <el-table-column prop="status" label="借阅状态" width="100">
                <template slot-scope="scope">
                    <span v-if="getStatus(scope.row.requiredate)=='逾期'"
                          style="color: red">
                        {{getStatus(scope.row.requiredate)}}</span>
                    <span v-if="getStatus(scope.row.requiredate)=='近3天到期'"
                          style="color: darkorange">
                        {{getStatus(scope.row.requiredate)}}</span>
                    <span v-if="getStatus(scope.row.requiredate)=='正常'"
                          style="color: black">
                        {{getStatus(scope.row.requiredate)}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="date" label="借阅日期" width="100">
            </el-table-column>
            <el-table-column prop="requiredate" label="应还日期">
            </el-table-column>
            <el-table-column label="操作" width="100">
                <template scope="scope">
                    <el-button type="primary" size="small" @click="showReturnDialog(scope.row)">点击归还</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
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
        data() {
            return {
                listLoading: false,
                total: 0,
                currentPage: 1,
                borrows: [],
                borrowNum: '',
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
                param.status = false;
                this.$http.patch('/borrow', param).then((result) => {
                    if(result.data.total==0){
                        this.$message({
                            message: '对不起，没有符合条件的数据，请重新输入！',
                            center: true
                        });
                    }
                    this.borrows = result.data.rows;
                    this.total = result.data.total;
                    this.listLoading = false;
                })
            },
            /**
             * 输入的时候去服务端查询的数据[模糊查询]
             * @param queryString input输入框中输入的内容[值一旦改变就会触发这个方法]
             * @param cb 将服务器返回的数据显示在下来列表中
             * 注意：返回的数据中，核心字段要讲字段名称改为value
             */
            querySearchAsync(queryString, cb) {
                let param = {borrowNum: queryString, status:false};
                this.$http.patch("/borrow", param).then((result) => {
                    this.restaurants = result.data.rows;
                    // 调用 callback 返回建议列表的数据
                    cb(this.restaurants);
                })

            },
            /**
             * 选中某一个数据后触发的事件
             * @param item 用户选中的下拉表列中的对象
             */
            handleSelect(item) {
                this.$http.patch("/borrow", item).then((result) => {
                    this.borrows = result.data.rows;
                })
            },
            search() {
                let param = {borrowNum:this.borrowNum,status:false};
                this.getBorrows(param);
            },
            /**
             * 根据日期计算出借阅状态：
             *  逾期、近3天到期、正常
             * @param date
             * @param now
             * @reverts {number}
             */
            getStatus(date) {
                let startDate = Date.parse(date.replace('/-/g', '/'));
                let endDate = new Date();
                //let endDate = Date.parse(now.replace('/-/g', '/'));
                let diffDate = (endDate - startDate) + 1 * 24 * 60 * 60 * 1000;
                let days = diffDate / (1 * 24 * 60 * 60 * 1000);
                if (days > 0) {
                    return "逾期";
                } else if (days > -3 && days < 0) {
                    return "近3天到期";
                } else {
                    return "正常";
                }
            },
            showReturnDialog(row) {
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
                    this.getBorrows(param.borrow);
                });
            }
        },
        mounted() {
        }
    }
</script>

<style scoped>
    .my-autocomplete {

    }

    li {
        line-height: normal;
        padding: 7px;
    }

    .name {
        text-overflow: ellipsis;
        overflow: hidden;
    }

    .addr {
        font-size: 12px;
        color: #b4b4b4;
    }

    .highlighted .addr {
        color: #ddd;
    }
</style>