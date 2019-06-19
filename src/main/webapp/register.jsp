<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCUMENT HTML>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="static/css/register.css">
</head>
<body>

<div class="register_warp">
    <div class="top">
        <img src="static/images/sms_reg_title.png">
    </div>

    <div class="content">
        <form action="register" method="post">
            <input type="hidden" name="action" value="register">
            <div>
                <label for="username">用户名&nbsp;:</label>
                <input id="username" name="username" required>
            </div>
            <div>
                <label for="username">账号&nbsp;:</label>
                <input id="account" name="account" required>
            </div>
            <div>
                <label for="password">密码&nbsp;:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div>
                <label for="rel_password">确认密码&nbsp;:</label>
                <input type="password" id="rel_password" name="rel_password" required>
            </div>
            <div>
                <label for="email">邮箱&nbsp;:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="submit_btn">
                <input type="image" src="static/images/sms_btn_reg.png">
                <button type="reset"></button>
            </div>
        </form>

        <div style="text-align: center">
            <a href="main.jsp"><img src="static/images/sms_btn_goback.png"></a>
        </div>
    </div>
</div>

</body>
</html>
