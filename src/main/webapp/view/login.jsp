<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>用户登录</title>
    <jsp:include page="/view/inc.jsp"></jsp:include>
    <script src="./../statics/js/login/login.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/login/login.css">
</head>

<body>
    <div class="login">
        <div class="box png">
            <div class="logo png"></div>
            <div class="input">
                <div class="log">
                    <form id="loginForm">
                        <div class="name">
                            <label>用户名</label><input type="text" class="text" placeholder="用户名" name="userName" tabindex="1">
                        </div>
                        <div class="pwd">
                            <label>密 码</label><input type="password" class="text" placeholder="密码" name="password" tabindex="2">
                        </div>
                        <div>
                            <label><input name="rememberMe" type="checkbox" value="1" /></label>记住我

                        </div>
                        <div>
                            <input type="submit" class="button" tabindex="3" value="登录">
                            <input type="reset" class="button" tabindex="4" value="重置">
                        </div>
                    </form>
                </div>
                <h2 style="text-align: center">${message}</h2>
            </div>
        </div>
        <div class="air-balloon ab-1 png"></div>
        <div class="air-balloon ab-2 png"></div>
        <div class="footer"></div>
    </div>

</body>
</html>