/**
 * 初始化积分管理详情对话框
 */
var GiftIntegralInfoDlg = {
    giftIntegralInfoData : {}
};

/**
 * 清除数据
 */
GiftIntegralInfoDlg.clearData = function() {
    this.giftIntegralInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftIntegralInfoDlg.set = function(key, val) {
    this.giftIntegralInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftIntegralInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GiftIntegralInfoDlg.close = function() {
    parent.layer.close(window.parent.GiftIntegral.layerIndex);
}

/**
 * 收集数据
 */
GiftIntegralInfoDlg.collectData = function() {
    this
    .set('id')
    .set('date')
    .set('time')
    .set('message')
    .set('score')
    .set('openid');
}

/**
 * 提交添加
 */
GiftIntegralInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/giftIntegral/add", function(data){
        Feng.success("添加成功!");
        window.parent.GiftIntegral.table.refresh();
        GiftIntegralInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftIntegralInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GiftIntegralInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/giftIntegral/update", function(data){
        Feng.success("修改成功!");
        window.parent.GiftIntegral.table.refresh();
        GiftIntegralInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftIntegralInfoData);
    ajax.start();
}

$(function() {

});
