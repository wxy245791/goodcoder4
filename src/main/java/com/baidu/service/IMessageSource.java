package com.baidu.service;

import com.baidu.message.BaseMessage;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */

public interface IMessageSource {

    public boolean sendMessage(BaseMessage message);
}
