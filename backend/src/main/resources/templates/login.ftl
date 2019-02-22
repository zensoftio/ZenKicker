<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="shortcut icon" href="favicon.ico">
  <title>Login</title>
  <link href="css/login.css" rel="stylesheet">
</head>
<body>

<section class="wrapper">
  <div class="logo">Zen<span>Kicker</span></div>
  <div class="title">Sign in</div>
  <form id="login-form" method="POST">
    <#if override_referrer??>
        <input type="hidden" name="overrideReferrer" value="${override_referrer}">
    </#if>
    <p class="error"></p>
    <div class="input_block">
      <input name="email" type="text" placeholder="Email">
    </div>
    <div class="input_block">
      <input name="password" type="password" placeholder="Password">
      <div class="eye eye_close"></div>
    </div>
    <div class="check_button_block">
      <button>Let's go!</button>
    </div>
    <a class="sign-up-link" href="/sign-up">Sign up</a>
  </form>
</section>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="js/login.js"></script>

</body>
</html>