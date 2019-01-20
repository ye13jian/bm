/**
 * 礼品管理管理初始化
 */
var Gift = {
    id: "GiftTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Gift.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            /*{title: '参数', field: 'attributes', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'describes', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'imgurl1', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'imgurl2', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'imgurl3', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'imgurl4', visible: true, align: 'center', valign: 'middle'},*/
            {title: '分值', field: 'score', visible: true, align: 'center', valign: 'middle',},
            {title: '库存', field: 'stock', visible: true, align: 'center', valign: 'middle'},
            {title: '销量', field: 'sale', visible: true, align: 'center', valign: 'middle',},
            /*{title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},*/
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',formatter:Gift.statusFormat},
            {title: '创建日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

Gift.statusFormat = function(value,row,index){
    if(value==1){
        return '<span style="color:darkgreen;">上架</span>';
    }else{
        return '<span style="color:darkred;">下架</span>';
    }
}

/**
 * 检查是否选中
 */
Gift.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Gift.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加礼品管理
 */
Gift.openAddGift = function () {
    var index = layer.open({
        type: 2,
        title: '添加礼品管理',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/gift/gift_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看礼品管理详情
 */
Gift.openGiftDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '礼品管理详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/gift/gift_update/' + Gift.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除礼品管理
 */
Gift.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/gift/delete", function (data) {
            Feng.success("删除成功!");
            Gift.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("giftId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询礼品管理列表
 */
Gift.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Gift.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Gift.initColumn();
    var table = new BSTable(Gift.id, "/gift/list", defaultColunms);
    table.setPaginationType("client");
    Gift.table = table.init();
});
