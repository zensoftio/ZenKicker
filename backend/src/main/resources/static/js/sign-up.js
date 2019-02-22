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
    const $form = $('#sign-up-form');
    const $email = $form.find('input[name="email"]');
    const $fullName = $form.find('input[name="fullName"]');
    const $password = $form.find('input[name="password"]');
    const $confirmPassword = $form.find('input[name="confirmPassword"]');
    const $error = $form.find('.error');

    $form.submit(function (e) {
      e.preventDefault();

      if ($confirmPassword.val() === $password.val()) {
        $.ajax({
          url: '/api/players',
          type: 'POST',
          dataType: "json",
          contentType: "application/json; charset=utf-8",
          data: JSON.stringify({
            email: $email.val(),
            fullName: $fullName.val(),
            password: $password.val()
          }),
          success: function (e) {
            document.location = '/';
          },
          error: function (e) {
            const error = e.responseJSON;
            if (error.errors && error.errors.length) {
              $error.text(error.errors[0].field.charAt(0).toUpperCase() + error.errors[0].field.slice(1).toLowerCase() + ' ' + error.errors[0].message);
            } else if (error.message) {
              $error.text(error.message);
            }
          }
        });
      } else {
        $error.text('Passwords doesn\'t match');
      }
    });
  }());

});
