<%--
  Created by IntelliJ IDEA.
  User: 召
  Date: 2019/6/10
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>阅读短消息</title>
    <link rel="stylesheet" type="text/css" href="static/css/sendMessage.css">
</head>
<body>
<img src="static/images/sms_read_message.png">
<form action="sendMessage" method="post" class="wrap" enctype="multipart/form-data">
    <input type="hidden" value="sendMessage" name="action">
    <div class="top">
        <label for="user">发件人&nbsp;:&nbsp;</label>
        <span id="user">${user.username}</span>
        <label for="subject">标题&nbsp;:&nbsp;</label>
        <span id="subject">${message.subject}</span>
        <label for="attachment">附件:</label>
        <span id="attachment">
            <a href="${message.attachment}">${fileName}</a>
        </span>
    </div>
    <hr class="line" color="white">
    <div style="text-align: center">
        <textarea class="content" name="content">
            ${message.content}
        </textarea>
    </div>
    <div class="footer">
        <a href="goSendMessage?action=reply&id=${message.id}" class="font">回信</a>
    </div>
</form>
</body>
</html>
