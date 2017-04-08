package com.baidu.enumeration;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */

public enum MessageType {

    EMAIL, HI, SMS;

    public String getTypeName() {
        switch (this) {
            case EMAIL:
                return "邮件消息";
            case HI:
                return "Hi消息";
            case SMS:
                return "短信消息";
            default:
                return "";
        }
    }
}

