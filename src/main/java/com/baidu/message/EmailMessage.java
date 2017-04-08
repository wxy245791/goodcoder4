package com.baidu.message;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */


public class EmailMessage extends BaseMessage {

    private String fromEmail;
    private String toEmail;
    protected String subject;

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

