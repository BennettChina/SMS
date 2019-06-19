<%--
  Created by IntelliJ IDEA.
  User: 召
  Date: 2019/6/8
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>发送短消息</title>
    <link rel="stylesheet" type="text/css" href="static/css/sendMessage.css">
</head>
<body>
<img src="static/images/sms_send_message.png">
    <form action="sendMessage" method="post" class="wrap" enctype="multipart/form-data">
        <input type="hidden" value="sendMessage" name="action">
        <div class="top">
            <label for="user">收件人&nbsp;:&nbsp;</label>
            <select id="user" name="to_id">
                <c:forEach var="us" items="${userList}">
                    <option value="${us.id}">${us.username}</option>
                </c:forEach>
            </select>
            <label for="subject">标题&nbsp;:&nbsp;</label>
            <input id="subject" type="text" name="subject">
            <label for="attachment">附件:</label>
            <input id="attachment" type="file" name="attachment">
        </div>
        <hr class="line" color="white">
        <div style="text-align: center">
        <textarea class="content" name="content">
        </textarea>
        </div>
        <div class="footer">
            <input type="image" src="static/images/sms_send.gif">
        </div>
    </form>
</body>
</html>
