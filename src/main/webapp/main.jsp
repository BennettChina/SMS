<%--
  Created by IntelliJ IDEA.
  User: 召
  Date: 2019/6/6
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>

    <link rel="stylesheet" type="text/css" href="static/css/main.css">
</head>
<body>
<div class="top">
    <img src="static/images/sms_my_message.png">
</div>
<div class="middle">
    <span>当前用户&nbsp;:&nbsp;</span>
    <span style="color: red">${user.username}</span>
    <span class="font"><a href="goSendMessage?action=goSendMessage">发短消息</a></span>
    <span class="font"><a href="exit?action=exit">退出</a></span>
</div>
<div class="footer">
    <ul>
        <c:forEach var="message" items="${messageList}">
            <li>
                <c:if test="${message.status == 1}">
                    <img src="static/images/sms_readed.png">
                    <span><a href="readMessage?action=readMessage&id=${message.id}">${message.subject}</a></span>
                    <div class="pos">
                        <span><a href="deleteMessage?action=deleteMessage&id=${message.id}"
                                 onclick="return tips();">删除</a></span>
                        <span><a href="goSendMessage?action=reply&id=${message.id}">回信</a></span>
                        <span>${message.createTime}</span>
                    </div>
                </c:if>
                <c:if test="${message.status == 2}">
                    <img src="static/images/sms_unReaded.png">
                    <span style="font-weight: bold">
                        <a href="readMessage?action=readMessage&id=${message.id}">${message.subject}</a>
                    </span>
                    <div class="pos">
                        <span><a href="deleteMessage?action=deleteMessage&id=${message.id}"
                                 onclick="return tips();">删除</a></span>
                        <span><a href="goSendMessage?action=reply&id=${message.id}">回信</a></span>
                        <span>${message.createTime}</span>
                    </div>
                </c:if>
                <c:if test="${message.status == 3}">
                </c:if>
            </li>
        </c:forEach>
    </ul>
</div>

<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

    $(function () {

        // // 让数据只要进来就刷新
        // getData();

        // 删除提示
        function tips() {
            if (confirm("确定要删除吗？")) {
                return true;
            } else {
                return false;
            }
        }

        // Ajax获取数据
        function getData() {
            $.getJSON("getMessage", {action: "getMessage"}, function (data) {
                    $('.footer ul').empty();
                    // console.log(data);
                    for (var i = 0; i < data.length;i++){
                        if (data[i].status == 1) {
                            $('.footer ul').append('<li><img src="static/images/sms_readed.png">' +
                                '<span><a href="readMessage?action=readMessage&id='+data[i].id+'">'+data[i].subject+'</a></span>' +
                                '<div class="pos"><span><a href="deleteMessage?action=deleteMessage&id='+data[i].id+'" onclick="return tips();">删除</a></span>' +
                                '<span><a href="goSendMessage?action=reply&id='+data[i].id+'">回信</a></span>\n' +
                                '<span>'+data[i].createTime+'</span></div></li>');
                        }
                        if (data[i].status == 2) {
                            $('.footer ul').append('<li><img src="static/images/sms_unReaded.png">' +
                                '<span style="font-weight: bold"><a href="readMessage?action=readMessage&id='+data[i].id+'">'+data[i].subject+'</a></span>' +
                                '<div class="pos"><span><a href="deleteMessage?action=deleteMessage&id='+data[i].id+'" onclick="return tips();">删除</a></span>' +
                                '<span><a href="goSendMessage?action=reply&id='+data[i].id+'">回信</a></span>\n' +
                                '<span>'+data[i].createTime+'</span></div></li>');
                        }
                        if (data[i].status == 3){}
                    }
                }
            )

        }

        // 定时异步刷新网页
        window.setInterval("getData()", 60000);
    })
</script>
    <!-- 返回页面时 重新加载一次 -->
    <%--<script type="text/javascript">
        if(window.name != "bencalie"){
            location.reload();
            window.name = "bencalie";
        }else{
            window.name = "";
        }
    </script>--%>
</body>
</html>
