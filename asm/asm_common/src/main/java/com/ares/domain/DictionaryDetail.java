package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

public class DictionaryDetail extends BaseDomain {
    private String sncode;
    private String name;
    private Long parent_id;
    private Long types_id;
    private Long centralVal;

    public Long getCentralVal() {
        return centralVal;
    }

    public void setCentralVal(Long centralVal) {
        this.centralVal = centralVal;
    }

    public String getValue(){
        return this.sncode;
    }

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

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getTypes_id() {
        return types_id;
    }

    public void setTypes_id(Long types_id) {
        this.types_id = types_id;
    }

    @Override
    public String toString() {
        return "DictionaryDetail{" +
                "sncode='" + sncode + '\'' +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", types_id=" + types_id +
                ", id=" + id +
                '}';
    }
}
