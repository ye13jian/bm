/**
 * 初始化意见建议详情对话框
 */
var OpinionInfoDlg = {
    opinionInfoData : {}
};

/**
 * 清除数据
 */
OpinionInfoDlg.clearData = function() {
    this.opinionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OpinionInfoDlg.set = function(key, val) {
    this.opinionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OpinionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OpinionInfoDlg.close = function() {
    parent.layer.close(window.parent.Opinion.layerIndex);
}

/**
 * 收集数据
 */
OpinionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('content')
    .set('openid')
    .set('mobile')
    .set('createdate')
    .set('createtime');
}

/**
 * 提交添加
 */
OpinionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/opinion/add", function(data){
        Feng.success("添加成功!");
        window.parent.Opinion.table.refresh();
        OpinionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.opinionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OpinionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/opinion/update", function(data){
        Feng.success("修改成功!");
        window.parent.Opinion.table.refresh();
        OpinionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.opinionInfoData);
    ajax.start();
}

$(function() {

});
