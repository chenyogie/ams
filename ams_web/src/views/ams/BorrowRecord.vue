<!--借阅档案-->
<template>
    <section>
        <!--高级查询区-->
        <el-col :span="24" class="toolbar" style="padding-bottom:0px;margin-bottom: 0px">
            <el-form :inline="true">
                <el-form-item label="档案编号：">
                    <el-input v-model="form.num" style="width: 210px"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="searchNum">搜索</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="showSearchDialog">高级查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="reset">重置</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--表格-->
        <el-table :data="records" v-loading="listLoading" highlight-current-row
                  style="width: 100%;" :cell-style="cellStyle">
            <el-table-column type="selection" width="40">
            </el-table-column>
            <el-table-column type="index" label="序号" width="80">
            </el-table-column>
            <el-table-column prop="num" label="档案编号" width="100">
            </el-table-column>
            <el-table-column prop="headline" label="档案名称" width="150">
            </el-table-column>
            <el-table-column prop="type.name" label="档案分类" width="150">
            </el-table-column>
            <el-table-column prop="site.location" label="档案存放点">
            </el-table-column>
            <el-table-column prop="borrowstatus" label="档案状态" :formatter="statusFormatter" width="100">
            </el-table-column>
            <el-table-column label="操作" width="100">
                <template scope="scope">
                    <el-button type="primary" :disabled="scope.row.borrowstatus==0?true:false"
                               size="small" @click="showBorrowDialog(scope.row)">点击借阅</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                @current-change="pageChange"
                :total="total">
        </el-pagination>

        <!--高级查询的表单-->
        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="400px" cell-style="center">
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="档案标题">
                    <el-input v-model="form.headline" placeholder="请输入关键字" style="width: 210px"></el-input>
                </el-form-item>
                <el-form-item label="档案类型">
                    <el-select v-model="form.name" placeholder="请选择档案类型">
                        <el-option
                                v-for="item in form.options"
                                :key="item.name"
                                :label="item.name"
                                :value="item.name">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="档案存档点">
                    <el-select v-model="form.location" placeholder="请选择档案存档点">
                        <el-option
                                v-for="item in form.sites"
                                :key="item.location"
                                :label="item.location"
                                :value="item.location">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSearch">查询</el-button>
                    <el-button type="primary" @click="dialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <!--借阅的表单-->
        <el-dialog title="借阅档案" :visible.sync="borrowVisible" :close-on-click-modal="false" width="490px">
            <el-form :model="borrwForm" label-width="100px" :rules="borrowFormRules" ref="addForm">
                <el-form-item label="档案编号" prop="num">
                    <el-input v-model="borrwForm.num" :disabled="true" auto-complete="off" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="档案标题" prop="headline">
                    <el-input v-model="borrwForm.headline" :disabled="true" auto-complete="off" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="借阅人姓名" prop="borrower.name">
                    <el-input v-model="borrwForm.borrower.name" auto-complete="off" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="证件类型" prop="certificateType">
                    <el-select v-model="borrwForm.typename" placeholder="请选择证件类型">
                        <el-option
                                v-for="item in borrwForm.certificateType"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="证件号码" prop="borrower.certificateNum">
                    <el-input v-model="borrwForm.borrower.certificateNum" auto-complete="off" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="联系方式" prop="borrower.phone">
                    <el-input v-model="borrwForm.borrower.phone" auto-complete="off" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="归还日期" prop="requiredate">
                    <el-date-picker type="date" placeholder="选择日期" v-model="borrwForm.requiredate" style="width: 300px"></el-date-picker>
                </el-form-item>
                <el-form-item label="备注信息" prop="requiredate">
                    <el-input type="textarea" v-model="borrwForm.notes" style="width: 300px"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="borrowVisible = false">取消</el-button>
                <el-button type="primary" @click.native="onSubmit">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        name: "BorrowRecord.vue",
        data() {
            return {
                listLoading: false,
                total:0,
                currentPage: 1,
                records: [],
                dialogVisible: false,
                borrowVisible:false,
                //高级查询界面的数据
                form:{
                    num:'',
                    headline:"",
                    options: [],
                    sites:[],
                    name:''
                },
                //借阅界面的数据
                borrwForm:{
                    num:'',
                    headline:'',
                    requiredate:'',
                    certificateType:[],
                    notes:'',
                    typename:'',
                    borrower:{
                        certificateType:'',
                        name:'',
                        certificateNum:'',
                        phone:''
                    }
                },
                borrowFormRules:{
                    name: [
                        { require: true, message: '请输入活动名称', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    certificateType:[
                        {require:true,message:"请选择证件类型", trigger: 'blur'}
                    ],
                    certificateNum:[
                        {require:true,message:"请输入证件号码", trigger: 'change'}
                    ],
                    requiredate: [
                        { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    phone: [
                        { require: true, required: true, message: '请填写联系方式！', trigger: 'change' }
                    ]
                }
            }
        },
        methods: {
            getRecords() {
                this.listLoading = true;
                let param = {
                    num:this.form.num,
                    headline:this.form.headline,
                    typename:this.form.name,
                    location:this.form.location,
                    currentPage:this.currentPage
                };
                //let param = Object.assign({}, this.form);
                this.$http.post('/borrow/findRecord', param).then((result) => {
                    this.records = result.data.rows;
                    this.total = result.data.total;
                    this.listLoading = false;
                })
            },
            getRecordType() {
                let param = {num:this.form.num};
                this.$http.patch('/recordType', param).then((result) => {
                    this.form.options = result.data.rows;
                })
            },
            getSites(){
                let param = {};
                this.$http.patch('/site',param).then((result)=>{
                    this.form.sites = result.data.rows;
                })
            },
            statusFormatter(row, column, cellValue, index) {
                if (row.borrowstatus == 0) {
                    return "不可借";
                } else {
                    return "可借阅";
                }
            },
            cellStyle({row, column, rowIndex, columnIndex}) {
                if (row.borrowstatus == 0) {
                    return 'color:red';
                }
            },
            showSearchDialog() {
                this.dialogVisible = true;
            },
            onSearch(){
                this.getRecords();
                this.dialogVisible = false;
            },
            searchNum(){
                this.getRecords();
            },
            reset(){
                this.form.num = "";
                this.form.name="";
                this.form.location = "";
                this.form.headline="";
                this.getRecords();
            },
            //显示借阅form
            showBorrowDialog(row){
                this.borrwForm = {
                    num:row.num,
                    recordId:row.id,
                    headline:row.headline,
                    requiredate:'',
                    certificateType:this.getCertificateType(),
                    notes:'',
                    borrower:{
                        certificateType:'',
                        name:'',
                        certificateNum:'',
                        phone:''
                    }
                };
                this.borrowVisible = true;
            },
            //点击借阅按钮后的，添加一条借阅记录的请求
            onSubmit(){
                let param = Object.assign({}, this.borrwForm);
                param.borrower.certificateType = this.borrwForm.typename;
                this.$http.put("/borrow",param).then((result)=>{
                    this.borrowVisible = false;
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
                })
            },
            //获取所有的借阅人信息
            getBorrowers(){

            },
            getCertificateType(){
                this.$http.post("/borrow/findCertificateType").then((result)=>{
                    this.borrwForm.certificateType = result.data;
                })
            },
            pageChange(){
                this.page = this.currentPage;
                this.getRecords();
            }
        },
        mounted() {
            this.getRecords();
            this.getRecordType();
            this.getSites();
            //this.getCertificateType();
        }
    }
</script>

<style scoped>
</style>