layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();

    $(".login-out").click(function (){
        //删除当前页面cookie
        $.removeCookie("userIdStr",{domain:"localhost",path:"/comment"});
        $.removeCookie("userName",{domain:"localhost",path:"/comment"});
        window.parent.location.href=ctx+"/index";
    });
});