/**
 * 书籍目录管理初始化
 */
var BookCatalog = {
    id: "BookCatalogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    bookid: 0
};

/**
 * 初始化表格的列
 */
BookCatalog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '书籍的id', field: 'bookid', visible: true, align: 'center', valign: 'middle'},
            {title: '目录名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'describes', visible: true, align: 'center', valign: 'middle'},
            //{title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            //{title: '资源类型', field: 'restype', visible: true, align: 'center', valign: 'middle'},
            //{title: '资源地址', field: 'resurl', visible: true, align: 'center', valign: 'middle'},
            {title: '外链地址', field: 'reslink', visible: true, align: 'center', valign: 'middle'}
            //{title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            //{title: '创建日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'}
            //{title: '创建时间2', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BookCatalog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BookCatalog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加书籍目录
 */
BookCatalog.openAddBookCatalog = function () {

    if(BookCatalog.bookid == 0){
        Feng.info("请先选中一本书籍！");
        return false;
    }

    var index = layer.open({
        type: 2,
        title: '添加书籍目录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bookCatalog/bookCatalog_add/' + BookCatalog.bookid
    });
    this.layerIndex = index;
};

/**
 * 打开查看书籍目录详情
 */
BookCatalog.openBookCatalogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '书籍目录详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bookCatalog/bookCatalog_update/' + BookCatalog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除书籍目录
 */
BookCatalog.delete = function () {
    /*if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bookCatalog/delete", function (data) {
            Feng.success("删除成功!");
            BookCatalog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bookCatalogId",this.seItem.id);
        ajax.start();
    }*/


    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/bookCatalog/delete", function (data) {
                Feng.success("删除成功!");
                BookCatalog.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("bookCatalogId",BookCatalog.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除【"+this.seItem.name+"】?",operation);
    }





};

/**
 * 生成二维码
 */
BookCatalog.qrcode = function () {

    if(BookCatalog.bookid == 0){
        Feng.info("请先选中一本书籍！");
        return false;
    }
    location.href = Feng.ctxPath + '/bookCatalog/qrcode/'+BookCatalog.bookid;

        //var ajax = new $ax(Feng.ctxPath + "/bookCatalog/qrcode/"+BookCatalog.bookid, function (data) {
            //Feng.success("生成成功!");
        //}, function (data) {
            //Feng.error("生成失败!" + data.responseJSON.message + "!");
        //});
        //ajax.start();
};



/**
 * 查询书籍目录列表
 */
BookCatalog.search = function () {
    var queryData = {};
    queryData['bookid'] = BookCatalog.bookid;
    queryData['condition'] = $("#condition").val();
    BookCatalog.table.refresh({query: queryData});
};

/**
 * ztree点击事件
 * @param e
 * @param treeId
 * @param treeNode
 */
BookCatalog.onClickBook = function (e, treeId, treeNode) {
    BookCatalog.bookid = treeNode.id;
    BookCatalog.search();
};

$(function () {
    var defaultColunms = BookCatalog.initColumn();
    var table = new BSTable(BookCatalog.id, "/bookCatalog/list", defaultColunms);
    table.setPaginationType("client");//分页
    BookCatalog.table = table.init();
    //alert(JSON.stringify(BookCatalog));

    //初始化书籍列表
    var ztree = new $ZTree("bookTree", "/book/tree");
    ztree.bindOnClick(BookCatalog.onClickBook);
    ztree.init();
});
