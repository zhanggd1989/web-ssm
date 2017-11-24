// 初始化
$(function () {
    loadPage();// 加载页面
});

// 加载页面
function loadPage() {
    $.ajax({
        url: '/organization/getOrganizations',
        type: 'GET',
        success: function (result) {
            // 加载机构信息
            build_organization(result);
        }
    })
};

// 加载机构信息
function build_organization(result) {
    var organizationList = result.extend.organizationList;
    var source =
        {
            dataType: "json",
            dataFields: [
                {name: 'id', type: 'number'},
                {name: 'name', type: 'string'},
                {name: 'sequence', type: 'string'},
                {name: 'icon', type: 'string'},
                {name: 'type', type: 'string'},
                {name: 'address', type: 'string'},
                {name: 'pid', type: 'string'},
                {name: 'status', type: 'string'}
            ],
            hierarchy:
                {
                    keyDataField: {name: 'id'},
                    parentDataField: {name: 'pid'}
                },
            id: 'id',
            localData: organizationList
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
                // 弹出编辑机构界面
                $(".organization-edit").click(function () {
                    var selection = $("#treeGrid").jqxTreeGrid('getSelection');
                    $('#organizationModal').modal({
                        backdrop: 'static'
                    });
                    $('#organizationLabel').html('编辑机构');
                    $('#organization_save_btn').text('更新');
                    $('#organization_save_btn').attr('editOrganization-id', selection[0].id);
                    getOrganizationById(selection[0].id);
                    loadParentOrganization();
                });
                // 弹出删除机构界面
                $(".organization-delete").click(function () {
                    var selection = $("#treeGrid").jqxTreeGrid('getSelection');
                    var organizaitonId = selection[0].id;
                    $.ajax({
                        url : 'user/getUsersByOrganizationId/' + organizaitonId,
                        type : 'GET',
                        success : function (result) {
                            var users = result.extend.userList;
                            if (users.length != 0) {
                                var userNames = '';
                                $.each(users, function (index, item) {
                                    userNames += item.realName + ',';
                                });
                                $('#organizationContent').html("请先解除用户【" + userNames.substring(0, userNames.length - 1) + "】与该机构的关系");
                                $('#organization_delete_btn').attr('disabled', true);
                            }
                            else {
                                $('#organizationContent').html("确认要删除吗？");
                                $('#organization_delete_btn').attr('disabled', false);
                            }
                            $('#delcfmModel').modal({
                                backdrop: 'static'
                            });
                            $('#organization_delete_btn').attr('deleteOrganization-id', selection[0].id);
                        }
                    })
                });
            },
            columns:
                [
                    {text: 'id', dataField: 'id', align: 'center', hidden: true},
                    {text: '名称', dataField: 'name', align: 'center'},
                    {text: '排序', dataField: 'sequence', align: 'center'},
                    {text: '图标', dataField: 'icon', align: 'center'},
                    {
                        text: '类型',
                        dataField: 'type',
                        align: 'center',
                        cellsRenderer: function (row, column, value, rowData) {
                            if ('0' == value) {
                                return '单位'
                            } else {
                                return '部门'
                            }
                        }
                    },
                    {text: '联系地址', dataField: 'address', align: 'center'},
                    {
                        text: '状态',
                        dataField: 'status',
                        align: 'center',
                        cellsRenderer: function (row, column, value, rowData) {
                            if ('0' == value) {
                                return '启用'
                            } else {
                                return '停用'
                            }
                        }
                    },
                    {
                        text: '操作', align: 'center', cellsRenderer: function (row, column, value, rowData) {
                        var editOrganizationBtn = "<button class='btn btn-primary btn-xs organization-edit'><span class='glyphicon glyphicon-pencil'></span>编辑</button>";
                        var deleteOrganizationBtn = "<button class='btn btn-danger btn-xs organization-delete'><span class='glyphicon glyphicon-trash'></span>删除</button>";
                        return editOrganizationBtn + "&nbsp;" + deleteOrganizationBtn;
                    }
                    }
                ]
        })
    ;
};

// 弹出新增机构界面
$("#organization_add_btn").click(function () {
    $('#organizationModal').modal({
        backdrop: 'static'
    });
    $('#organizationModal form')[0].reset();
    $("#dropDownButton").jqxDropDownButton('setContent', '');
    loadParentOrganization();
    $('#organizationLabel').html('添加机构');
    $('#organization_save_btn').text('保存');
});

// 保存机构信息
$('#organization_save_btn').click(function () {
    var url;
    var btnText = $(this).text();
    if (btnText == '保存') {
        url = 'organization/addOrganization';
    } else {
        var id = $('#organization_save_btn').attr('editOrganization-id');
        url = 'organization/editOrganizationById/' + id;
    };
    $.ajax({
        url: url,
        type: 'POST',
        data: $('#organizationModal form').serialize(),
        success: function (result) {
            if (result.code == 100) {
                $('#organizationModal').modal('hide');
                loadPage();
            } else {

            }
        }
    })
});

// 根据组织id查询机构信息
function getOrganizationById(id) {
    $.ajax({
        url: 'organization/getOrganizationById/' + id,
        type: 'GET',
        success: function (result) {
            if (result.extend.organization != null) {
                var organizationElement = result.extend.organization;
                $('#name').val(organizationElement.name);
                $('#sequence').val(organizationElement.sequence);
                $('#icon').val(organizationElement.icon);
                $('#type').val(organizationElement.type);
                $('#address').val(organizationElement.address);
                $('#pid').val(organizationElement.pid);
                $("#dropDownButton").jqxDropDownButton('setContent', '<div style="position: relative; margin-left: 3px; margin-top: 5px; font-size: 14px; padding: 1px 12px; color: #555;">' + organizationElement.pName + '</div>');
                $('#status').val(organizationElement.status);
            } else {

            }
        }
    })
};

// 加载父机构信息
function loadParentOrganization() {
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

// 删除机构信息
$('#organization_delete_btn').click(function () {
    var id = $('#organization_delete_btn').attr('deleteOrganization-id');
    deleteOrganizationById(id);
});

// 根据id删除机构信息
function deleteOrganizationById(id) {
    $.ajax({
        url: 'organization/deleteOrganizationById/' + id,
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


