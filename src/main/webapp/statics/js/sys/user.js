var totalRecord;
var id;
// 初始化
$(function () {
    loadPage(1);// 加载页面
    // $(function () {
    //     $("#myform").validate();
    // });
});

// 加载页面
function loadPage(page) {
    $.ajax({
        url: "/user/getUsers",
        data: "page=" + page,
        type: "GET",
        success: function (result) {
            // 加载用户信息
            build_user(result);
            // 加载条数信息
            build_page_info(result);
            // 加载分页信息
            build_page_nav(result);
        }
    })
};

// 加载用户信息
function build_user(result) {
    $("#userTable tbody").empty();
    var users = result.extend.userPage.list;
    $.each(users, function (index, item) {
        var loginName = $("<td></td>").append(item.loginName);
        var realName = $("<td></td>").append(item.realName);
        var gender = $("<td></td>").append(item.gender == 0 ? '男' : '女');
        var email = $("<td></td>").append(item.email);
        var phone = $("<td></td>").append(item.phone);
        var type = $("<td></td>").append(item.type == 0 ? '管理员' : '普通用户');
        var status = $("<td></td>").append(item.status == 0 ? '启用' : '停用');
        var orgName = $("<td></td>").append(item.orgName);
        var description = $("<td></td>").append(item.description);
        var editUserBtn = $("<button></button>").addClass("btn btn-primary btn-xs editUser-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
            .append("编辑");
        editUserBtn.attr("editUser-id", item.id);
        var deleteUserBtn = $("<button></button>").addClass("btn btn btn-danger btn-xs deleteUser-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
            .append("删除");
        deleteUserBtn.attr("deleteUser-id", item.id);
        var grantRoleBtn = $("<button></button>").addClass("btn btn-info btn-xs grantRole-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-tags"))
            .append("授予角色");
        grantRoleBtn.attr('grantRole-id', item.id);
        var btn = $("<td></td>").append(editUserBtn).append("&nbsp;").append(deleteUserBtn).append("&nbsp;").append(grantRoleBtn);
        $("<tr></tr>").append(loginName).append(realName).append(gender).append(email).append(phone).append(type).append(status).append(orgName).append(description).append(btn)
            .appendTo("#userTable tbody");
    })
}

// 加载条数信息
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前" + result.extend.userPage.pageNum + "页,")
        .append("总共" + result.extend.userPage.pages + "页，")
        .append("总" + result.extend.userPage.total + "条记录");
    totalRecord = result.extend.userPage.total;
}

// 加载分页信息
function build_page_nav(result) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    //首页+上一页
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.userPage.hasPrevisousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            loadPage(1);
        });
        prePageLi.click(function () {
            loadPage(result.extend.userPage.pageNum - 1);
        });
    }
    //下一页+末页
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.extend.userPage.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            loadPage(result.extend.userPage.pageNum + 1);
        });
        lastPageLi.click(function () {
            loadPage(result.extend.userPage.pages);
        });
    }
    ul.append(firstPageLi).append(prePageLi);

    $.each(result.extend.userPage.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.userPage.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            loadPage(item);
        })
        ul.append(numLi);
    })
    ul.append(nextPageLi).append(lastPageLi);

    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}

// function reset_form(element) {
//     $(element)[0].reset();
//     $(element).find('*').removeClass("has-error has-success");
//     $(element).find(".help-block").text("");
// }

// 弹出新增用户界面
$("#user_add_btn").click(function () {
    $('#userModal form')[0].reset();
    $("#dropDownButton").jqxDropDownButton('setContent', '');
    $('#userModal').modal({
        backdrop: 'static'
    });
    loadOrg();
    $('#userLabel').html('添加用户');
    $('#user_save_btn').text('保存');
});

// 校验
// function validate_add_form() {
//     var loginName = $('#loginName').val();
//     var regName = /^[a-zA-Z0-9_-]{3,16}$/;
//     if (!regName.test(loginName)) {
//         show_validata_msg('#loginName', 'error', '用户名必须是3到16位的字母与数字组合');
//         return false;
//     } else {
//         show_validata_msg('#loginName', 'success', '');
//     };
// //            var email = $('#emal').val();
// //            var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
// //            if(!regEmail.test(email)){
// //                alert('邮箱格式不正确');
// //                return false;
// //            }
//     return true;
// }

// function show_validata_msg(element, status, msg) {
//     $(element).parent().removeClass("has-error has-success");
//     $(element).next('span').text();
//     if ('success' == status) {
//         $(element).parent().attr("has-success");
//     } else if ('error' == status) {
//         $(element).parent().attr("has-error");
//     }
//     $(element).next("span").text(msg);
// };

