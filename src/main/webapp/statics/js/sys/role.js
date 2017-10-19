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
        url: "/role/getRoles",
        data: "page=" + page,
        type: "GET",
        success: function (result) {
            // 加载角色信息
            build_role(result);
            // 加载条数信息
            build_page_info(result);
            // 加载分页信息
            build_page_nav(result);
        }
    })
};

// 加载角色信息
function build_role(result) {
    $("#roleTable tbody").empty();
    var roles = result.extend.roleList.list;
    $.each(roles, function (index, item) {
        var name = $("<td></td>").append(item.name);
        var sequence = $("<td></td>").append(item.sequence);
        var description = $("<td></td>").append(item.description);
        var status = $("<td></td>").append(item.status == 0 ? '启用' : '停用');
        var editRoleBtn = $("<button></button>").addClass("btn btn-primary btn-xs editRole-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
            .append("编辑");
        editRoleBtn.attr("editRole-id", item.id);
        var deleteRoleBtn = $("<button></button>").addClass("btn btn btn-danger btn-xs deleteRole-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
            .append("删除");
        deleteRoleBtn.attr("deleteRole-id", item.id);
        var grantResourceBtn = $("<button></button>").addClass("btn btn-info btn-xs grantResource-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-tags"))
            .append("分配资源");
        grantResourceBtn.attr('grantResource-id', item.id);
        var btn = $("<td></td>").append(editRoleBtn).append("&nbsp;").append(deleteRoleBtn).append("&nbsp;").append(grantResourceBtn);
        $("<tr></tr>").append(name).append(sequence).append(description).append(status).append(btn)
            .appendTo("#roleTable tbody");
    })
}

// 加载条数信息
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前" + result.extend.roleList.pageNum + "页,")
        .append("总共" + result.extend.roleList.pages + "页，")
        .append("总" + result.extend.roleList.total + "条记录");
    totalRecord = result.extend.roleList.total;
}

// 加载分页信息
function build_page_nav(result) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    //首页+上一页
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.roleList.hasPrevisousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            loadPage(1);
        });
        prePageLi.click(function () {
            loadPage(result.extend.roleList.pageNum - 1);
        });
    }
    //下一页+末页
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.extend.roleList.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            loadPage(result.extend.roleList.pageNum + 1);
        });
        lastPageLi.click(function () {
            loadPage(result.extend.roleList.pages);
        });
    }
    ul.append(firstPageLi).append(prePageLi);

    $.each(result.extend.roleList.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.roleList.pageNum == item) {
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

// 弹出新增角色界面
$("#role_add_btn").click(function () {
    $('#roleModal form')[0].reset();
    // getOrganizaitons();
    $('#roleModal').modal({
        backdrop: 'static'
    });
    $('#roleLabel').html('添加角色');
    $('#role_save_btn').text('保存');
})

// 获取机构信息
// function getOrganizaitons() {
//     $('#company').empty();
//     $.ajax({
//         url: "/organization/organizationTree",
//         type: "GET",
//         success: function (result) {
//             $.each(result, function (i, n) {
//                 var optionEle = $('<option></option>').append(n.text).attr("value", n.id).attr("children", n.children);
//                 optionEle.appendTo('#company');
//             })
//             $('#company').select2();
//         }
//     })
// }

// 获取部门信息
// $('#department').click(function () {
//     var companyId = $('#company').val();
//     $.ajax({
//         url: "",
//         type: "GET",
//         success: function (result) {
//             $('#department').select2({
//                 width: '170px',
//                 data: [{
//                     id: '1',
//                     text: '行政部'
//                 }, {
//                     id: '2',
//                     text: '财务部'
//                 }]
//             })
//         }
//     })
// })

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
// })
// 保存用户信息
$('#role_save_btn').click(function () {
    var url;
    // if (!validate_add_form()) {
    //     return false;
    // }
    // ;
    if ($(this).attr("ajax-va") == 'error') {
        return false;
    }
    if ($(this).text() == '保存') {
        url = 'role/addRole';
    } else {
        var id = $('#role_save_btn').attr('editRole-id');
        url = 'role/editRole/' + id;
    }
    $.ajax({
        url: url,
        type: 'POST',
        data: $('#roleModal form').serialize(),
        success: function (result) {
            if (result.code == 100) {
                $('#roleModal').modal('hide');
                loadPage(totalRecord);
            } else {
                // if (undefined != result.extend.fieldErrors.email) {
                //     show_validata_msg('#email', 'error', result.extend.fieldErrors.email);
                // }
                // if (undefined != result.extend.fieldErrors.loginName) {
                //     show_validata_msg('#email', 'error', result.extend.fieldErrors.loginName);
                // }
            }

        }

    })
});
// 弹出修改角色界面
$(document).on('click', '.editRole-btn', function () {
    // getCompanys();
    getRole($(this).attr("editRole-id"));// 根据用户ID查询用户信息
    $('#roleModal').modal({
        backdrop: 'static'
    });
    $('#roleLabel').html('修改角色');
    $('#role_save_btn').text('更新');
    $('#role_save_btn').attr('editRole-id', $(this).attr('editRole-id'));
});

// 根据角色ID查询角色信息
function getRole(id) {
    $.ajax({
        url: 'role/getRoleById/' + id,
        type: 'GET',
        success: function (result) {
            var roleElement = result.extend.role;
            $('#name').val(roleElement.name);
            $('#sequence').val(roleElement.sequence);
            $('#description').val(roleElement.description);
            $('#status').val(roleElement.status);
        }
    })
};

// 弹出删除角色界面
$(document).on('click', '.deleteRole-btn', function () {
    $('#delcfmModel').modal({
        backdrop: 'static'
    });
    $('#role_delete_btn').attr('deleteRole-id', $(this).attr('deleteRole-id'));
});
$("#role_delete_btn").click(function () {
    var id = $('#role_delete_btn').attr('deleteRole-id');
    deleteRoleById(id);
});
// 根据id删除角色信息
function deleteRoleById(id) {
    $.ajax({
        url: 'role/deleteRoleById/' + id,
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