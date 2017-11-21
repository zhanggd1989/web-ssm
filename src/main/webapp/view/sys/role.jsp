<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>角色信息</title>
    <jsp:include page="../inc.jsp"></jsp:include>
    <script src="${ctx}/statics/js/sys/role.js"></script>
</head>
<body>
<!-- 角色添加/修改界面 -->
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="roleLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="roleLabel"></h4>
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
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="description" name="description">
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
                <button type="button" class="btn btn-primary" id="role_save_btn"></button>
            </div>
        </div>
    </div>
</div>
<!-- 角色查询界面-->
<div class="panel panel-default">
    <div class="panel-heading">
        <form class="form-inline">
            <div class="form-group">
                <label for="inputName">名称</label>
                <input type="text" class="form-control" id="inputName" name="inputName">
            </div>
            <button type="button" class="btn btn-default btn-sm" id="role_query_btn">查询
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </button>
        </form>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-sm" id="role_add_btn">新增
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover table-bordered" id="roleTable">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>排序</th>
                        <th>状态</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="panel-footer">
        <div class="row">
            <div class="col-md-6" id="page_info_area"></div>
            <div class="col-md-6" id="page_nav_area"></div>
        </div>
    </div>
</div>
<!-- 角色删除确认界面 -->
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
                <button type="button" class="btn btn-primary" id="role_delete_btn">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- 角色分配资源界面 -->
<div class="modal fade" id="grantResourceModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">分配资源</h4>
            </div>
            <div id='jqxWidget'>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="role_grantresource_btn">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>