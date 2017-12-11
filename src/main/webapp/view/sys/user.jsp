<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
    <title>用户信息</title>
    <jsp:include page="../inc.jsp"></jsp:include>
    <script src="${ctx}/statics/js/sys/user.js"></script>
</head>
<body>
<!-- 用户添加/修改界面 -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="userLabel"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">登录名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="loginName" name="loginName">
                            <span class="help-block"></span>
                        </div>
                        <label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="realName" name="realName">
                        </div>
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-4">
                            <label class="radio-inline">
                                <input type="radio" id="sex1" name="gender" value="0">男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="sex2" name="gender" value="1">女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-4">
                            <input type="email" class="form-control required email" id="email" name="email">
                        </div>
                        <label class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="phone" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="type" name="type">
                                <option value="0">管理员</option>
                                <option value="1">普通用户</option>
                            </select>
                        </div>
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="status" name="status">
                                <option value="0">启用</option>
                                <option value="1">停用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属机构</label>
                        <input type="text" class="form-control" id="orgId" name="orgId" style="display:none"/>
                        <div class="col-sm-4" id='jqxWidget'>
                            <div style='float: left;' id="dropDownButton">
                                <div style="border: none;" id='jqxTree'>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" id="description" name="description"></textarea>
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
<!-- 用户查询界面-->
<div class="panel panel-default">
    <div class="panel-heading">
        <form class="form-inline">
            <div class="form-group">
                <label for="inputRealName">姓名</label>
                <input type="text" class="form-control" id="inputRealName" name="inputRealName">
            </div>
            <button type="button" class="btn btn-default btn-sm" id="user_query_btn">查询
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </button>
        </form>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-sm" id="user_add_btn">新增
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover table-bordered" id="userTable">
                    <thead>
                    <tr>
                        <th>登录名</th>
                        <th>用户名</th>
                        <th>性别</th>
                        <th>邮箱</th>
                        <th>电话</th>
                        <th>类型</th>
                        <th>状态</th>
                        <th>所属机构</th>
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
<!-- 用户删除确认界面 -->
<div class="modal fade" id="delcfmModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>确认要删除【用户】及【用户授予的角色】吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="user_delete_btn">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- 用户授予角色界面 -->
<div class="modal fade" id="grantRoleModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">授予角色</h4>
            </div>
            <div class="modal-body" id="roleList">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="user_grantrole_btn">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>