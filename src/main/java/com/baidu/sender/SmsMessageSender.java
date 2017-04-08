package com.baidu.sender;

import com.baidu.message.BaseMessage;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */

public class SmsMessageSender implements IMessageSender {


    public boolean sendMessage(BaseMessage message) {
        // to send a sms message
        return true;
    }
}
