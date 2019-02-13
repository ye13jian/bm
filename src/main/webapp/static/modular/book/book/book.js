/**
 * 书籍管理管理初始化
 */
var Book = {
    id: "BookTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Book.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '书名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '书籍类别', field: 'category', visible: true, align: 'center', valign: 'middle'},
            {title: '书籍系列', field: 'series', visible: true, align: 'center', valign: 'middle'},
            //{title: 'ISBN号', field: 'isbn', visible: true, align: 'center', valign: 'middle'},
            //{title: '书籍图片', field: 'imgurl', visible: true, align: 'center', valign: 'middle'},
            {title: '作者', field: 'author', visible: true, align: 'center', valign: 'middle'},
            {title: '编辑', field: 'editor', visible: true, align: 'center', valign: 'middle'},
            //{title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '积分', field: 'score', visible: true, align: 'center', valign: 'middle'},
            //{title: '出版日期', field: 'publicationdate', visible: true, align: 'center', valign: 'middle'},
            //{title: '出版社', field: 'publicationpress', visible: true, align: 'center', valign: 'middle'},
            //{title: '书籍描述', field: 'describes', visible: true, align: 'center', valign: 'middle'},
            //{title: '创建人', field: 'createuser', visible: true, align: 'center', valign: 'middle'},
            //{title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Book.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Book.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加书籍管理
 */
Book.openAddBook = function () {
    var index = layer.open({
        type: 2,
        title: '添加书籍管理',
        area: ['800px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/book/book_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看书籍管理详情
 */
Book.openBookDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '书籍管理详情',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/book/book_update/' + Book.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除书籍管理
 */
Book.delete = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/book/delete", function (data) {
                Feng.success("删除成功!");
                Book.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("bookId",Book.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除《"+this.seItem.name+"》?",operation);
    }
};

/**
 * 书籍详情
 */
Book.detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '书籍管理详情',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/book/detail/' + Book.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 查询书籍管理列表
 */
Book.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Book.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Book.initColumn();
    var table = new BSTable(Book.id, "/book/list", defaultColunms);
    table.setPaginationType("client");
    Book.table = table.init();
});
