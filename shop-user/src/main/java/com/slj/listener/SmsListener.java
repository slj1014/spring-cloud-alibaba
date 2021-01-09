package com.slj.listener;

import com.alibaba.fastjson.JSON;
import com.slj.dao.UserDao;
import com.slj.domain.Order;
import com.slj.domain.User;
import com.slj.utils.SmsUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * 消息服务监听器
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "shop-user", topic = "order-topic"
      //  consumeMode = ConsumeMode.CONCURRENTLY,//消费模式,指定是否顺序消费 CONCURRENTLY(同步,默认) ORDERLY(顺序)
       // messageModel = MessageModel.CLUSTERING//消息模式 BROADCASTING(广播)  CLUSTERING(集群,默认)
)
public class SmsListener implements RocketMQListener<Order> {
    @Autowired
    private UserDao userDao;
    @Override
    public void onMessage(Order order) {
        //根据uid 获取手机号
        User user = userDao.findById(order.getUid()).get();
        //生成验证码
        StringBuilder builder = new StringBuilder();   for (int i = 0; i < 6; i++) {
            builder.append(new Random().nextInt(9) + 1);
        }
        String smsCode = builder.toString();

        Param param = new Param(smsCode);
        try {
            //发送短信 {"code":"123456"}
            SmsUtil.sendSms(user.getTelephone(), "黑马旅游网", "SMS_170836451", JSON.toJSONString(param));
            log.info("短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AllArgsConstructor
    @NoArgsConstructor
    class Param {
        private String code;
    }

}
