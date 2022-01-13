<!DOCTYPE html>
<html>
<head>
    <title>查询评价</title>
    <#include "../common.ftl">
    <style>

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
    </blockquote>

    <table id="scoreList" class="layui-table"  lay-filter="users"></table>
<#--    <!--操作&ndash;&gt;-->
<#--    <script id="scoreListBar" type="text/html">-->
<#--        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>-->
<#--    </script>-->
</form>

<script type="text/javascript" src="${ctx}/js/score/score.js"></script>
</body>
</html>