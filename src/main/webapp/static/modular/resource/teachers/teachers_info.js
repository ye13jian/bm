/**
 * 初始化师资管理详情对话框
 */
var TeachersInfoDlg = {
    teachersInfoData : {}
};

/**
 * 清除数据
 */
TeachersInfoDlg.clearData = function() {
    this.teachersInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeachersInfoDlg.set = function(key, val) {
    this.teachersInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeachersInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TeachersInfoDlg.close = function() {
    parent.layer.close(window.parent.Teachers.layerIndex);
}

/**
 * 收集数据
 */
TeachersInfoDlg.collectData = function() {
    this
    .set('id')
    .set('fullname')
    .set('mobile')
    .set('province')
    .set('city')
    .set('country')
    .set('school')
    .set('grades')
    .set('classes')
    .set('status')
    .set('remark');
}

/**
 * 提交添加
 */
TeachersInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teachers/add", function(data){
        Feng.success("添加成功!");
        window.parent.Teachers.table.refresh();
        TeachersInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teachersInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TeachersInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teachers/update", function(data){
        Feng.success("修改成功!");
        window.parent.Teachers.table.refresh();
        TeachersInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teachersInfoData);
    ajax.start();
}

$(function() {

});
