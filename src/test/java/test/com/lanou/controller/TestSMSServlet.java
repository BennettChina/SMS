package test.com.lanou.controller;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.Message;
import com.lanou.bean.User;
import com.lanou.service.SMSService;
import com.lanou.service.serviceimpl.SMSServiceImpl;
import org.junit.Test;

import java.util.List;

/**
* @program: SMS消息系统
*
* @description: 测试servlet
*
* @author: 吴蒙召
*
* @create: 2019-06-10 16:33
*
* @version: 1.0
**/

public class TestSMSServlet {

    @Test
    public void testGetMessage(){
        User user = new User();
        SMSService SMSService = new SMSServiceImpl();
        user.setId(2);
        List<Message> messageList = SMSService.searchMessage(user);
        String data = JSON.toJSONString(messageList,true);
        System.out.println(data);
    }

}
