$(function () {
    $(".panel-heading").click(function (e) {
        /*切换折叠指示图标*/
        $(this).find("span").toggleClass("glyphicon-chevron-down");
        $(this).find("span").toggleClass("glyphicon-chevron-up");
    });

    $(".leftMenu a").click(function () {
        var title = $(this).children('span').html();
        var url = $(this).attr('rel');
        $.ajax({
            type: "GET",
            url: url,
            success: function (data) {
                $('#myTab').addTabs({
                    "id": "tab" + $(this).attr('id'),
                    "title": title,
                    "content": data
                });
            }
        });
    });

    $("#logout").click(function () {
        window.location.href = '/logout';
    });
})