package com.diudiu.applet.service.impl;

import com.diudiu.applet.global.QueryParams;
import com.diudiu.applet.service.BaseService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService {


    public List<T> startPage(QueryParams qp, ISelect select) {
        if (qp.isPage()) {
            Integer pageNum = qp.pageNum();
            Integer pageSize = qp.pageSize();
            qp.delPageNum().delPageSize();
            return PageHelper.startPage(pageNum, pageSize).doSelectPage(select);
        }
        Page<T> page = PageHelper.startPage(0,0,false,false, true);
        page.doSelectPage(select);
        return page.getResult();
    }
}
