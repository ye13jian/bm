/**
 * 快递管理管理初始化
 */
var Express = {
    id: "ExpressTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Express.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '快递编号', field: 'expCode', visible: true, align: 'center', valign: 'middle'},
            {title: '快递名称', field: 'expName', visible: true, align: 'center', valign: 'middle'},
            {title: 'logo图标', field: 'logo', visible: true, align: 'center', valign: 'middle'},
            {title: '服务电话', field: 'tel', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle', formatter:Express.statusFormat}
    ];
};

Express.statusFormat = function(value,row,index){
    if(value==1){
        return '<span style="color:darkgreen;">启用</span>';
    }else{
        return '';
    }
}

/**
 * 检查是否选中
 */
Express.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Express.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加快递管理
 */
Express.openAddExpress = function () {
    var index = layer.open({
        type: 2,
        title: '添加快递管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/express/express_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看快递管理详情
 */
Express.openExpressDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '快递管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/express/express_update/' + Express.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除快递管理
 */
Express.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/express/delete", function (data) {
            Feng.success("删除成功!");
            Express.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("expressId",this.seItem.id);
        ajax.start();
    }
};

Express.startExpress = function () {
    updateStatus(1);
};
Express.stopExpress = function () {
    updateStatus(0);
};
function updateStatus(status){
    if (Express.check()) {
        var ajax = new $ax(Feng.ctxPath + "/express/updateStatus", function (data) {
            Feng.success("修改成功!");
            Express.table.refresh();
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set("expressId",Express.seItem.id);
        ajax.set("status",status);
        ajax.start();
    }
};

/**
 * 查询快递管理列表
 */
Express.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Express.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Express.initColumn();
    var table = new BSTable(Express.id, "/express/list", defaultColunms);
    table.setPaginationType("client");
    Express.table = table.init();
});
