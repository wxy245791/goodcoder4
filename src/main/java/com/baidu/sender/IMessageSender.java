package com.baidu.sender;
import com.baidu.message.BaseMessage;
/**
 * Created by wangxiaoyu08 on 16/11/11.
 */

public interface IMessageSender {

    public boolean sendMessage(BaseMessage message);
}

