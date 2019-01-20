/**
 * 积分管理管理初始化
 */
var GiftIntegral = {
    id: "GiftIntegralTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GiftIntegral.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '日期', field: 'date', visible: true, align: 'center', valign: 'middle'},
            {title: '时间', field: 'time', visible: true, align: 'center', valign: 'middle'},
            {title: '信息', field: 'message', visible: true, align: 'center', valign: 'middle'},
            {title: '分值', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'fullname', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
GiftIntegral.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GiftIntegral.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加积分管理
 */
GiftIntegral.openAddGiftIntegral = function () {
    var index = layer.open({
        type: 2,
        title: '添加积分管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/giftIntegral/giftIntegral_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看积分管理详情
 */
GiftIntegral.openGiftIntegralDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '积分管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/giftIntegral/giftIntegral_update/' + GiftIntegral.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除积分管理
 */
GiftIntegral.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/giftIntegral/delete", function (data) {
            Feng.success("删除成功!");
            GiftIntegral.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("giftIntegralId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询积分管理列表
 */
GiftIntegral.search = function () {
    var queryData = {};
    queryData['startdate'] = $("#startdate").val();
    queryData['enddate'] = $("#enddate").val();
    queryData['condition'] = $("#condition").val();
    GiftIntegral.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = GiftIntegral.initColumn();
    var table = new BSTable(GiftIntegral.id, "/giftIntegral/list", defaultColunms);
    table.setPaginationType("client");
    GiftIntegral.table = table.init();
});
