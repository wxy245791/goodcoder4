import com.baidu.service.DemoMessageSource;
import com.baidu.service.MessageHandler;

/**
 * Created by wangxiaoyu08 on 16/11/11.
 */


public class DemoClient {

    public static void main(String[] args) throws InterruptedException {
        DemoMessageSource messageSource = new DemoMessageSource();
        MessageHandler messageHandler = MessageHandler.newInstance(messageSource);
        messageHandler.start();

        // 消息源新增了数据，通知消息处理器
        messageSource.addNewMessages();
        messageHandler.notifyMessageChanged();

    }
}
