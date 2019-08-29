package com.ares.basic;

/**
 * @program: ssm_parent
 * @Date: 2019/7/30 18:28
 * @Author: Chenyogie
 * @Description: 异步请求返回的json对象
 */
public class AjaxResult {

    private Boolean success = true;
    private String message = "操作成功";
    private Object resultObj = null;

    private AjaxResult(){}

    private AjaxResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * 操作成功的调用
     * @return
     */
    public static AjaxResult getInstance(){
        return new AjaxResult();
    }

    /**
     * 操作失败的调用
     * @param message 操作失败的提示信息
     * @return
     */
    public static AjaxResult getInstance(String message){
        return new AjaxResult(false,message);
    }

    public Boolean getSuccess() {
        return success;
    }

    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
        return this;
    }
}
