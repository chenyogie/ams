package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

/**
 * @program: asm
 * @Date: 2019/8/5 18:55
 * @Author: Chenyogie
 * @Description:
 */
public class RecordType extends BaseDomain {
    //类型编码
    private String sncode;

    //类型名称
    private String name;

    public String getSncode() {
        return sncode;
    }

    public void setSncode(String sncode) {
        this.sncode = sncode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RecordType{" +
                "sncode='" + sncode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
