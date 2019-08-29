package com.ares.basic.query;

/**
 * @program: ssmvue
 * @Date: 2019/7/30 16:52
 * @Author: Chenyogie
 * @Description:
 */
public class BaseQuery {

    private Integer currentPage = 1;
    private Integer pageSize = 10;
    private String keyWords;

    /**
     * JavaBean属性
     * @return
     */
    private Integer getStart(){
        return (this.currentPage-1)*pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", keyWords='" + keyWords + '\'' +
                '}';
    }
}
