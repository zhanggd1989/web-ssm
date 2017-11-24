var totalRecord;
var id;
// 初始化
$(function () {
    loadPage(1);// 加载页面
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
    var roles = result.extend.rolePage.list;
    $.each(roles, function (index, item) {
        var name = $("<td></td>").append(item.name);
        var sequence = $("<td></td>").append(item.sequence);
        var status = $("<td></td>").append(item.status == 0 ? '启用' : '停用');
        var description = $("<td></td>").append(item.description);
        var editRoleBtn = $("<button></button>").addClass("btn btn-primary btn-xs editRole-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
            .append("&nbsp;")
            .append("编辑");
        editRoleBtn.attr("editRole-id", item.id);
        var deleteRoleBtn = $("<button></button>").addClass("btn btn btn-danger btn-xs deleteRole-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
            .append("&nbsp;")
            .append("删除");
        deleteRoleBtn.attr("deleteRole-id", item.id);
        var grantResourceBtn = $("<button></button>").addClass("btn btn-info btn-xs grantResource-btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-tags"))
            .append("&nbsp;")
            .append("分配资源");
        grantResourceBtn.attr('grantResource-id', item.id);
        var btn = $("<td></td>").append(editRoleBtn).append("&nbsp;").append(deleteRoleBtn).append("&nbsp;").append(grantResourceBtn);
        $("<tr></tr>").append(name).append(sequence).append(status).append(description).append(btn)
            .appendTo("#roleTable tbody");
    });
};

// 加载条数信息
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前" + result.extend.rolePage.pageNum + "页,")
        .append("总共" + result.extend.rolePage.pages + "页，")
        .append("总" + result.extend.rolePage.total + "条记录");
    totalRecord = result.extend.rolePage.total;
};

// 加载分页信息
function build_page_nav(result) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    //首页+上一页
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.rolePage.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            loadPage(1);
        });
        prePageLi.click(function () {
            loadPage(result.extend.rolePage.pageNum - 1);
        });
    }
    //下一页+末页
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.extend.rolePage.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            loadPage(result.extend.rolePage.pageNum + 1);
        });
        lastPageLi.click(function () {
            loadPage(result.extend.rolePage.pages);
        });
    }
    ul.append(firstPageLi).append(prePageLi);

    $.each(result.extend.rolePage.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.rolePage.pageNum == item) {
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
};

// 弹出新增角色界面
$("#role_add_btn").click(function () {
    $('#roleModal').modal({
        backdrop: 'static'
    });
    $('#roleModal form')[0].reset();
    $('#roleLabel').html('添加角色');
    $('#role_save_btn').text('保存');
});

// 弹出修改角色界面
$(document).on('click', '.editRole-btn', function () {
    $('#roleModal').modal({
        backdrop: 'static'
    });
    getRoleById($(this).attr("editRole-id"));// 根据角色id查询角色信息
    $('#roleLabel').html('修改角色');
    $('#role_save_btn').text('更新');
    $('#role_save_btn').attr('editRole-id', $(this).attr('editRole-id'));
});

// 保存角色信息
$('#role_save_btn').click(function () {
    var url;
    var btnText = $(this).text();

    if ($(this).attr("ajax-va") == 'error') {
        return false;
    }
    ;
    if (btnText == '保存') {
        url = 'role/addRole';
    } else {
        var id = $('#role_save_btn').attr('editRole-id');
        url = 'role/editRole/' + id;
    }
    ;
    $.ajax({
        url: url,
        type: 'POST',
        data: $('#roleModal form').serialize(),
        success: function (result) {
            if (result.code == 100) {
                $('#roleModal').modal('hide');
                if (btnText == '保存') {
                    loadPage(totalRecord);
                } else {
                    loadPage($('#page_nav_area').find('li.active a').html());
                }
            } else {
            }
        }
    });
});

// 根据角色id查询角色信息
function getRoleById(id) {
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
    var roleId = $(this).attr('deleteRole-id');
    $.ajax({
        url: 'user/getUsersByRoleId/' + roleId,
        type: 'GET',
        success: function (result) {
            var users = result.extend.userList;
            if (users.length != 0) {
                var userName = '';
                $.each(users, function (index, item) {
                    userName += item.realName + ',';
                });
                $('#roleContent').html("请先解除用户【" + userName.substring(0, userName.length - 1) + "】与该角色的关系");
                $('#role_delete_btn').attr('disabled', true);
            }
            else {
                $('#roleContent').html("确认要删除【角色】及【角色授予的资源】吗？");
                $('#role_delete_btn').attr('disabled', false);

            }
            $('#delcfmModel').modal({
                backdrop: 'static'
            });
            $('#role_delete_btn').attr('deleteRole-id', roleId);
        }
    });
});

// 删除角色信息
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

// 弹出分配资源界面
$(document).on('click', '.grantResource-btn', function () {
    $('#grantResourceModel').modal({
        backdrop: 'static'
    });
    loadResource($(this).attr('grantResource-id')); // 加载资源列表
    $('#role_grantresource_btn').attr('grantResource-id', $(this).attr('grantResource-id'));
});

// 加载资源列表
function loadResource(id) {
    $.ajax({
        url: '/resource/getResources',
        type: 'POST',
        success: function (result) {
            var resourceList = result.extend.resourceList;
            var source =
                {
                    datatype: "json",
                    datafields: [
                        {name: 'id'},
                        {name: 'pid'},
                        {name: 'name'}
                    ],
                    id: 'id',
                    localdata: resourceList
                };
            var dataAdapter = new $.jqx.dataAdapter(source);
            dataAdapter.dataBind();
            var records = dataAdapter.getRecordsHierarchy('id', 'pid', 'items', [{name: 'name', map: 'label'}]);
            $('#jqxWidget').jqxTree({
                source: records,
                hasThreeStates: true,
                checkboxes: true,
                height: '50%'
            });
            $("#jqxWidget").jqxTree('expandAll');
            loadResourceByRoleId(id); //加载指定角色分配的资源
        }
    })
};

// 加载指定角色分配的资源
function loadResourceByRoleId(id) {
    $.ajax({
        url: 'resource/getResourcesByRoleId/' + id,
        type: 'POST',
        success: function (result) {
            var roleResources = result.extend.roleResource;
            if (roleResources.length != 0) {
                $.each(roleResources, function (index, item) {
                    $("#jqxWidget").jqxTree('checkItem', $("#" + item.resourceId)[0], true);
                });
            }
        }
    })
};

// 保存角色-资源关系
$("#role_grantresource_btn").click(function () {
    var id = $('#role_grantresource_btn').attr('grantResource-id')
    var chk_value = [];
    $.each($('#jqxWidget').jqxTree('getCheckedItems'), function (index, item) {
        chk_value.push(item.id);
    });
    saveRoleAndResourcesByRoleId(id, chk_value);
});

// 根据角色id保存角色和资源关系
function saveRoleAndResourcesByRoleId(roleId, resourceIds) {
    $.ajax({
        url: 'role/addRoleAndResourcesByRoleId/' + roleId,
        type: 'POST',
        data: "resourceIds=" + resourceIds,
        success: function (result) {
            if (result.code == 100) {
                $('#grantResourceModel').modal('hide');
                loadPage(1);
            } else {

            }
        }
    })
};