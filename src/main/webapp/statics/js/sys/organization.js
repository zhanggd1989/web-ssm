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
    var organizationList = result.extend.organizationPage;
    // prepare the data
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
    // create Tree Grid
    $("#treeGrid").jqxTreeGrid(
        {
            width: '100%',
            height: '100%',
            source: dataAdapter,
            theme: 'bootstrap',
            ready: function () {
                $("#treeGrid").jqxTreeGrid('expandRow', '1');
            },
            columns: [
                {text: 'id', dataField: 'id', align: 'center', hidden: true},
                {text: '名称', dataField: 'name', align: 'center'},
                {text: '排序', dataField: 'sequence', align: 'center'},
                {text: '图标', dataField: 'icon', align: 'center'},
                {text: '类型', dataField: 'type', align: 'center'},
                {text: '联系地址', dataField: 'address', align: 'center'},
                {text: '状态', dataField: 'status', align: 'center'},
                {
                    text: '操作', align: 'center', cellsRenderer: function (row, column, value, rowData) {
                        console.log(rowData);
                    var editOrganizationBtn = "<input type='button' value='编辑' id='jqxEditbutton' class='btn btn-primary btn-xs'/>";
                    var deleteOrganizationBtn = "<input type='button' value='删除' id='jqxDeletebutton' class='btn btn btn-danger btn-xs'/>";
                    return editOrganizationBtn + "&nbsp;" + deleteOrganizationBtn;
                }
                }
            ]
        });
}