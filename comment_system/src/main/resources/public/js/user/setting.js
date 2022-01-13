layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
//监听提交
    form.on('submit(saveBtn)',function(data){

        //发送ajax
        $.ajax({
            type:"post",
            url:ctx+"/userController/setting",
            data:{
                userName:data.field.userName,
                sex:data.field.sex,
                age:data.field.age,
                id:data.field.id
            },
            dataType:"json",
            success:function (msg){
                if(msg.code==200){
                    layer.msg("保存成功了",function (){
                        //清空cookie
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
                        $.removeCookie("userName",{domain:"localhost",path:"/crm"});
                        //页面跳转
                        window.parent.location.href=ctx+"/main";
                    })

                }else {
                    layer.msg(msg.msg)
                }
            }
        })

        return false;
    });
});