/**
 * 初始化书籍管理详情对话框
 */
var BookInfoDlg = {
    bookInfoData : {},
    //添加表单空输入提示 -- 对应input框
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '书籍名称不能为空'
                }
            }
        },
        author: {
            validators: {
                notEmpty: {
                    message: '作者不能为空'
                }
            }
        },
        editor: {
            validators: {
                notEmpty: {
                    message: '编辑不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
BookInfoDlg.clearData = function() {
    this.bookInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookInfoDlg.set = function(key, val) {
    this.bookInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BookInfoDlg.close = function() {
    parent.layer.close(window.parent.Book.layerIndex);
}

/**
 * 收集数据
 */
BookInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('category')
    .set('series')
    .set('isbn')
    .set('imgurl')
    .set('author')
    .set('editor')
    .set('price')
    .set('publicationdate')
    .set('publicationpress')
    .set('describes')
    .set('createuser')
    .set('status')
    .set('createdate')
    .set('createtime');
}

/*
* 验证数据是否为空
* */
BookInfoDlg.validate = function () {
    $('#bookInfoForm').data("bootstrapValidator").resetForm();
    $('#bookInfoForm').bootstrapValidator('validate');
    return $("#bookInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
BookInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //最后在提交添加的方法中加入以下判断，目的是在点击提交时，判断是否进行了表单判空验证。
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/book/add", function(data){
        Feng.success("添加成功!");
        window.parent.Book.table.refresh();
        BookInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BookInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/book/update", function(data){
        Feng.success("修改成功!");
        window.parent.Book.table.refresh();
        BookInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookInfoData);
    ajax.start();
}

/*
* 初始化方法中加入表单验证
*/
$(function() {
    //初始化表单验证
    Feng.initValidator("bookInfoForm", BookInfoDlg.validateFields);

    // 初始化图片上传
    var avatarUp = new $WebUpload("imgurl");
    //avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

    //alert('初始化');
    //重新设定img尺寸,要求正方形，便于后面手机展示。书本原210X297
    $("#imgurlPreId img").attr('width','248px');
    $("#imgurlPreId img").attr('height','248px');

});