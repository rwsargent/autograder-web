define(
    [],
    function(header) {
        return function() {
            //Capture Ctrl-Space for switching between password and text input type
            $(document).keydown(function (event) {
                if (event.keyCode == 32 && event.ctrlKey) {
                    event.preventDefault();
                    toggleHiddenTextView();
                }
            });
            var $progress = $('.progress');
            var $message = $('#message-anchor');

            // helper method for above
            var toggleHiddenTextView = function () {
                var $inputField = $("#password");
                if ($inputField.attr("type") != "password")
                {
                    $inputField.attr("type", "password");
                } else {
                    $inputField.attr("type", "text");
                }
            };
            

            var $submitButton = $("#submit");
            $submitButton.click(function() {
                var input = document.getElementById('password');
                var url = '/labs/lab' + $submitButton.attr("lab");
                // start preloader
                $progress.removeClass('hide');
                $.post(url, { submission: input.value }, function (data) {
                    var response = data;
                    $('#score').html(response.score);
                    if (response.message) {
                        $message.html(response.message);
                    }
                    if (response.success) {
                        if (response.complete) {
                            var $scoreBox = $(".score-box");
                            $scoreBox.addClass("done");
                        }
                        input.value = '';
                        input.classList.remove('invalid');
                        input.classList.add('valid');
                        $("#password").attr("type", "text");
                    } else {
                        input.classList.remove('valid');
                        input.classList.add('invalid');
                    }
                }).fail(function () {
                    $message.html("Woops! Server had a hiccup");
                }).always(function () {
                    $progress.addClass('hide');
                });
            });
        };
    }
);
