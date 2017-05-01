$('.signup-input').on('focus', function() {
  $('.signup').addClass('focused');
});

$('.signup').on('submit', function(e) {
  e.preventDefault();
  $('.signup').removeClass('focused').addClass('loading');
});
