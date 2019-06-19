package com.lanou.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
* @program: SMS消息系统
*
* @description: 操作数据的工具类
*
* @author: 吴蒙召
*
* @create: 2019-06-05 10:20
*
* @version: 1.0
**/

public class DBTools {

    private static DataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        ((DruidDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/sms?characterEncoding=utf8");
        ((DruidDataSource) dataSource).setUsername("root");
        ((DruidDataSource) dataSource).setPassword("root");
        ((DruidDataSource) dataSource).setMaxActive(10);
        ((DruidDataSource) dataSource).setMinIdle(1);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    /*public static<T> int insert(String sql,Class<T> clazz, Object ... params){
        QueryRunner runner = new QueryRunner(getDataSource());
        ArrayHandler rsh = new ArrayHandler();
        for(int i = 0;i < params.length;i++){
//            params.
        }

        return 0;
    }

    *//**
    * @Description: 通用查询数据
    * @Param: [sql, clazz, params]
    * @Return: java.util.List<T>
    * @Author: 吴蒙召
    * @Date: 2019/6/5
    **//*
    public static<T> List<T> search(String sql, Class<T> clazz, Object ... params) {

        QueryRunner runner = new QueryRunner(getDataSource());
        ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(clazz);
        List<T> result = new ArrayList<>();
        try {
            result = runner.query(sql, rsh, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }*/
}
