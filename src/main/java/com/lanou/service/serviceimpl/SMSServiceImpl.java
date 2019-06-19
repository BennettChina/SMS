package com.lanou.service.serviceimpl;

import com.lanou.bean.Message;
import com.lanou.bean.User;
import com.lanou.dao.SMSDao;
import com.lanou.dao.daoimpl.SMSDaoImpl;
import com.lanou.service.SMSService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* @program: SMS消息系统
*
* @description: SMS项目的业务逻辑实现类
*
* @author: 吴蒙召
*
* @create: 2019-06-05 09:41
*
* @version: 1.0
**/

public class SMSServiceImpl implements SMSService {

    private static SMSDao smsDao;
    static {
        smsDao = new SMSDaoImpl();
    }

    /**
    * @Description: 添加用户
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    @Override
    public Object insert(User user) {
        return smsDao.insert(user);
    }

    /**
    * @Description: 新增消息
    * @Param: [message]
    * @Return: java.lang.Object
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public Object insert(Message message) {
        return smsDao.insert(message);
    }

    /**
    * @Description: 删除用户
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    @Override
    public int delete(User user) {
        return 0;
    }

    /**
    * @Description: 删除消息
    * @Param: [message]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public int delete(Message message) {
        // 调用dao层的删除方法
       return smsDao.delete(message);
    }

    /**
    * @Description: 更新用户
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    @Override
    public int update(User user) {
        // 调用DAO层的更新方法
        return smsDao.updateUser(user);
    }

    /**
    * @Description: 更新消息
    * @Param: [message]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public int update(Message message) {
        return smsDao.updateMessage(message);
    }

    /**
    * @Description: 查找所有的用户(除当前用户)
    * @Param: []
    * @Return: java.util.List<com.lanou.bean.User>
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public List<User> searchAll(User user) {
        List<User> userList = smsDao.searchUser();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()){
            User us = iterator.next();
            if (us.getId() == user.getId()){
                iterator.remove();
            }
        }
        return userList;
    }

    /**
    * @Description: 查询用户信息（登录）
    * @Param: [user]
    * @Return: java.util.List<User>
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    @Override
    public List search(User user) {
        // 获取所有的用户信息
        List<User> list = smsDao.searchUser();
        // 从所有的用户中找到目标用户
        for (User us : list) {
            // 根据用户登录输入的信息作为条件判断该用户的信息是否正确
            if (us.getAccount().equals(user.getAccount()) && us.getPassword().equals(user.getPassword())) {
                // 清空已有的用户信息
                list.clear();
                // 将目标用户的信息存入并返回
                list.add(us);
                return list;
            }
        }
        return null;
    }

    /**
    * @Description: 根据id查找一条用户信息
    * @Param: [user]
    * @Return: java.util.List<com.lanou.bean.User>
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    @Override
    public List<User> searchUserById(User user) {
        // 获取所有的用户信息
        List<User> userList = smsDao.searchUser();
        // 根据id拿出对应的用户信息
        List<User> list = new ArrayList<>();
        for(User us : userList){
            if (us.getId() == user.getId()){
                list.add(us);
            }
        }
        return list;
    }

    /**
    * @Description: 查找当前用户的消息
    * @Param: [user]
    * @Return: java.util.List<com.lanou.bean.Message>
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public List<Message> searchMessage(User user) {
        // 获取所有的消息
        List<Message> list = smsDao.searchMessage();
        List<Message> result = new ArrayList<>();
        // 找出目标用户的消息
        for (Message message : list){
            // 判断该条消息是否是用户的消息
            if (message.getToId().equals(user.getId())){
                result.add(message);
            }
        }
        return result;
    }

    /**
    * @Description: 查询某一条消息
    * @Param: [message]
    * @Return: com.lanou.bean.Message
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    @Override
    public Message searchMessage(Message message) {
        List<Message> messageList = smsDao.searchMessage();
        for (Message msg: messageList) {
            if (message.getId() == msg.getId()){
                return msg;
            }
        }
        return null;
    }

    /**
    * @Description: 查找发信人的名字
    * @Param: [message]
    * @Return: com.lanou.bean.User
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    @Override
    public User searchUsername(Message message) {
        // 找出所有的用户
        List<User> userList = smsDao.searchUser();
        for (User user : userList){
            // 若用户的id与发信id相同则返回这个用户
            if (user.getId() == message.getFromId()){
                return user;
            }
        }
        return null;
    }
}
