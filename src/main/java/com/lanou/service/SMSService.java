package com.lanou.service;

import com.lanou.bean.Message;
import com.lanou.bean.User;

import java.util.List;

public interface SMSService {

    /**
    * @Description: 添加用户
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    Object insert(User user);

    /**
    * @Description: 新增消息
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
    * @Date: 2019/6/5
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
    * @Description: 更新用户
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    int update(User user);

    /**
    * @Description: 更新消息
    * @Param: [message]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    int update(Message message);

    /**
    * @Description: 查询用户信息(登录)
    * @Param: [user]
    * @Return: java.util.List<User>
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    List<User> search(User user);

    /**
    * @Description: 根据id查询一条用户信息
    * @Param: [user]
    * @Return: java.util.List<com.lanou.bean.User>
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    List<User> searchUserById(User user);

    /**
    * @Description: 查询发信人的名字
    * @Param: [message]
    * @Return: com.lanou.bean.User
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    User searchUsername(Message message);

    /**
    * @Description: 查找用户的所有的消息
    * @Param: [user]
    * @Return: java.util.List<com.lanou.bean.Message>
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    List<Message> searchMessage(User user);

    /**
    * @Description: 查询所有用户（除当前用户）
    * @Param: []
    * @Return: java.util.List<com.lanou.bean.User>
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    List<User> searchAll(User user);

    /**
    * @Description: 查询某一条消息
    * @Param: [message]
    * @Return: com.lanou.bean.Message
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    Message searchMessage(Message message);
}
