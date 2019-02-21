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
    const $email = $form.find('input[name="email"]');
    const $password = $form.find('input[name="password"]');
    const $error = $form.find('.error');

    $form.submit(function (e) {
      e.preventDefault();
      $.ajax({
        url: '/login',
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded',
        data: {
          email: $email.val(),
          password: $password.val(),
        },
        success: function (e) {
          document.location = '/';
        },
        error: function (e) {
          const error = e.responseJSON
          if (error.errors && error.errors.length) {
            $error.text(error.errors[0].field.charAt(0).toUpperCase() + error.errors[0].field.slice(1).toLowerCase() + ' ' + error.errors[0].message);
          } else if (error.message) {
            $error.text(error.message);
          }
        }
      });
    });
  }());

});
