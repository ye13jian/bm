/**
 * 用户帖子管理初始化
 */
var BbsWx = {
    id: "BbsWxTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BbsWx.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            //{title: '图片', field: 'imgurls', visible: true, align: 'center', valign: 'middle'},
            {title: '微信openid', field: 'openid', visible: true, align: 'center', valign: 'middle'},
            {title: '创建日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '最后回复时间', field: 'lastReply', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BbsWx.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BbsWx.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户帖子
 */
BbsWx.openAddBbsWx = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户帖子',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bbsWx/bbsWx_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户帖子详情
 */
BbsWx.openBbsWxDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户帖子详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bbsWx/bbsWx_update/' + BbsWx.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户帖子
 */
BbsWx.delete = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/bbsWx/delete", function (data) {
                Feng.success("删除成功!");
                BbsWx.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("bbsWxId",BbsWx.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除【"+this.seItem.id+"】?",operation);
    }
};

/**
 * 查询用户帖子列表
 */
BbsWx.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BbsWx.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BbsWx.initColumn();
    var table = new BSTable(BbsWx.id, "/bbsWx/list", defaultColunms);
    table.setPaginationType("client");
    BbsWx.table = table.init();
});
