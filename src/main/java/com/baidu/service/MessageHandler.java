package com.baidu.service;

import com.baidu.exception.NoSuchMsgTypeException;
import com.baidu.message.BaseMessage;
import com.baidu.sender.MessageSenderPool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消息中心处理类
 * Created by wangxiaoyu08 on 16/11/11.
 */


public class MessageHandler {

    private DemoMessageSource messageSource;

    private static MessageHandler messageHandler;

    /**
     * 消息队列，持有待发送的消息
     */
    private final BlockingQueue<BaseMessage> messageQueue;

    /**
     * 消息发送线程池，调度每一个发送消息的线程
     */
    private ExecutorService threadPool;

    private static final int QUEUE_SIZE = 10;
    private static final int THREAD_POOL_SIZE = 5;

    private MessageHandler() {
        messageQueue = new LinkedBlockingQueue<BaseMessage>(QUEUE_SIZE);
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    /**
     * 服务器每分钟可以发送的消息数量
     */
    private static int maxMsgCountPerMin = 15;

    /**
     * 获取消息处理类的唯一实例
     *
     * @param messageSource 指定一个消息源
     * @return MessageHandler实例
     */
    public static MessageHandler newInstance(DemoMessageSource messageSource) {
        if (messageHandler == null) {
            messageHandler = new MessageHandler();
        }
        messageHandler.setMessageSource(messageSource);
        return messageHandler;
    }

    /**
     * 启动消息中心主循环线程
     */
    public void start() {
        notifyMessageChanged();
        new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                while (true) {
                    counter++;
                    BaseMessage message = null;
                    try {
                        message = messageQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(message.getType().getTypeName() + message.getId() + " ：开始发送......");
                    submitSendingTask(message);
                    if (counter == maxMsgCountPerMin) {
                        counter = 0;
                        try { // 每分钟可发消息数已达上限时休眠一分钟，等待下一分钟继续发送
                            Thread.sleep(60 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "THREAD: SEND MESSAGE").start();
    }

    /**
     * 调用此方法告知消息处理器有新的消息要发送
     * 消息处理器通过调用IMessageSource定义的接口获取待发消息
     */
    public void notifyMessageChanged() {
        final List<BaseMessage> messages = messageSource.getUnPushedMessages();
        new Thread(new Runnable() {

            public void run() {
                for (BaseMessage message : messages) {
                    try {
                        messageQueue.put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "THREAD: SCAN MESSAGE SOURCE").start();
    }

    /**
     * 向发送线程池提交一个消息发送任务
     *
     * @param message 待发送的消息
     */
    private void submitSendingTask(final BaseMessage message) {
        threadPool.submit(new Runnable() {

            public void run() {
                boolean r = false;
                try {
                    r = MessageSenderPool.getMessageSender(message.getType()).sendMessage(message);
                } catch (NoSuchMsgTypeException e) {
                    e.printStackTrace();
                }
                if (r) {
                    System.out.println(message.getType().getTypeName() + message.getId() + " ：发送成功！");
                } else {
                    System.out.println(message.getType().getTypeName() + message.getId() + " ：发送失败！");
                    try {
                        messageQueue.put(message); // 发送失败了重新加入消息队列，继续尝试发送
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

//    public IMessageSource getMessageSource() {
//        return messageSource;
//    }

    public void setMessageSource(DemoMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static int getMaxMsgCountPerMin() {
        return maxMsgCountPerMin;
    }

    public static void setMaxMsgCountPerMin(int maxMsgCountPerMin) {
        MessageHandler.maxMsgCountPerMin = maxMsgCountPerMin;
    }
}

