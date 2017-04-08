package com.baidu.exception;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */

public class NoSuchMsgTypeException extends Exception {

    @Override
    public String getMessage() {
        return "No such Message type";
    }
}
