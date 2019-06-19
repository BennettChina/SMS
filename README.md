## SMS(Short Message System)
`一个短消息系统，类似于邮箱`

本系统完全采用原生的servlet来实现功能，没有使用现有的框架。由于系统仅是本人练习所作，所以界面也较为简陋，功能也不是很多，只有一个邮箱基本的功能。

系统采用的是MySQL数据库，由于未上传本系统的数据库，因此在这里给出数据库中用到的两张表的结构
### 用户表（user）
| 字段名          | 类型         | 说明                                      |
| --------------- | ------------ | ----------------------------------------- |
| id              | int          | 自增、主键id                              |
| username        | varchar(255) | 用户名                                    |
| account         | varchar(255) | 用户账号                                  |
| password        | varchar(255) | 密码                                      |
| email           | varchar(255) | 邮箱                                      |
| status          | int          | 用户状态： 正常：1;    注销：2；  锁定：3 |
| createtime      | timestamp    | 注册时间                                  |
| last_login_time | timestamp    | 最后登录时间                              |

### 消息表（message）
| 字段名     | 类型          | 说明                                  |
| ---------- | ------------- | ------------------------------------- |
| id         | int           | 自增，主键id                          |
| from_id    | int           | 发送人id。 引用用户表id               |
| to_id      | int           | 接收人id。 引用用户表id               |
| subject    | varchar(255)  | 消息主题                              |
| content    | varchar(2000) | 消息内容                              |
| createtime | timestamp     | 发送时间                              |
| status     | int           | 消息状态： 已读：1； 未读：2；删除：3 |
| attachment | varchar(1000) | 附件文件地址                          |
