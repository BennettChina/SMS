package com.lanou.dao.daoimpl;

import com.lanou.bean.Message;
import com.lanou.bean.User;
import com.lanou.dao.SMSDao;
import com.lanou.utils.DBTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
* @program: SMS消息系统
*
* @description: SMSDao接口的实现类
*
* @author: 吴蒙召
*
* @create: 2019-06-05 10:00
*
* @version: 1.0
**/

public class SMSDaoImpl implements SMSDao {

    /**
    * @Description: 添加用户信息
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **/
    @Override
    public Object insert(User user) {
        QueryRunner runner = new QueryRunner(DBTools.getDataSource());
        ArrayHandler rsh = new ArrayHandler();
        Object[] params = new Object[]{
            user.getUsername(),
            user.getAccount(),
            user.getPassword(),
            user.getEmail(),
            user.getStatus(),
            user.getCreateTime(),
            user.getLastLoginTime()
        };
        String sql = "insert into user (username, account, password, email, status," +
                " createtime, last_login_time) values (?,?,?,?,?,?,?)";
        try {
            Object[] result = runner.insert(sql,rsh,params);
            return result[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: 添加消息
    * @Param: [message]
    * @Return: java.lang.Object
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public Object insert(Message message) {
        QueryRunner runner = new QueryRunner(DBTools.getDataSource());
        ArrayHandler rsh = new ArrayHandler();
        String sql = "insert into message (from_id,to_id,subject,content,createtime," +
                "status,attachment) values (?,?,?,?,?,?,?)";
        Object[] params = new Object[]{
            message.getFromId(),
                message.getToId(),
                message.getSubject(),
                message.getContent(),
                message.getCreateTime(),
                message.getStatus(),
                message.getAttachment()
        };
        try {
            return runner.insert(sql,rsh,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: 删除用户
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
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
        QueryRunner runner = new QueryRunner(DBTools.getDataSource());
        String sql = "delete from message where id = ?";
        Object[] params = new Object[]{
                message.getId()
        };
        try {
            return runner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
    * @Description: 更新用户的最后登录时间
    * @Param: [user]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/7
    **/
    @Override
    public int updateUser(User user) {
        QueryRunner runner = new QueryRunner(DBTools.getDataSource());
        String sql = "update user set last_login_time = ? where id = ?";
        Object[] params = new Object[]{
                user.getLastLoginTime(),
                user.getId()
        };
        try {
            return runner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
    * @Description: 更新消息状态
    * @Param: [message]
    * @Return: int
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public int updateMessage(Message message) {
        QueryRunner runner = new QueryRunner(DBTools.getDataSource());
        String sql = "update message set status = ? where id = ?";
        Object[] params = new Object[]{
                message.getStatus(),
                message.getId()
        };
        try {
            return runner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
    * @Description: 查询所有的用户信息
    * @Param: [user]
    * @Return: java.util.List<com.lanou.bean.User>
    * @Author: 吴蒙召
    * @Date: 2019/6/7
    **/
    @Override
    public List<User> searchUser() {
        DataSource ds = DBTools.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
        List<User> list = new ArrayList<>();
        String sql = "select id,username,account,password,email,status," +
                "createtime createTime,last_login_time lastLoginTime from user";
        try {
            list = runner.query(sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
    * @Description: 查找所有的消息
    * @Param: []
    * @Return: java.util.List<com.lanou.bean.Message>
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    @Override
    public List<Message> searchMessage() {
        DataSource ds = DBTools.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
        List<Message> list = new ArrayList<>();
        String sql = "select id,from_id fromId,to_id toId,subject,content," +
                "createtime createTime,status,attachment from message";
        try {
            list = runner.query(sql,new BeanListHandler<>(Message.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
