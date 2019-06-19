<%--
  Created by IntelliJ IDEA.
  User: 召
  Date: 2019/6/4
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="static/css/login.css">
</head>
<body>

<div class="register_warp">
    <div class="top">
        <img src="static/images/sms_login_title.png">
    </div>

    <div class="content">
        <form action="login" method="post">
            <input type="hidden" name="action" value="login">
            <div>
                <label for="username">用户名&nbsp;:</label>
                <input id="username" name="username" required >
            </div>
            <div>
                <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="submit_btn">
                <input type="image" src="static/images/sms_btn_login.png">
                <a href="register.jsp"><img alt="注册" src="static/images/sms_btn_reg.png"></a>
            </div>
        </form>
    </div>
</div>

<!-- JavaScript代码 -->
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/js-cookie/latest/js.cookie.min.js"></script>

<script>
    $('#username').blur(function () {
        // 获取cookie
       var cookie = Cookies.get();
       // 获取用户名
       var username = $("#username").val();
       // 获取cookie中用户名对应的密码（如果有的话）
       var cPassword = cookie[username];
       // 若cPassword不空为真
       if (cPassword) {
           // 将cookie中的密码设置到密码框内
           $("#password").val(cPassword);
       }
    });
</script>
</body>
</html>
