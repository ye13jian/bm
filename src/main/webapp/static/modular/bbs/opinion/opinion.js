/**
 * 意见建议管理初始化
 */
var Opinion = {
    id: "OpinionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Opinion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '意见内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '微信openid', field: 'openid', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '创建日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Opinion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Opinion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加意见建议
 */
Opinion.openAddOpinion = function () {
    var index = layer.open({
        type: 2,
        title: '添加意见建议',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/opinion/opinion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看意见建议详情
 */
Opinion.openOpinionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '意见建议详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/opinion/opinion_update/' + Opinion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除意见建议
 */
Opinion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/opinion/delete", function (data) {
            Feng.success("删除成功!");
            Opinion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("opinionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询意见建议列表
 */
Opinion.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Opinion.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Opinion.initColumn();
    var table = new BSTable(Opinion.id, "/opinion/list", defaultColunms);
    table.setPaginationType("client");
    Opinion.table = table.init();
});
