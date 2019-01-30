/**
 * 初始化我的快递详情对话框
 */
var ExpressUserInfoDlg = {
    expressUserInfoData : {},
    validateFields: {
        expCode: {
            validators: {
                notEmpty: {
                    message: '快递不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ExpressUserInfoDlg.clearData = function() {
    this.expressUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpressUserInfoDlg.set = function(key, val) {
    this.expressUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpressUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ExpressUserInfoDlg.close = function() {
    parent.layer.close(window.parent.ExpressUser.layerIndex);
}

/**
 * 收集数据
 */
ExpressUserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('expCode')
    .set('userid');
}

/*
* 验证数据是否为空
* */
ExpressUserInfoDlg.validate = function () {
    $('#expressUserInfoForm').data("bootstrapValidator").resetForm();
    $('#expressUserInfoForm').bootstrapValidator('validate');
    return $("#expressUserInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
ExpressUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //最后在提交添加的方法中加入以下判断，目的是在点击提交时，判断是否进行了表单判空验证。
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/expressUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.ExpressUser.table.refresh();
        ExpressUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.expressUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ExpressUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/expressUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.ExpressUser.table.refresh();
        ExpressUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.expressUserInfoData);
    ajax.start();
}

$(function() {
    //初始化表单验证
    Feng.initValidator("expressUserInfoForm", ExpressUserInfoDlg.validateFields);
});
