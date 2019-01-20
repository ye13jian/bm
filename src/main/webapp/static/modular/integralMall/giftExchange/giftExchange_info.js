/**
 * 初始化礼品兑换详情对话框
 */
var GiftExchangeInfoDlg = {
    giftExchangeInfoData : {},
    validateFields: {
        express: {
            validators: {
                notEmpty: {
                    message: '快递不能为空'
                }
            }
        },
        expressno: {
            validators: {
                notEmpty: {
                    message: '快递单号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
GiftExchangeInfoDlg.clearData = function() {
    this.giftExchangeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftExchangeInfoDlg.set = function(key, val) {
    this.giftExchangeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftExchangeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GiftExchangeInfoDlg.close = function() {
    parent.layer.close(window.parent.GiftExchange.layerIndex);
}

/**
 * 收集数据
 */
GiftExchangeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('express')
    .set('expressno')
    .set('expressdate')
    .set('openid')
    .set('status')
    .set('remark');
}

/*
* 验证数据是否为空
* */
GiftExchangeInfoDlg.validate = function () {
    $('#giftExchangeInfoForm').data("bootstrapValidator").resetForm();
    $('#giftExchangeInfoForm').bootstrapValidator('validate');
    return $("#giftExchangeInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
GiftExchangeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/giftExchange/add", function(data){
        Feng.success("添加成功!");
        window.parent.GiftExchange.table.refresh();
        GiftExchangeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftExchangeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GiftExchangeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //最后在提交添加的方法中加入以下判断，目的是在点击提交时，判断是否进行了表单判空验证。
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/giftExchange/update", function(data){
        Feng.success("修改成功!");
        window.parent.GiftExchange.table.refresh();
        GiftExchangeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftExchangeInfoData);
    ajax.start();
}

$(function() {

    //初始化表单验证
    Feng.initValidator("giftExchangeInfoForm", GiftExchangeInfoDlg.validateFields);

});
