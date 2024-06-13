$(document).ready(function() {
/*    $('#password, #confirmPassword').on('keyup', function() {
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();
        if (password === confirmPassword) {
            $('#message').html('Passwords match').css('color', 'green');
        } else {
            $('#message').html('Passwords do not match').css('color', 'red');
        }
    });*/

    $('#registrationForm').on('submit', function(event) {
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();
		if (password !== confirmPassword) {
			event.preventDefault(); // Ngăn biểu mẫu gửi đi
            $('#message').html('Passwords do not match ').css('color', 'red');
            alert('Passwords do not match . Please try again.');
		};
    });
});