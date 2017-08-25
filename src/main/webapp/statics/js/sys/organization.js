$(function () {
    loadPage();// 加载页面
});

// 加载页面
function loadPage() {
    $.ajax({
        url : '/organization/getOrganizations',
        type : 'GET',
        success : function (result) {
            // 加载机构信息
            build_organizaiton(result);
        }
    })
}