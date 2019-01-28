/**
 * 初始化会员认证详情对话框
 */
var QrcodeAuthInfoDlg = {
    qrcodeAuthInfoData : {}
};

/**
 * 清除数据
 */
QrcodeAuthInfoDlg.clearData = function() {
    this.qrcodeAuthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeAuthInfoDlg.set = function(key, val) {
    this.qrcodeAuthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeAuthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeAuthInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeAuth.layerIndex);
}

/**
 * 收集数据
 */
QrcodeAuthInfoDlg.collectData = function() {
    this
    .set('id')
    .set('qrcode')
    .set('bookid')
    .set('openid')
    .set('createdate')
    .set('createtime');
}

/**
 * 提交添加
 */
QrcodeAuthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeAuth/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeAuth.table.refresh();
        QrcodeAuthInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeAuthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeAuthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeAuth/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeAuth.table.refresh();
        QrcodeAuthInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeAuthInfoData);
    ajax.start();
}

$(function() {

});
