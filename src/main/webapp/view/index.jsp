<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <jsp:include page="inc.jsp"></jsp:include>
    <script src="./../statics/js/index/dynamic_tabs.js"></script>
    <script src="./../statics/js/index/index.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/statics/css/index/dynamic_tabs.css">
</head>
<body style="padding: 50px">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">SSM整合框架</a>
                    </div>
                    <div>
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">系统管理</a></li>
                            <li><a href="#">字典管理</a></li>
                            <li><a href="#">流程管理</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <div class="panel-group table-responsive" role="tablist">
                <div class="panel panel-primary leftMenu">
                    <div class="panel-heading" id="collapseListGroupHeading1" data-toggle="collapse"
                         data-target="#collapseListGroup1" role="tab">
                        <h4 class="panel-title">
                            系统管理
                            <span class="glyphicon glyphicon-chevron-up right"></span>
                        </h4>
                    </div>
                    <div id="collapseListGroup1" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="collapseListGroupHeading1">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="#" id="userControle" rel="${ctx}/user/list">
                                    <i class="glyphicon glyphicon-user"></i>
                                    <span>用户管理</span>
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a href="#" id="roleControl" rel="${ctx}/role/list">
                                    <i class="glyphicon glyphicon-asterisk"></i>
                                    <span>角色管理</span>
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a href="#" id="resourceControle" rel="${ctx}/resource/list">
                                    <i class="glyphicon glyphicon-file"></i>
                                    <span>资源管理</span>
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a href="#" id="orgControl" rel="${ctx}/organization/list">
                                    <i class="glyphicon glyphicon-tasks"></i>
                                    <span>机构管理</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-primary leftMenu">
                    <div class="panel-heading" id="collapseListGroupHeading2" data-toggle="collapse"
                         data-target="#collapseListGroup2" role="tab">
                        <h4 class="panel-title">
                            任务管理
                            <span class="glyphicon glyphicon-chevron-down right"></span>
                        </h4>
                    </div>
                    <div id="collapseListGroup2" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="collapseListGroupHeading2">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="#" id="todoControl" rel="">
                                    <i class="glyphicon glyphicon-bell"></i>
                                    <span>待办管理</span>
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a href="#" id="doneControl">
                                    <i class="glyphicon glyphicon-wrench"></i>
                                    <span>已办管理</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-primary leftMenu">
                    <div class="panel-heading" id="collapseListGroupHeading3" data-toggle="collapse"
                         data-target="#collapseListGroup3" role="tab">
                        <h4 class="panel-title">
                            业务管理
                            <span class="glyphicon glyphicon-chevron-down right"></span>
                        </h4>
                    </div>
                    <div id="collapseListGroup3" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="collapseListGroupHeading3">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="#" id="leaveControle">
                                    <i class="glyphicon glyphicon-paperclip"></i>
                                    <span>请假管理</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-primary leftMenu">
                    <div class="panel-heading" id="collapseListGroupHeading4" data-toggle="collapse"
                         data-target="#collapseListGroup4" role="tab">
                        <h4 class="panel-title">
                            流程管理
                            <span class="glyphicon glyphicon-chevron-down right"></span>
                        </h4>
                    </div>
                    <div id="collapseListGroup4" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="collapseListGroupHeading4">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="#" id="definitionControl">
                                    <i class="glyphicon glyphicon-leaf"></i>
                                    <span>定义管理</span>
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a href="#" id="deployControl">
                                    <i class="glyphicon glyphicon-tint"></i>
                                    <span>部署管理</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10 tabbable" id="myDivTab">
            <ul class="nav nav-tabs" id="myTab">
            </ul>
            <div class="tab-content">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
                <div class="container-fluid">
                    copyright
                </div>
            </nav>
        </div>
    </div>
</div>


</body>

</html>