package com.diudiu.applet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/26/17
 * @since 0.1
 */
public class Message {
    public static final String SUCCESS = "200";

    public static final Message REQ_SUCCESS = new Message("200", "请求成功");
    public static final Message SYS_ERROR = new Message("500", "请稍后再试");
    public static final Message ARGS_ERROR = new Message("400", "参数错误");

    public static final Message FORBIDDEN_ERROR = new Message("403", "没有访问权限");
    public static final Message NOT_FOUND = new Message("404", "没有找到资源");

    public static final Message SMS_TTL = new Message("550", "验证码过期");
    public static final Message SMS_ERROR = new Message("551", "验证码不正确");

    // 重大错误
    public static final Message REPEAT_REQUEST = new Message("999", "重复提交请求");

    @JsonProperty("StatusCode")
    private String resCode = "200";
    private Boolean Status = true;
    @JsonProperty("Msg")
    private String msg;
    @JsonProperty("Result")
    private Object data;
    private Page page;

    public Message() {
    }

    public Message(String resCode, String msg) {
        this.resCode = resCode;
        this.msg = msg;
    }

    public static  Message successData(Object data) {
        Message m = new Message();
        m.setData(data);
        return m;
    }

    public static Message successMsg(String msg) {
        Message m = new Message();
        m.setMsg(msg);
        return m;
    }

    public static Message page(Object list) {
        Message r = new Message();

        if (list instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page) list;
            Page p = new Page(page.getTotal(), page.getPageNum(),
                    page.getPageNum() == page.getPages() ? page.getPages() : page.getPageNum() + 1,
                    page.getPageNum() == 1 ? 1 : page.getPageNum() - 1,
                    page.getPageSize(), page.getPages());
            r.setPage(p);
            r.setData(page.getResult());
        } else {
            Page p;
            PageInfo pageInfo;
            if (list instanceof PageInfo) {
                pageInfo = (PageInfo) list;
            } else {
                pageInfo = new PageInfo((List) list);
            }
            p = new Page(pageInfo.getTotal(), pageInfo.getPageNum(),
                    pageInfo.getPageNum() + 1, pageInfo.getPageNum() == 1 ? 1 : pageInfo.getPageNum() - 1,
                    pageInfo.getPageSize(), pageInfo.getPages());
            r.setPage(p);
            r.setData(pageInfo.getList());
        }
        return r;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public Message setData(Object data) {
        this.data = data;
        return this;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Message newMessage(String msg) {
        Message message = new Message();
        message.setResCode(this.resCode);
        message.setMsg(msg);
        return message;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS.equals(resCode);
    }

}
