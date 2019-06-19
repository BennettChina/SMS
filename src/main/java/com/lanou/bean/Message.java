package com.lanou.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 消息表实体类
 */

@Getter
@Setter
public class Message {

    public static final int STATUS_READ = 1;// 消息已读
    public static final int STATUS_UNREAD = 2;// 消息未读
    public static final int STATUS_DELETED = 3;// 消息已删除

    private Integer id;// 定义消息表id
    private Integer fromId;// 定义发送人id
    private Integer toId;// 定义接收人id
    private String subject;// 定义消息主题
    private String content;// 定义消息内容
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;// 定义消息发送时间
    private Integer status;// 定义消息状态
    private String attachment;// 定义附件文件地址
}
