<!DOCTYPE html>
<html>
<head>

    <title>评价系统-注册</title>
    <link rel="stylesheet" href="${ctx}/css/index.css" media="all">
    <#include "common.ftl">
</head>
<body>
<form class="layui-form" style="width:80%;">
<#--    <input name="id" type="hidden" value="${(user.id)!}"/>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="userName" id="userName"  value="${(user.userName)!}" placeholder="请输入用户名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input sex"
                   lay-verify="sex" name="sex" value="${(user.sex)!}"
                   id="sex"
                   placeholder="请输入性别">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input age"
                   lay-verify="age" name="age" value="${(user.age)!}" id="age" placeholder="请输入年龄">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/register.js"></script>
</body>
</html>