// $('#loginName').change(function () {
//     $.ajax({
//         url: '/user/checkUser',
//         data: 'loginName=' + $('#loginName').val(),
//         type: 'POST',
//         success: function (result) {
//             if (result.code == 100) {
//                 show_validata_msg('#loginName', "success", "用户名可用");
//                 $('#user_save_btn').attr("ajax-va", "success");
//             } else {
//                 show_validata_msg('#loginName', "error", result.extend.va_msg);
//                 $('#user_save_btn').attr("ajax-va", "error");
//             }
//         }
//     })
// });

// 弹出修改用户界面
$(document).on('click', '.editUser-btn', function () {
    $('#userModal').modal({
        backdrop: 'static'
    });
    loadOrg();
    getUser($(this).attr("editUser-id"));// 根据用户ID查询用户信息
    $('#userLabel').html('修改用户');
    $('#user_save_btn').text('更新');
    $('#user_save_btn').attr('edit_id', $(this).attr('edit-id'));
});
// 根据用户ID查询用户信息
function getUser(id) {
    $.ajax({
        url: 'user/getUserById/' + id,
        type: 'GET',
        success: function (result) {
            var userElement = result.extend.user;
            $('#loginName').val(userElement.loginName);
            $('#password').val(userElement.password);
            $('#realName').val(userElement.realName);
            $('#sex').val(userElement.sex);
            $('#email').val(userElement.email);
            $('#phone').val(userElement.phone);
            $('#type').val(userElement.type);
            $('#status').val(userElement.status);
            $("#dropDownButton").jqxDropDownButton('setContent', '<div style="position: relative; margin-left: 3px; margin-top: 5px;">' + userElement.orgName + '</div>');
            $('#description').val(userElement.description);
        }
    })
};
// 保存用户信息
$('#user_save_btn').click(function () {
    var url;
    // if (!validate_add_form()) {
    //     return false;
    // };
    // if ($(this).attr("ajax-va") == 'error') {
    //     return false;
    // };
    if ($(this).text() == '保存') {
        url = 'user/addUser';
    } else {
        var id = $('#user_save_btn').attr('editUser-id');
        url = 'user/editUser/' + id;
    };
    $.ajax({
        url: url,
        type: 'POST',
        data: $('#userModal form').serialize(),
        success: function (result) {
            if (result.code == 100) {
                $('#userModal').modal('hide');
                loadPage(totalRecord);
            } else {
                // if (undefined != result.extend.fieldErrors.email) {
                //     show_validata_msg('#email', 'error', result.extend.fieldErrors.email);
                // };
                // if (undefined != result.extend.fieldErrors.loginName) {
                //     show_validata_msg('#email', 'error', result.extend.fieldErrors.loginName);
                // };
            }
        }
    });
});
function loadOrg() {
    $.ajax({
        url: '/organization/getOrganizations',
        type: 'GET',
        success: function (result) {
            var data = result.extend.organizationList;// 加载机构信息
            var source =
                {
                    datatype: "json",
                    datafields: [
                        {name: 'id'},
                        {name: 'pid'},
                        {name: 'name'}
                    ],
                    id: 'id',
                    localdata: data
                };
            var dataAdapter = new $.jqx.dataAdapter(source);
            dataAdapter.dataBind();
            var records = dataAdapter.getRecordsHierarchy('id', 'pid', 'items', [{name: 'name', map: 'label'}]);
            // Create jqxDropDownButton
            $("#dropDownButton").jqxDropDownButton({width: 170, height: 28, theme: 'bootstrap'});
            $('#jqxTree').on('select', function (event) {
                var args = event.args;
                var item = $('#jqxTree').jqxTree('getItem', args.element);
                var dropDownContent = '<div style="position: relative; margin-left: 3px; margin-top: 5px;">' + item.label + '</div>';
                $("#dropDownButton").jqxDropDownButton('setContent', dropDownContent);
                $('#dropDownButton').jqxDropDownButton('close');
                $('#orgId').val(item.id);
            });
            $("#jqxTree").jqxTree({
                source: records,
                width: 170,
                theme: 'bootstrap',
            });
        }
    });
};
// 弹出删除用户界面
$(document).on('click', '.deleteUser-btn', function () {
    $('#delcfmModel').modal({
        backdrop: 'static'
    });
    $('#user_delete_btn').attr('deleteUser-id', $(this).attr('deleteUser-id'));
});
$("#user_delete_btn").click(function () {
    var id = $('#user_delete_btn').attr('deleteUser-id');
    deleteUserById(id);
});
// 根据id删除用户信息
function deleteUserById(id) {
    $.ajax({
        url: 'user/deleteUserById/' + id,
        type: 'POST',
        success: function (result) {
            if (result.extend.count != 0) {
                $('#delcfmModel').modal('hide');
                loadPage(1);
            } else {

            }
        }
    })
};