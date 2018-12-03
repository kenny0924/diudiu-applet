package com.diudiu.applet.dto;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 11/15/17
 * @since 0.1
 */
public class Page {
    private Long totalRow;
    private Integer currentPage;
    private Integer nextPage;
    private Integer prePage;
    private Integer pageSize;
    private Integer pages;

    public Page() {
    }

    public Page(Long totalRow, Integer currentPage, Integer nextPage, Integer prePage, Integer pageSize, Integer pages) {
        this.totalRow = totalRow;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.prePage = prePage;
        this.pageSize = pageSize;
        this.pages = pages;
    }

    public Long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Long totalRow) {
        this.totalRow = totalRow;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
