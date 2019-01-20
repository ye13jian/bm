/**
 * 礼品兑换管理初始化
 */
var GiftExchange = {
    id: "GiftExchangeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GiftExchange.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '日期', field: 'date', visible: true, align: 'center', valign: 'middle',formatter:GiftExchange.dateFormat},
            {title: '时间', field: 'time', visible: true, align: 'center', valign: 'middle',formatter:GiftExchange.timeFormat},
            {title: '礼品', field: 'giftname', visible: true, align: 'center', valign: 'middle'},
            {title: '数量', field: 'count', visible: true, align: 'center', valign: 'middle'},
            {title: '分值', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'fullname', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',formatter:GiftExchange.statusFormat}
    ];
};

GiftExchange.dateFormat = function(value,row,index){
    return value.substr(0,10);
}
GiftExchange.timeFormat = function(value,row,index){
    return value.substr(value.length-8,8);
}
GiftExchange.statusFormat = function(value,row,index){
    if(value==2){
        return '<span style="color:darkgreen;">已发货</span>';
    }
    if(value==0){
        return '<span style="color:darkred;">已关闭</span>';
    }
}


/**
 * 检查是否选中
 */
GiftExchange.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GiftExchange.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加礼品兑换
 */
GiftExchange.openAddGiftExchange = function () {
    var index = layer.open({
        type: 2,
        title: '添加礼品兑换',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/giftExchange/giftExchange_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看礼品兑换详情
 */
GiftExchange.openGiftExchangeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '礼品发货',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/giftExchange/giftExchange_update/' + GiftExchange.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除礼品兑换
 */
GiftExchange.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/giftExchange/delete", function (data) {
            Feng.success("删除成功!");
            GiftExchange.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("giftExchangeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询礼品兑换列表
 */
GiftExchange.search = function () {
    var queryData = {};
    queryData['startdate'] = $("#startdate").val();
    queryData['enddate'] = $("#enddate").val();
    queryData['condition'] = $("#condition").val();
    GiftExchange.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = GiftExchange.initColumn();
    var table = new BSTable(GiftExchange.id, "/giftExchange/list", defaultColunms);
    table.setPaginationType("client");
    GiftExchange.table = table.init();
});
