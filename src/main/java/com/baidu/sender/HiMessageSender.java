package com.baidu.sender;

import com.baidu.message.BaseMessage;

/**
 * Hi消息发送类
 * Created by wangxiaoyu08 on 16/11/11.
 */

public class HiMessageSender implements IMessageSender {


    public boolean sendMessage(BaseMessage message) {
        // to send a hi message
        return true;
    }
}
