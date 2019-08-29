package com.ares.query;

import com.ares.basic.query.BaseQuery;

/**
 * @program: asm
 * @Date: 2019/8/6 17:27
 * @Author: Chenyogie
 * @Description:
 */
public class RecordQuery extends BaseQuery {
    //档案标题
    private String headline;
    //档案存档点
    private String location;
    //档案类型
    private String typename;
    //档案编号
    private String num;
    //档案状态
    private String recStates;

    public String getRecStates() {
        return recStates;
    }

    public void setRecStates(String recStates) {
        this.recStates = recStates;
    }
    //到期管理的标识
    private String sncode;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSncode() {
        return sncode;
    }

    public void setSncode(String sncode) {
        this.sncode = sncode;
    }

    @Override
    public String toString() {
        return "RecordQuery{" +
                "headline='" + headline + '\'' +
                ", location='" + location + '\'' +
                ", typename='" + typename + '\'' +
                ", sncode='" + sncode + '\'' +
                ", num='" + num + '\'' + "," + super.toString() +
                '}';
    }
}
