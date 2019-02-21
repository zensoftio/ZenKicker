<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="shortcut icon" href="favicon.ico">
  <title>Sign Up</title>
  <link href="css/login.css" rel="stylesheet">
</head>
<body>
    <section class="wrapper">
      <div class="logo">Zen<span>Kicker</span></div>
      <div class="title">Sign up</div>
      <form id="sign-up-form" method="POST">
        <#if override_referrer??>
            <input type="hidden" name="overrideReferrer" value="${override_referrer}">
        </#if>
        <p class="error"></p>
        <div class="input_block">
          <input name="email" type="text" placeholder="Email">
        </div>
        <div class="input_block">
          <input name="fullName" type="text" placeholder="Full name">
        </div>
        <div class="input_block">
          <input name="password" type="password" placeholder="Password">
          <div class="eye eye_close"></div>
        </div>
        <div class="input_block">
          <input name="confirmPassword" type="password" placeholder="Confirm password">
          <div class="eye eye_close"></div>
        </div>
        <div class="check_button_block">
          <button>Just do it!</button>
        </div>
      </form>
      <a class="sign-up-link" href="/login">Sign in</a>
    </section>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="js/sign-up.js"></script>

</body>
</html>