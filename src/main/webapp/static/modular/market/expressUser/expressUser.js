/**
 * 我的快递管理初始化
 */
var ExpressUser = {
    id: "ExpressUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ExpressUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '快递名称', field: 'expName', visible: true, align: 'center', valign: 'middle'},
            {title: '快递编号', field: 'expCode', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ExpressUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ExpressUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的快递
 */
ExpressUser.openAddExpressUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的快递',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/expressUser/expressUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的快递详情
 */
ExpressUser.openExpressUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的快递详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/expressUser/expressUser_update/' + ExpressUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的快递
 */
ExpressUser.delete = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/expressUser/delete", function (data) {
                Feng.success("删除成功!");
                ExpressUser.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("expressUserId",ExpressUser.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除"+this.seItem.expName+"?",operation);
    }
};

/**
 * 查询我的快递列表
 */
ExpressUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ExpressUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ExpressUser.initColumn();
    var table = new BSTable(ExpressUser.id, "/expressUser/list", defaultColunms);
    table.setPaginationType("client");
    ExpressUser.table = table.init();
});
