<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" id="id" value="${(user.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="userName" id="userName" value="${(user.userName)!}" placeholder="请输入真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="sex" name="sex" value="${(user.sex)!}"
                   id="sex"
                   placeholder="请输入性别">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="age" name="age" value="${(user.age)!}" id="age" placeholder="请输入年龄">
        </div>
    </div>

    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">课程</label>
        <div class="layui-input-block">
            <select name="subId" xm-select="selectSubId"></select>
        </div>
    </div>

    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">专业名称</label>
        <div class="layui-input-block">
            <select name="majorId" xm-select="selectMajorId"></select>
        </div>
    </div>

    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <select name="roleId" xm-select="selectRole"></select>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/user/add_update.js"></script>
</body>
</html>