/**
 * 会员认证管理初始化
 */
var QrcodeAuth = {
    id: "QrcodeAuthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeAuth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '二维码', field: 'qrcode', visible: true, align: 'center', valign: 'middle'},
            {title: '书籍id', field: 'bookid', visible: true, align: 'center', valign: 'middle'},
            {title: '微信openid', field: 'openid', visible: true, align: 'center', valign: 'middle'},
            {title: '认证日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '认证时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
QrcodeAuth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeAuth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员认证
 */
QrcodeAuth.openAddQrcodeAuth = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员认证',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeAuth/qrcodeAuth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员认证详情
 */
QrcodeAuth.openQrcodeAuthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员认证详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeAuth/qrcodeAuth_update/' + QrcodeAuth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员认证
 */
QrcodeAuth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeAuth/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeAuth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrcodeAuthId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员认证列表
 */
QrcodeAuth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    QrcodeAuth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QrcodeAuth.initColumn();
    var table = new BSTable(QrcodeAuth.id, "/qrcodeAuth/list", defaultColunms);
    table.setPaginationType("client");
    QrcodeAuth.table = table.init();
});
