layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    //添加学生用户
    /*form.on("submit(addUser)",function (data){
    var url="/comment/save";

    $.post(url,data.field,function (result){
        if (result.code=200){
            layer.msg("注册成功了",{icon:1,time:1500, shade:0.4});
            window.location.href=ctx+"/index";
        }
        else {
            layer.msg(result.msg,{icon: 5});
        }

    },"json");return false;
})*/
    form.on("submit(addUser)", function (data) {
        var url = "/comment/save";

        $.post(url, data.field, function (result) {
            if (result.code = 200) {
                setTimeout(function () {
                    layer.msg("注册成功了");
                    window.location.href = ctx + "/index";
                }, 500)
            } else {
                layer.msg(result.msg, {icon: 5});
            }
        }, "json");
        return false;
    })
});
/*    /!*取消*!/

    $("#closeBtn").click(function(){
        //假设这是iframe页
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });*/
   // return false;



