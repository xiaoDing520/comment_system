<!DOCTYPE html>
<html>
<head>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
	<input type="hidden" name="subId" value="${(subject.id)!}">
	<input type="hidden" name="userId" value="${(subject.userId)!}">


	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"> <legend>对这门课程的好感</legend>
	</fieldset>
	<div id="test1"></div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"> <legend>对这位老师的好感</legend>
	</fieldset>
	<div id="test2"></div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"> <legend>学习时是否有明确目标</legend>
	</fieldset>
	<div id="test3"></div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"> <legend>老师的讲解效果</legend>
	</fieldset>
	<div id="test4"></div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"> <legend>课堂小结效果</legend>
	</fieldset>
	<div id="test5"></div>

	<ul>
		<li>
			<div id="test10"></div></li>
		<li>
			<div id="test11"></div></li>
		<li>
			<div id="test12"></div></li>
		<li>
			<div id="test13"></div></li>
		<li>
			<div id="test14"></div></li>
	</ul>

<#--	<script src="/public/js/add.js" charset="utf-8"></script>-->
	<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
	<script>
		layui.use(['rate'], function(){
			var rate = layui.rate;
			//基础效果
			rate.render({
				elem: '#test1'
			})
			rate.render({
				elem: '#test2'
			})
			rate.render({
				elem: '#test3'
			})
			rate.render({
				elem: '#test4'
			})
			rate.render({
				elem: '#test5'
			})


		});
	</script>


	<div>请您从以下3个角度打分(必填)</div>
	<hr/>
	<div class="layui-form-item">
		<label class="layui-form-label">教学态度</label>
		<div class="layui-input-block">
			<input type="number" name="manner" required="required"  placeholder="请输入0~100" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">教学氛围</label>
		<div class="layui-input-block">
			<input type="number" name="atmosphere" required="required" placeholder="请输入0~100" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">教学质量</label>
		<div class="layui-input-block">
			<input type="number" name="quality" required="required" placeholder="请输入0~100" autocomplete="off" class="layui-input">
		</div>
	</div>
	</div>
	<hr />
	<div>想对老师说些什么吗?(非必填)</div>
	<textarea></textarea>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-lg" id="submit" lay-filter="update">确认</button>
			<button class="layui-btn layui-btn-lg" id="closeBtn">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="${ctx}/js/student/add.js"></script>
</body>
</html>
