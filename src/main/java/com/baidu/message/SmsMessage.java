package com.baidu.message;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */

public class SmsMessage extends BaseMessage {

    private String fromPhone;
    private String toPhone;

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }
}
