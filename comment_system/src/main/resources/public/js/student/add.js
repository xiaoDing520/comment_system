layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    $(function () {
        //点击事件
        $('#submit').click(function () {
            $(function(){
                //请求参数
                var list = {};
                //
                $.ajax({
                    //请求方式
                    type : "post",
                    data: {
                        "subId": $("input[name=subId]").val(),
                        "userId": $("input[name=userId]").val(),
                        "manner": $("input[name=manner]").val(),
                        "atmosphere": $("input[name=atmosphere]").val(),
                        "quality": $("input[name=quality]").val()
                    },
                    //请求地址
                    url : ctx+"/scoreController/add",
                    //数据，json字符串
                    //请求成功
                    success : function(result) {
                        console.log(result);
                    },
                    //请求失败，包含具体的错误信息
                    error : function(e){
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });
            });

            // $.ajax({
            //     type: "post",
            //     url: ctx+"/scoreController/add",
            //     dataType: "json",
            //     success: function (data) {
            //         console.log(data.msg)
            //     }
            // })
            //使用ajax传输数据
            // $.ajax({
            //     type: "post",
            //     url: ctx + "/scoreController/add",
            //     data: {
            //         "manner": $("input[name=manner]").val(),
            //         "atmosphere": $("input[name=atmosphere]").val(),
            //         "quality": $("input[name=quality]").val()
            //     },
            //     dataType: "json",
            //     success: function (data) {
            //         if(data.code==200){
            //             layer.msg("评价成功",{icon : 6 });
            //             layer.close(index);
            //             //重新加载数据
            //             table.reload();
            //         }else{
            //             layer.msg(data.msg,{icon : 5 });
            //         }
            //     }
            //
            // });
            window.parent.location.reload();

        })









        $("#closeBtn").click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        })

    });
})

