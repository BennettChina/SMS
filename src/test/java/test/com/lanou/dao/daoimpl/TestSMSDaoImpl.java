package test.com.lanou.dao.daoimpl;

import com.lanou.bean.User;
import com.lanou.dao.daoimpl.SMSDaoImpl;
import org.junit.Before;
import org.junit.Test;

/**
* @program: SMS消息系统
*
* @description: Dao的测试类
*
* @author: 吴蒙召
*
* @create: 2019-06-05 11:21
*
* @version: 1.0
**/

public class TestSMSDaoImpl {

    SMSDaoImpl smsDao = null;

    @Before
    public void setUp(){
        smsDao = new SMSDaoImpl();
    }

    /*@Test
    public void testSearchUser(){
        List<User> list = smsDao.searchUser();
        Assert.assertNotNull("查询结果不应该为空",list);
        Assert.assertEquals("查询结果集应该一条数据也没有",0, list.size());
    }*/

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("周总");
        user.setAccount("master zhou");
        user.setPassword("123456");
        user.setStatus(User.STATUS_NORMAL);


    }
}
