<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资源信息</title>
    <jsp:include page="../inc.jsp"></jsp:include>
    <script src="${ctx}/statics/js/sys/resource.js"></script>
</head>
<body class="default">
<!-- 资源新增/修改界面 -->
<div class="modal fade" id="resourceModal" tabindex="-1" role="dialog" aria-labelledby="resourceLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="resourceLabel"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name">
                            <span class="help-block"></span>
                        </div>
                        <label class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="url" name="url">
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sequence" name="sequence">
                        </div>
                        <label class="col-sm-2 control-label">图标</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="icon" name="icon">
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="type" name="type">
                                <option value="0">菜单</option>
                                <option value="1">按钮</option>
                            </select>
                        </div>
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="description" name="description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上级资源</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="pid" name="pid" style="display:none"/>
                            <div id='jqxWidget'>
                                <div style='float: left;' id="dropDownButton">
                                    <div style="border: none;" id='jqxTree'>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="status" name="status">
                                <option value="0">启用</option>
                                <option value="1">停用</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="resource_save_btn"></button>
            </div>
        </div>
    </div>
</div>
<!-- 资源新增/删除按钮 -->
<div class="panel panel-default">
    <div class="panel-body">
        <div class="row">
            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-sm" id="resource_add_btn">新增
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div id="treeGrid">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 资源删除确认界面 -->
<div class="modal fade" id="delcfmModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="resource_delete_btn">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>