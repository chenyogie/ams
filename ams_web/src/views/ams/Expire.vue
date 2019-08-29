<!--借阅归还，借阅查询-->
<template>
    <section>
        <!--高级查询区-->
        <el-col :span="24" class="toolbar" style="padding-bottom:0px;margin-bottom: 0px">
            <el-form :inline="true">
                <el-form-item label="选择过期时间：">
                    <el-select @change="handleSelect"
                               v-model="sncode" filterable placeholder="请输入/请选择" class="h-m-select">
                        <el-option
                                v-for="item in restaurants"
                                :key="item.name"
                                :label="item.name"
                                v-model="item.sncode">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="search">搜索</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--表格-->
        <el-table :data="records" v-loading="listLoading" highlight-current-row style="width: 100%;">
            <el-table-column type="selection" width="40">
            </el-table-column>
            <el-table-column prop="num" label="档案编号" width="180">
            </el-table-column>
            <el-table-column prop="headline" label="档案名称" width="280">
            </el-table-column>
            <el-table-column prop="type.name" label="档案类型" width="280">
            </el-table-column>
            <el-table-column prop="site.location" label="存放地点" width="280">
            </el-table-column>
            <el-table-column prop="expirationdate" label="到期时间">
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                :total="total">
        </el-pagination>
    </section>
</template>

<script>
    export default {
        data() {
            return {
                listLoading: false,
                total: 0,
                currentPage: 1,
                records: [],
                sncode:'',
                restaurants: []
            }
        },
        methods: {
            getRecords(param) {
                this.listLoading = true;
                this.$http.patch('/record/page', param).then((result) => {
                    this.records = result.data.rows;
                    this.total = result.data.total;
                    this.listLoading = false;
                })
            },
            getExpireTypes(){
                let param = {};
                this.$http.post("/borrow/findExpireType", param).then((result) => {
                    this.restaurants = result.data;
                })
            },
            /**
             * 选中某一个数据后触发的事件
             * @param item 用户选中的下拉表列中的对象
             */
            handleSelect(item) {
                let param = {sncode:item};
                this.$http.patch("/record/page", param).then((result) => {
                    this.records = result.data.rows;
                })
            },
            search() {
                let param = {sncode:this.sncode};
                this.getRecords(param);
            }
        },
        mounted() {
            this.getRecords({});
            this.getExpireTypes();
        }
    }
</script>

<style scoped>
    li {
        line-height: normal;
        padding: 7px;
    }

    .name {
        text-overflow: ellipsis;
        overflow: hidden;
    }

    .highlighted .addr {
        color: #ddd;
    }
</style>