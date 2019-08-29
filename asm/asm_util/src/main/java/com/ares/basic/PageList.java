package com.ares.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ssm_parent
 * @Date: 2019/7/31 10:12
 * @Author: Chenyogie
 * @Description: 分页数据的返回对象
 */
public class PageList<T> {

    private Long total = 0L;
    private List<T> rows = new ArrayList<>();

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageList() {
    }

    public PageList(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
