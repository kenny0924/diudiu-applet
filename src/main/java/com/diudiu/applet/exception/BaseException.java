package com.diudiu.applet.exception;


import com.diudiu.applet.dto.Message;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 1/25/18
 * @since 0.1
 */
public class BaseException extends RuntimeException {
    /**  */
	private static final long serialVersionUID = 8510100868248962552L;

	/**
     * 错误代码
     **/
    private String resCode;

    /**
     * 错误消息
     **/
    private String msg;

    protected BaseException() {
    }

    public BaseException(Message message) {
        this.resCode = message.getResCode();
        this.msg = message.getMsg();
    }

    public String getResCode() {
        return resCode;
    }

    protected void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    protected void setMsg(String msg) {
        this.msg = msg;
    }

    public String print() {
        return "resCode: " + resCode + ", msg: " + msg;
    }
}
