/**
 * 初始化书籍管理详情对话框
 */
var MpInfoDlg = {
    mpInfoData : {}
};

/**
 * 清除数据
 */
MpInfoDlg.clearData = function() {
    this.mpInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MpInfoDlg.set = function(key, val) {
    this.mpInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MpInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 收集数据
 */
MpInfoDlg.collectData = function() {
    this
        .set('id').set('userid')
        .set('ad1_img').set('ad1_link')
        .set('ad2_img').set('ad2_link')
        .set('ad3_img').set('ad3_link')
        .set('ad4_img').set('ad4_link')
        .set('ad5_img').set('ad5_link')
        .set('ad6_img').set('ad6_link')
        .set('ad7_img').set('ad7_link')
        .set('ad8_img').set('ad8_link')
        .set('ad9_img').set('ad9_link')
        .set('ad10_img').set('ad10_link')
        .set('tz1_img').set('tz1_link').set('tz1_name')
        .set('tz2_img').set('tz2_link').set('tz2_name')
        .set('tz3_img').set('tz3_link').set('tz3_name')
        .set('tz4_img').set('tz4_link').set('tz4_name')
        .set('tz5_img').set('tz5_link').set('tz5_name')
        .set('tz6_img').set('tz6_link').set('tz6_name')
        .set('resource_img').set('collection_img').set('center_img')
        .set('integralmall_img').set('bbs_img').set('opinion_img')
        .set('rights').set('wxname').set('wxqrcode_img')
        .set('title').set('logo_img').set('restype').set('tel');
}

/**
 * 提交修改
 */
MpInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mpInfo/set", function(data){
        Feng.success("修改成功!");
        location.refresh();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mpInfoData);
    ajax.start();
}

/*
* 初始化方法中加入表单验证
*/
$(function() {

    //alert('初始化');

    // 初始化图片上传
    var avatarUp1 = new $WebUpload("ad1_img");
    avatarUp1.init();
    var avatarUp2 = new $WebUpload("ad2_img");
    avatarUp2.init();
    var avatarUp3 = new $WebUpload("ad3_img");
    avatarUp3.init();
    var avatarUp4 = new $WebUpload("ad4_img");
    avatarUp4.init();
    var avatarUp5 = new $WebUpload("ad5_img");
    avatarUp5.init();
    var avatarUp6 = new $WebUpload("ad6_img");
    avatarUp6.init();
    var avatarUp7 = new $WebUpload("ad7_img");
    avatarUp7.init();
    var avatarUp8 = new $WebUpload("ad8_img");
    avatarUp8.init();
    var avatarUp9 = new $WebUpload("ad9_img");
    avatarUp9.init();
    var avatarUp10 = new $WebUpload("ad10_img");
    avatarUp10.init();


    var avatarUptz1 = new $WebUpload("tz1_img");
    avatarUptz1.init();
    var avatarUptz2 = new $WebUpload("tz2_img");
    avatarUptz2.init();
    var avatarUptz3 = new $WebUpload("tz3_img");
    avatarUptz3.init();
    var avatarUptz4 = new $WebUpload("tz4_img");
    avatarUptz4.init();
    var avatarUptz5 = new $WebUpload("tz5_img");
    avatarUptz5.init();
    var avatarUptz6 = new $WebUpload("tz6_img");
    avatarUptz6.init();


    var avatarUp11 = new $WebUpload("resource_img");
    avatarUp11.init();
    var avatarUp12 = new $WebUpload("collection_img");
    avatarUp12.init();
    var avatarUp13 = new $WebUpload("center_img");
    avatarUp13.init();
    var avatarUp14 = new $WebUpload("integralmall_img");
    avatarUp14.init();
    var avatarUp15 = new $WebUpload("bbs_img");
    avatarUp15.init();
    var avatarUp16 = new $WebUpload("opinion_img");
    avatarUp16.init();

    var avatarUp17 = new $WebUpload("wxqrcode_img");
    avatarUp17.init();

    var avatarUp18 = new $WebUpload("logo_img");
    avatarUp18.init();



});