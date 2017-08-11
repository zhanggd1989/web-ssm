$.fn.addTabs = function (options) {

    //判断是否已存在指定ID的tab
    if ($("#" + options.id + '_li').length > 0) {
        $('li').removeClass('active');
        $('.tab-content').children().hide();
        // 激活当前tab页
        ($("#" + options.id + '_li')).addClass('active');
        ($("#" + options.id)).show();
        return;
    }

    //构建li元素
    var li = $("<li />", {
        "id": options.id + '_li',
    });

    var closeButton = $('<a />', {
        "href": "#",
        "text": 'x',
        "click": function () {
            // 删除标签和相关内容
            ($("#" + options.id + '_li')).remove();
            ($("#" + options.id)).hide();
        }
    })

    //构建a元素
    var a = $("<a />", {
        "id": options.id + "_a",
        "href": "#" + options.id,
        "text": options.title,
        "click": function () {
            // hide all other tabs 隐藏其他tab
            $('li').removeClass('active');
            $('.tab-content').children().hide();

            // show current tab 显示当前tab
            ($("#" + options.id + '_li')).addClass('active');
            ($("#" + options.id)).show();
        }
    });

    a.append(closeButton);
    //合并li和a元素
    li.append(a);

    var ul = $(this);
    //合并ul和li元素
    ul.append(li);

    //添加完成显示当前li
    $(li).tab("show");

    //构建div内容元素
    var div = $("<div />", {
        "id": options.id,
        "class": "tab-pane fade in active",
    });


    //兼容纯文本和html片段
    typeof options.content == "string" ? div.append(options.content) : div.html(options.content);

    var container = $(".tab-content");
    container.load(options.content);;

    //添加完成后显示div
    $(div).siblings().removeClass("active");
}