<template>
    <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"
             class="demo-ruleForm login-container">
        <h3 class="title">系统登录</h3>
        <el-form-item prop="account">
            <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号  account"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
            <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"
                      placeholder="密码  password"></el-input>
        </el-form-item>
        <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
        <el-form-item style="width:100%;">
            <el-button type="primary" style="width:40%;float: right" @click.native.prevent="handleSubmit2"
                       :loading="logining">登录
            </el-button>
            <el-button type="warning" @click.native.prevent="handleReset2" style="width: 40%;float:left;">重置</el-button>
        </el-form-item>
        <el-form-item>
            <el-button @click.native.prevent="handleRegister" style="width: 40%;float: right">注册</el-button>
            <el-button type="text" @click.native.prevent="handleRetrievePassword" style="width: 40%;float:left;">
                忘记密码？找回。。
            </el-button>
        </el-form-item>

    </el-form>
</template>

<script>
    import {requestLogin} from '../api/api';
    //import NProgress from 'nprogress'
    export default {
        data() {
            return {
                logining: false,
                ruleForm2: {
                    account: 'test',
                    checkPass: '1'
                },
                rules2: {
                    account: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        //{ validator: validaePass }
                    ],
                    checkPass: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        //{ validator: validaePass2 }
                    ]
                },
                checked: true
            };
        },
        methods: {
            handleRegister() {
            },
            handleRetrievePassword() {
            },
            handleReset2() {
                this.ruleForm2 = {}
            },
            handleSubmit2(ev) {
                var _this = this;
                this.$refs.ruleForm2.validate((valid) => {
                    if (valid) {
                        //_this.$router.replace('/table');
                        this.logining = true;
                        //NProgress.start();
                        var loginParams = {username: this.ruleForm2.account, password: this.ruleForm2.checkPass};
                       /* loginParams = JSON.stringify(loginParams);*/
                        this.$http.post("/login", loginParams).then(data => {
                            this.logining = false;
                            //NProgress.done();
                            let {message, success, resultObj} = data.data;
                            if (!success) {
                                this.$message({
                                    message: message,
                                    type: 'error'
                                });
                            } else {
                                console.log(resultObj.user);
                                console.log(resultObj.token);
                                sessionStorage.setItem('user', JSON.stringify(resultObj.user.username));
                                sessionStorage.setItem('token', resultObj.token);
                                this.$router.push({path: '/'});
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }

</script>

<style lang="scss" scoped>
    .login-container {
        /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        .title {
            margin: 0px auto 40px auto;
            text-align: center;
            color: #505458;
        }
        .remember {
            margin: 0px 0px 35px 0px;
        }
        .text {
            font-size: 14px;
        }
        .item {
            margin-bottom: 18px;
        }
        .clearfix:before, .clearfix:after {
            display: table;
            content: "";
        }
        .clearfix:after {
            clear: both
        }
        .box-card {
            width: 480px;
        }
    }
</style>