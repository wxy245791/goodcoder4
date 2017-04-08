package com.baidu.sender;

/**
 * 生成具体的消息发送工具
 * 预先生成各个sender，可以提高提高消息发送的时效性
 * Created by wangxiaoyu08 on 16/11/11.
 */

import com.baidu.enumeration.MessageType;
import com.baidu.exception.NoSuchMsgTypeException;

public class MessageSenderPool {

    private static IMessageSender emailMessageSender;
    private static IMessageSender hiMessageSender;
    private static IMessageSender smsMessageSender;

    static {
        emailMessageSender = new EmailMessageSender();
        hiMessageSender = new HiMessageSender();
        smsMessageSender = new SmsMessageSender();
    }

    /**
     * 根据消息类型获取相应的消息发送工具
     *
     * @param messageType 消息类型
     *
     * @return IMessageSender 具体类
     *
     * @throws NoSuchMsgTypeException 参数messageType表示的消息没有对应的发送工具
     */
    public static IMessageSender getMessageSender(MessageType messageType) throws NoSuchMsgTypeException {
        switch (messageType) {
            case SMS:
                return smsMessageSender;
            case HI:
                return hiMessageSender;
            case EMAIL:
                return hiMessageSender;
            default:
                throw new NoSuchMsgTypeException();
        }
    }
}

