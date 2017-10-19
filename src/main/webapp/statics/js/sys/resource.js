$(function () {
    loadPage();// 加载页面
});

// 加载页面
function loadPage() {
    $.ajax({
        url: '/resource/getResources',
        type: 'GET',
        success: function (result) {
            build_resource(result);// 加载资源信息
        }
    })
};

// 加载资源信息
function build_resource(result) {
    var resourceList = result.extend.resourceList;
    // prepare the data
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
    // create Tree Grid
    $("#treeGrid").jqxTreeGrid(
        {
            width: '100%',
            height: '100%',
            source: dataAdapter,
            filterable: true,
            altRows: true,
            sortable: true,
            theme: 'bootstrap',
            ready: function () {
                $("#treeGrid").jqxTreeGrid('expandAll');
            },
            rendered: function () {
                // 编辑资源
                $(".resource-edit").click(function () {
                    var selection = $("#treeGrid").jqxTreeGrid('getSelection');
                    $('#resourceModal').modal({
                        backdrop: 'static'
                    });
                    $('#resourceLabel').html('编辑资源');
                    $('#resource_save_btn').text('更新');
                    $('#resource_save_btn').attr('edit_id', selection[0].id);
                    getResourceById(selection[0].id);
                    loadParentResource();
                });
                // 删除资源
                $(".resource-delete").click(function () {
                    var selection = $("#treeGrid").jqxTreeGrid('getSelection');
                    $('#delcfmModel').modal({
                        backdrop: 'static'
                    });
                    $('#resource_delete_btn').attr('delete_id', selection[0].id);
                });
            },
            columns:
                [
                    {text: 'id', dataField: 'id', align: 'center', hidden: true},
                    {text: '名称', dataField: 'name', align: 'center'},
                    {text: '链接', dataField: 'url', align: 'center'},
                    {text: '排序', dataField: 'sequence', align: 'center'},
                    {text: '图标', dataField: 'icon', align: 'center'},
                    {
                        text: '类型',
                        dataField: 'type',
                        align: 'center',
                        cellsRenderer: function (row, column, value, rowData) {
                            if ('0' == value) {
                                return '菜单'
                            } else {
                                return '按钮'
                            }
                        }
                    },
                    {text: '资源描述', dataField: 'description', align: 'center'},
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
                        var editResourceBtn = "<input type='button' value='编辑' class='btn btn-primary btn-xs resource-edit'/>";
                        var deleteResourceBtn = "<input type='button' value='删除' class='btn btn btn-danger btn-xs resource-delete'/>";
                        return editResourceBtn + "&nbsp;" + deleteResourceBtn;
                    }
                    }
                ]
        })
    ;
};

// 根据id查询资源信息
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
                $('#pid').val(resourceElement.pid);
                $('#status').val(resourceElement.status);
            } else {

            }
        }
    })
};
// 保存资源信息
$('#resource_save_btn').click(function () {
    var url;
    if ($(this).text() == '保存') {
        url = 'resource/addResource';
    } else {
        var id = $('#resource_save_btn').attr('edit_id');
        url = 'resource/editResource/' + id;
    }
    ;
    $.ajax({
        url: url,
        type: 'POST',
        data: $('#resourceModal form').serialize(),
        success: function (result) {
            if (result.code == 100) {
                $('#resourceModal').modal('hide');
                loadPage();
                $("#treeGrid").jqxTreeGrid('expandAll');
            } else {

            }
        }
    })
});
// 删除资源信息
$('#resource_delete_btn').click(function () {
    var id = $('#resource_delete_btn').attr('delete_id');
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
// 新增资源信息
$("#resource_add_btn").click(function () {
    $('#resourceModal form')[0].reset();
    $('#resourceModal').modal({
        backdrop: 'static'
    });
    $('#resourceLabel').html('添加资源');
    $('#resource_save_btn').text('保存');
    loadParentResource();
});

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
            // Create jqxDropDownButton
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