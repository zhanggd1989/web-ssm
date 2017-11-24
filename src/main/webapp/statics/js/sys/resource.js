// 初始化
$(function () {
    loadPage();// 加载页面
});

// 加载页面
function loadPage() {
    $.ajax({
        url: '/resource/getResources',
        type: 'GET',
        success: function (result) {
            // 加载资源信息
            build_resource(result);
        }
    })
};

// 加载资源信息
function build_resource(result) {
    var resourceList = result.extend.resourceList;
    var source =
        {
            dataType: "json",
            dataFields: [
                {name: 'id', type: 'number'},
                {name: 'name', type: 'string'},
                {name: 'url', type: 'string'},
                {name: 'sequence', type: 'string'},
                {name: 'icon', type: 'string'},
                {name: 'type', type: 'string'},
                {name: 'description', type: 'string'},
                {name: 'pid', type: 'string'},
                {name: 'status', type: 'string'}
            ],
            hierarchy:
                {
                    keyDataField: {name: 'id'},
                    parentDataField: {name: 'pid'}
                },
            id: 'id',
            localData: resourceList
        };
    var dataAdapter = new $.jqx.dataAdapter(source);
    $("#treeGrid").jqxTreeGrid(
        {
            width: '100%',
            height: '80%',
            source: dataAdapter,
            filterable: true,
            altRows: true,
            sortable: true,
            theme: 'bootstrap',
            ready: function () {
                $("#treeGrid").jqxTreeGrid('expandAll');
            },
            rendered: function () {
                // 弹出编辑资源界面
                $(".resource-edit").click(function () {
                    var selection = $("#treeGrid").jqxTreeGrid('getSelection');
                    $('#resourceModal').modal({
                        backdrop: 'static'
                    });
                    $('#resourceLabel').html('编辑资源');
                    $('#resource_save_btn').text('更新');
                    $('#resource_save_btn').attr('editResource-id', selection[0].id);
                    getResourceById(selection[0].id);
                    loadParentResource();
                });
                // 弹出删除资源界面
                $(".resource-delete").click(function () {
                    var selection = $("#treeGrid").jqxTreeGrid('getSelection');
                    var resourceId = selection[0].id;
                    $.ajax({
                        url: 'role/getRolesByResourceId/' + resourceId,
                        type: 'GET',
                        success: function (result) {
                            var roles = result.extend.roleList;
                            if (roles.length != 0) {
                                var roleNames = '';
                                $.each(roles, function (index, item) {
                                    roleNames += item.name + ',';
                                });
                                $('#resourceContent').html("请先解除角色【" + roleNames.substring(0, roleNames.length - 1) + "】与该资源的关系");
                                $('#resource_delete_btn').attr('disabled', true);
                            }
                            else {
                                $('#resourceContent').html("确认要删除吗？");
                                $('#resource_delete_btn').attr('disabled', false);
                            }
                            $('#delcfmModel').modal({
                                backdrop: 'static'
                            });
                            $('#resource_delete_btn').attr('deleteResource-id', selection[0].id);
                        }
                    })
                });
            },
            columns:
                [
                    {text: 'id', dataField: 'id', align: 'left', hidden: true},
                    {text: '名称', dataField: 'name', align: 'left'},
                    {text: '链接', dataField: 'url', align: 'left'},
                    {text: '排序', dataField: 'sequence', align: 'left'},
                    {text: '图标', dataField: 'icon', align: 'left'},
                    {
                        text: '类型',
                        dataField: 'type',
                        align: 'left',
                        cellsRenderer: function (row, column, value, rowData) {
                            if ('0' == value) {
                                return '菜单'
                            } else {
                                return '按钮'
                            }
                        }
                    },
                    {text: '资源描述', dataField: 'description', align: 'left'},
                    {
                        text: '状态',
                        dataField: 'status',
                        align: 'left',
                        cellsRenderer: function (row, column, value, rowData) {
                            if ('0' == value) {
                                return '启用'
                            } else {
                                return '停用'
                            }
                        }
                    },
                    {
                        text: '操作', align: 'left', cellsRenderer: function (row, column, value, rowData) {
                        var editResourceBtn = "<button class='btn btn-primary btn-xs resource-edit'><span class='glyphicon glyphicon-pencil'></span>编辑</button>";
                        var deleteResourceBtn = "<button class='btn btn-danger btn-xs resource-delete'><span class='glyphicon glyphicon-trash'></span>删除</button>";
                        return editResourceBtn + "&nbsp;" + deleteResourceBtn;
                    }
                    }
                ]
        }
    );
};

