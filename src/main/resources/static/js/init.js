(function ($) {
  $(function () {

    $('.sidenav').sidenav();
    $('.parallax').parallax();

  }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(
    function(){
        $('.datepicker').datepicker({
            format: "yyyy-mm-dd"
        });
});