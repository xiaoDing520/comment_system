<!DOCTYPE html>
<html>
<head>
    <title>学习课程</title>
    <#include "../common.ftl">
</head>
<style type="text/css">
</style>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" id="subName" name="subName"
                           class="layui-input searchVal" placeholder="课程名" />
                </div>
                <div class="layui-input-inline">
                    <select name="state" id="state">
                        <option value="" >状态</option>
                        <option value="0">未评价</option>
                        <option value="1" >已评价</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>
    <table id="saleChanceList" class="layui-table" lay-filter="subjects">
    </table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
        </div>
    </script>
    <!--操作-->
    <script id="saleChanceListBar" type="text/html">
                <button class="layui-btn layui-btn-xs" id="edit" lay-event="edit" name="edit">评价</button>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/student/student.js">
</script>
</body>
</html>