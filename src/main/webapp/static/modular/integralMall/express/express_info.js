/**
 * 初始化快递管理详情对话框
 */
var ExpressInfoDlg = {
    expressInfoData : {}
};

/**
 * 清除数据
 */
ExpressInfoDlg.clearData = function() {
    this.expressInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpressInfoDlg.set = function(key, val) {
    this.expressInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpressInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ExpressInfoDlg.close = function() {
    parent.layer.close(window.parent.Express.layerIndex);
}

/**
 * 收集数据
 */
ExpressInfoDlg.collectData = function() {
    this
    .set('id')
    .set('expCode')
    .set('expName')
    .set('logo')
    .set('tel')
    .set('status');
}

/**
 * 提交添加
 */
ExpressInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/express/add", function(data){
        Feng.success("添加成功!");
        window.parent.Express.table.refresh();
        ExpressInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.expressInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ExpressInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/express/update", function(data){
        Feng.success("修改成功!");
        window.parent.Express.table.refresh();
        ExpressInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.expressInfoData);
    ajax.start();
}

$(function() {

});
