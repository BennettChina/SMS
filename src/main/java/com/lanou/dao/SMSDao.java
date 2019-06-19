package com.lanou.dao;

import com.lanou.bean.Message;
import com.lanou.bean.User;

import java.util.List;

/**
* @program: SMS消息系统
*
* @description: SMS消息系统的DAO接口
*
* @author: 吴蒙召
*
* @create: 2019-06-05 09:57
*
* @version: 1.0
**/

public interface SMSDao {

    /**
    * @Description: 添加用户
    * @Param: [user]
    * @Return: java.lang.Object
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    Object insert(User user);

    /**
    * @Description: 添加消息
    * @Param: [message]
    * @Return: java.lang.Object
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    Object insert(Message message);

    /**
    * @Description: 删除用户
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    int delete(User user);

    /**
    * @Description: 删除消息
    * @Param: [message]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    int delete(Message message);

    /**
    * @Description: 更新用户信息
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    int updateUser(User user);

    /**
    * @Description: 更新消息信息
    * @Param: [message]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    int updateMessage(Message message);

    /**
    * @Description: 查询所有的用户
    * @Param: []
    * @Return: java.util.List<com.lanou.bean.User>
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    List<User> searchUser();

    /**
    * @Description: 查询所有的消息
    * @Param: []
    * @Return: java.util.List<com.lanou.bean.Message>
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    List<Message> searchMessage();
}