// 弹出新增资源界面
$("#resource_add_btn").click(function () {
    $('#resourceModal').modal({
        backdrop: 'static'
    });
    $('#resourceModal form')[0].reset();
    $("#dropDownButton").jqxDropDownButton('setContent', '');
    loadParentResource();
    $('#resourceLabel').html('添加资源');
    $('#resource_save_btn').text('保存');
});

// 保存资源信息
$('#resource_save_btn').click(function () {
    var url;
    var btnText = $(this).text();
    if (btnText == '保存') {
        url = 'resource/addResource';
    } else {
        var id = $('#resource_save_btn').attr('editResource-id');
        url = 'resource/editResourceById/' + id;
    };
    $.ajax({
        url: url,
        type: 'POST',
        data: $('#resourceModal form').serialize(),
        success: function (result) {
            if (result.code == 100) {
                $('#resourceModal').modal('hide');
                loadPage();
            } else {

            }
        }
    })
});

// 根据资源id查询资源信息
function getResourceById(id) {
    $.ajax({
        url: 'resource/getResourceById/' + id,
        type: 'GET',
        success: function (result) {
            if (result.extend.resource != null) {
                var resourceElement = result.extend.resource;
                $('#name').val(resourceElement.name);
                $('#url').val(resourceElement.url);
                $('#sequence').val(resourceElement.sequence);
                $('#icon').val(resourceElement.icon);
                $('#type').val(resourceElement.type);
                $('#description').val(resourceElement.description);
                $("#dropDownButton").jqxDropDownButton('setContent', '<div style="position: relative; margin-left: 3px; margin-top: 5px; font-size: 14px; padding: 1px 12px; color: #555;">' + resourceElement.pName + '</div>');
                $('#status').val(resourceElement.status);
            } else {

            }
        }
    })
};

// 加载父资源信息
function loadParentResource() {
    $.ajax({
        url: '/resource/getResources',
        type: 'GET',
        success: function (result) {
            var data = result.extend.resourceList;// 加载资源信息
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
            $("#dropDownButton").jqxDropDownButton({width: 170, height: 28, theme: 'bootstrap'});
            $('#jqxTree').on('select', function (event) {
                var args = event.args;
                var item = $('#jqxTree').jqxTree('getItem', args.element);
                var dropDownContent = '<div style="position: relative; margin-left: 3px; margin-top: 5px;">' + item.label + '</div>';
                $("#dropDownButton").jqxDropDownButton('setContent', dropDownContent);
                $('#dropDownButton').jqxDropDownButton('close');
                $('#pid').val(item.id);
            });
            $("#jqxTree").jqxTree({
                source: records,
                width: 170,
                theme: 'bootstrap',
            });
        }
    });
};

// 删除资源信息
$('#resource_delete_btn').click(function () {
    var id = $('#resource_delete_btn').attr('deleteResource-id');
    deleteResourceById(id);
});

// 根据id删除资源信息
function deleteResourceById(id) {
    $.ajax({
        url: 'resource/deleteResourceById/' + id,
        type: 'POST',
        success: function (result) {
            if (result.extend.count != 0) {
                $('#delcfmModel').modal('hide');
                loadPage();
            } else {

            }
        }
    })
};