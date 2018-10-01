<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="favicon.ico">
    <title>Login</title>
    <link href="login.css" rel="stylesheet">
</head>
<body>

<section class="wrapper">
    <img class="logo" src='/images/logo-login.svg' alt="logo"/>
    <form id="login-form" method="POST" action="/login">
    <#if override_referrer??>
        <input type="hidden" name="overrideReferrer" value="${override_referrer}">
    </#if>
        <p class="error"></p>
        <div class="input_block">
            <img src="/images/login-user.svg" alt="login">
            <input name="username" type="text" placeholder="Username / E-mail">
            <img  class="delete hide" src="/images/login-delete.svg" alt="delete">
        </div>
        <div class="input_block">
            <img src="/images/login-password.svg" alt="password">
            <input name="password" type="password" placeholder="Password">
            <img class="delete hide" src="/images/login-delete.svg" alt="delete">
            <div class="eye eye_close"></div>
        </div>
        <div class="check_button_block">
            <div class="checkbox">
                <input name="rememberMe" type="checkbox" id="keep">
                <label for="keep"></label>
                <label for="keep">Keep me logged in</label>
            </div>
            <button>Log in</button>
        </div>
    </form>
</section>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="/js/login.js"></script>

</body>
</html>