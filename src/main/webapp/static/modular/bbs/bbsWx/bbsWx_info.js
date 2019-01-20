/**
 * 初始化用户帖子详情对话框
 */
var BbsWxInfoDlg = {
    bbsWxInfoData : {}
};

/**
 * 清除数据
 */
BbsWxInfoDlg.clearData = function() {
    this.bbsWxInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BbsWxInfoDlg.set = function(key, val) {
    this.bbsWxInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BbsWxInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BbsWxInfoDlg.close = function() {
    parent.layer.close(window.parent.BbsWx.layerIndex);
}

/**
 * 收集数据
 */
BbsWxInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('content')
    .set('imgurls')
    .set('openid')
    .set('createdate')
    .set('createtime')
    .set('lastReply');
}

/**
 * 提交添加
 */
BbsWxInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bbsWx/add", function(data){
        Feng.success("添加成功!");
        window.parent.BbsWx.table.refresh();
        BbsWxInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bbsWxInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BbsWxInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bbsWx/update", function(data){
        Feng.success("修改成功!");
        window.parent.BbsWx.table.refresh();
        BbsWxInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bbsWxInfoData);
    ajax.start();
}

$(function() {

});
