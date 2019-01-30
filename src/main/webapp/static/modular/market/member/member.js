/**
 * 会员信息管理初始化
 */
var Member = {
    id: "MemberTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Member.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            //{title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '微信头像', field: 'headimgurl', visible: true, align: 'center', valign: 'middle', formatter:Member.imgFormat},
            {title: '微信昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            //{title: '微信id', field: 'openid', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号码', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'fullname', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '省份', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            //{title: '详细地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '积分值', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '有效期', field: 'validitydate', visible: true, align: 'center', valign: 'middle'},
            //{title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            //{title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建日期', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

Member.imgFormat = function(value,row,index){
    return '<img src='+value+' style="height:32px;" />';
}

/**
 * 检查是否选中
 */
Member.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Member.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员信息
 */
Member.openAddMember = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/member/member_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员信息详情
 */
Member.openMemberDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/member/member_update/' + Member.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员信息
 */
Member.delete = function () {
    if (this.check()) {
        var operation = function(){

            var ajax = new $ax(Feng.ctxPath + "/member/delete", function (data) {
                Feng.success("删除成功!");
                Member.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("memberId",Member.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除会员【"+this.seItem.nickname+"】?",operation);
    }
};

/**
 * 查询会员信息列表
 */
Member.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Member.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Member.initColumn();
    var table = new BSTable(Member.id, "/member/list", defaultColunms);
    table.setPaginationType("client");
    Member.table = table.init();
});
