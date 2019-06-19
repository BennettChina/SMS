package com.lanou.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 用户表实体类
 */

@Getter
@Setter
public class User {

    public static final int STATUS_NORMAL = 1;// 正常状态
    public static final int STATUS_LOGOUT = 2;// 锁定状态
    public static final int STATUS_LOCKED = 3;// 注销状态

    private Integer id;// 定义用户表id
    private String username;// 定义用户名
    private String account;// 定义账号
    private String password;// 定义密码
    private String email;// 定义用户邮箱
    private Integer status;// 定义用户的状态
    private Timestamp createTime;// 定义用户创建时间
    private Timestamp lastLoginTime;// 定义用户最后登录时间
}
