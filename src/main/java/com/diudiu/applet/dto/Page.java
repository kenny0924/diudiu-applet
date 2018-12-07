package com.diudiu.applet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 11/15/17
 * @since 0.1
 */
@Data
public class Page {
    /*//列表商品总数
        "totalSize":112,
                //当前页码
                "pageNumber":1,
                //总页数
                "totalPages":6,
    //筛选项*/
    @JsonProperty("totalSize")
    private Long totalRow;
    @JsonProperty("pageNumber")
    private Integer currentPage;
    private Integer nextPage;
    private Integer prePage;
    private Integer pageSize;
    @JsonProperty("totalPages")
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

}
