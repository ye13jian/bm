/**
 * 师资管理管理初始化
 */
var Teachers = {
    id: "TeachersTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Teachers.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'fullname', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '省份', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '县区', field: 'country', visible: true, align: 'center', valign: 'middle'},
            {title: '学校', field: 'school', visible: true, align: 'center', valign: 'middle'},
            {title: '年级', field: 'grades', visible: true, align: 'center', valign: 'middle'},
            {title: '班级', field: 'classes', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Teachers.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Teachers.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加师资管理
 */
Teachers.openAddTeachers = function () {
    var index = layer.open({
        type: 2,
        title: '添加师资管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/teachers/teachers_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看师资管理详情
 */
Teachers.openTeachersDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '师资管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teachers/teachers_update/' + Teachers.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除师资管理
 */
Teachers.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/teachers/delete", function (data) {
            Feng.success("删除成功!");
            Teachers.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("teachersId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询师资管理列表
 */
Teachers.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Teachers.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Teachers.initColumn();
    var table = new BSTable(Teachers.id, "/teachers/list", defaultColunms);
    table.setPaginationType("client");
    Teachers.table = table.init();
});
