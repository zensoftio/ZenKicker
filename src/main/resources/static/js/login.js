$(document).ready(function () {

  $('.input_block').each(function (index, inputBlock) {
    const $inputBlock = $(inputBlock);
    const $input = $inputBlock.find('input');
    const $clear = $inputBlock.find('.delete');

    $input.on('keyup', function () {
      if ($input.val()) {
        $clear.removeClass('hide');
      } else {
        $clear.addClass('hide');
      }
    });

    $clear.on('click', function () {
      $input.val('');
      $clear.addClass('hide');
    });
  });

  $('.input_block .eye').each(function (index, passwordTrigger) {
    const $passwordTrigger = $(passwordTrigger);
    const $passwordInput = $passwordTrigger.siblings('input[type="password"]');

    $passwordTrigger.on('click', function () {
      if ($passwordTrigger.hasClass('eye_close')) {
        $passwordTrigger.removeClass('eye_close');
        $passwordTrigger.addClass('eye_open');
        $passwordInput.attr('type', 'text');
      } else {
        $passwordTrigger.removeClass('eye_open');
        $passwordTrigger.addClass('eye_close');
        $passwordInput.attr('type', 'password');
      }
    });
  });

  (function () {
    const $form = $('#login-form');
    const $userName = $form.find('input[name="username"]');
    const $password = $form.find('input[name="password"]');
    const $rememberMe = $form.find('input[name="rememberMe"]');
    const $error = $form.find('.error');

    $form.submit(function (e) {
      e.preventDefault();

      $.ajax({
        url: '/login',
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded',
        data: {
          username: $userName.val(),
          password: $password.val(),
          rememberMe: $rememberMe.prop('checked')
        },
        success: function (e) {
          document.location = '/';
        },
        error: function (e) {
          const error = e.responseJSON
          $userName.addClass('input_error');
          $password.addClass('input_error');
          if (e.responseJSON.message) {
            $error.text('Oops! ' + error.message);
          } else {
            $error.text('Oops! ' + error[0].fieldName.charAt(0).toUpperCase() + error[0].fieldName.slice(1).toLowerCase() + ' ' + error[0].errorMessage);
          }
        }
      });
    });
  }());

});
