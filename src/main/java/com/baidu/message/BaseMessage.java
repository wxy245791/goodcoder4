package com.baidu.message;

import com.sun.jmx.snmp.Timestamp;
import com.baidu.enumeration.MessageType;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */

public abstract class BaseMessage {

    protected int id;
    protected MessageType type;
    protected Timestamp createTime;
    protected String content;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


