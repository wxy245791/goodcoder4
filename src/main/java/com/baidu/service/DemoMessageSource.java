package com.baidu.service;
/**
 * 模拟消息数据源
 * Created by wangxiaoyu08 on 16/11/11.
 */


import com.baidu.enumeration.MessageType;
import com.baidu.message.BaseMessage;
import com.baidu.message.EmailMessage;
import com.baidu.message.HiMessage;
import com.baidu.message.SmsMessage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DemoMessageSource  {

    private List<BaseMessage> fakeData;

    public DemoMessageSource() {
        makeSomeMessages(7, 5, 8);
    }

    /**
     * 模拟消息源产生了新消息
     */
    public void addNewMessages() {
        makeSomeMessages(9, 7, 3);
    }


    public List<BaseMessage> getUnPushedMessages() {
        return fakeData;
    }

    /**
     * 模拟生成一些新的消息
     *
     * @param emailMsgCount 邮件消息数量
     * @param hiMsgCount    hi消息数量
     * @param smsMsgCount   短信消息数量
     */
    private void makeSomeMessages(int emailMsgCount, int hiMsgCount, int smsMsgCount) {
        int i = fakeData == null ? 0 : fakeData.size();
        fakeData = new LinkedList<BaseMessage>();
        for (int j = 0; j < smsMsgCount; j++, i++) {
            BaseMessage message = new SmsMessage();
            message.setId(i);
            message.setType(MessageType.SMS);
            fakeData.add(message);
        }
        for (int j = 0; j < emailMsgCount; j++, i++) {
            BaseMessage message = new EmailMessage();
            message.setId(i);
            message.setType(MessageType.EMAIL);
            fakeData.add(message);
        }
        for (int j = 0; j < hiMsgCount; j++, i++) {
            BaseMessage message = new HiMessage();
            message.setId(i);
            message.setType(MessageType.HI);
            fakeData.add(message);
        }
        Collections.shuffle(fakeData);
    }
}

