<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico">
    <title>Login</title>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>

<section class="wrapper">
    <div class="title">Zen<span>Kicker</span></div>
    <form id="login-form" method="POST" action="/login">
    <#if override_referrer??>
        <input type="hidden" name="overrideReferrer" value="${override_referrer}">
    </#if>
        <p class="error"></p>
        <div class="input_block">
            <input name="username" type="text" placeholder="Username">
        </div>
        <div class="input_block">
            <input name="password" type="password" placeholder="Password">
            <div class="eye eye_close"></div>
        </div>
        <div class="check_button_block">
            <button>Log in</button>
        </div>
    </form>
    <a class="sign-up-link" href="#">Sign up</a>
</section>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="js/login.js"></script>

</body>
</html>