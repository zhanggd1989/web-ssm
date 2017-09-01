<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构信息</title>
    <jsp:include page="../inc.jsp"></jsp:include>
    <script src="${ctx}/statics/js/sys/organization.js"></script>
    <script src="${ctx}/statics/js/sys/jqx-all.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/sys/jqx.base.css">
    <link rel="stylesheet" href="${ctx}/statics/css/sys/jqx.bootstrap.css">
</head>
<body>
<!-- 机构添加/修改界面 -->
<div class="modal fade" id="organizationModal" tabindex="-1" role="dialog" aria-labelledby="organizationLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="organizationLabel"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name">
                            <span class="help-block"></span>
                        </div>
                        <label class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sequence" name="sequence">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图标</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="icon" name="icon">
                        </div>
                        <label class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="type" name="type">
                                <option value="0">单位</option>
                                <option value="1">部门</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系地址</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="address" name="address">
                        </div>
                        <label class="col-sm-2 control-label">上级机构</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="pid" name="pid">
                        </div>
                    </div>
                    <div class="form-group">
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
                <button type="button" class="btn btn-primary" id="user_save_btn"></button>
            </div>
        </div>
    </div>
</div>
<!-- 机构查询界面-->
<div class="panel panel-default">
    <div class="panel-body">
        <div class="row">
            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-sm" id="organization_add_btn">新增
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </button>
                <button type="button" class="btn btn-danger btn-sm" id="organization_delete_btn">删除
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
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
</body>
</html>
