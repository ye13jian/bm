/**
 * 置顶文章管理初始化
 */
var BbsSys = {
    id: "BbsSysTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BbsSys.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            //{title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            //{title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建用户', field: 'createuser', visible: true, align: 'center', valign: 'middle'},
            {title: '创建日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BbsSys.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BbsSys.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加置顶文章
 */
BbsSys.openAddBbsSys = function () {
    var index = layer.open({
        type: 2,
        title: '添加置顶文章',
        area: ['800px', '670px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bbsSys/bbsSys_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看置顶文章详情
 */
BbsSys.openBbsSysDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '置顶文章详情',
            area: ['800px', '670px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bbsSys/bbsSys_update/' + BbsSys.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除置顶文章
 */
BbsSys.delete = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/bbsSys/delete", function (data) {
                Feng.success("删除成功!");
                BbsSys.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("bbsSysId",BbsSys.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除【"+this.seItem.title+"】?",operation);
    }
};

/**
 * 查询置顶文章列表
 */
BbsSys.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BbsSys.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BbsSys.initColumn();
    var table = new BSTable(BbsSys.id, "/bbsSys/list", defaultColunms);
    table.setPaginationType("client");
    BbsSys.table = table.init();
});
