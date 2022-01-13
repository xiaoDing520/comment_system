layui.use(['table','treetable'], function(){
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;

    //数据加载
    treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#moudleList',
        url: ctx+"/moudleController/list",
        toolbar: '#toolbarDemo',
        treeDefaultClose: true,
        page: true,
        cols:[[
            {type: 'numbers'},
            {field: 'name', minWidth: 100, title: '菜单名称'},
            {field: 'url', title: '权限码'},
            {field: 'optValue', title: '权限码'},
            {field: 'createDate', title: '创建时间'},
            {field: 'updateDate', title: '修改时间'},
            {templet: '#auth-state', width: 180, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    })

    table.on('toolbar(moudleList)', function(obj){
        console.log("点击时间");
        switch(obj.event){
            case "expand":
                treeTable.expandAll('#moudleList');
                break;
            case "fold":
                treeTable.foldAll('#moudleList');
                break;
        };
    });
});