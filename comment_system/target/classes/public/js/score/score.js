layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

   /* alert("sssss")*/
    //角色列表展示
    var tableIns = table.render({
        elem: '#scoreList',
        url: ctx + '/scoreController/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "scoreList",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'userName', title: '教师名', minWidth: 50, align: "center"},
            {field: 'subName', title: '课程名', minWidth: 50, align: "center"},
            {field: 'avgManner',title: '教学态度',minWidth: 50 ,align: "center"},
            {field: 'avgAtmosphere',title: '教学氛围',minWidth: 50 ,align: "center"},
            {field: 'avgQuality',title: '教学质量',minWidth: 50 ,align: "center"},
            // {title: '操作', minWidth: 150, templet: '#scoreListBar', fixed: "right", align: "center"}
        ]]
    });
})
