/**
 * 初始化书籍目录详情对话框
 */
var BookCatalogInfoDlg = {
    bookCatalogInfoData : {},
    //添加表单空输入提示 -- 对应input框
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '目录名不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
BookCatalogInfoDlg.clearData = function() {
    this.bookCatalogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookCatalogInfoDlg.set = function(key, val) {
    this.bookCatalogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookCatalogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BookCatalogInfoDlg.close = function() {
    parent.layer.close(window.parent.BookCatalog.layerIndex);
}

/**
 * 收集数据
 */
BookCatalogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('bookid')
    .set('name')
    .set('describes')
    .set('price')
    .set('restype')
    .set('resurl')
    .set('reslink')
    .set('status')
    .set('createdate')
    .set('createtime');
}


/*
* 验证数据是否为空
* */
BookCatalogInfoDlg.validate = function () {
    $('#bookCatalogInfoForm').data("bootstrapValidator").resetForm();
    $('#bookCatalogInfoForm').bootstrapValidator('validate');
    return $("#bookCatalogInfoForm").data('bootstrapValidator').isValid();
}



//如果选择文件，是否已经上传文件
var isUp = false;
//新增或是修改
var oprType;


//上传文件后跳转回来，选择是执行新增还是修改
function toOprFun(){
    //alert(oprType);
    if(oprType=="addSubmit"){
        oprSubmitAjax("/bookCatalog/add","添加");
        return;
    }
    if(oprType=="editSubmit"){
        oprSubmitAjax("/bookCatalog/update","修改");
        return;
    }
}




/**
 * 提交添加
 */
BookCatalogInfoDlg.addSubmit = function() {
    oprType = "addSubmit";

    if (!BookCatalogInfoDlg.validate()) {
        return;
    }

    //首先判断是否选择文件，一定在放在最上面，因为上传后会将名字写入hidden，下面才能collectData
    var console = $('#ossfile').html();
    //如果选择了文件，就在上传文件后执行addSubmitAxax();否则直接执行addSubmitAxax();
    if(console!=''){
        //如果没上传过就再调用上传，上传了，就跳过
        if(!isUp){
            isUp = true;
            $('#postfiles').click();
            return;
        }
    }
    //提交信息
    oprSubmitAjax("/bookCatalog/add","添加");
}



/**
 * 提交修改
 */
BookCatalogInfoDlg.editSubmit = function() {
    oprType = "editSubmit";

    if (!BookCatalogInfoDlg.validate()) {
        return;
    }

    //首先判断是否选择文件，一定在放在最上面，因为上传后会将名字写入hidden，下面才能collectData
    var console = $('#ossfile').html();
    //如果选择了文件，就在上传文件后执行addSubmitAxax();否则直接执行addSubmitAxax();
    if(console!=''){
        //如果没上传过就再调用上传，上传了，就跳过
        if(!isUp){
            isUp = true;
            $('#postfiles').click();
            return;
        }
    }
    //提交信息
    oprSubmitAjax("/bookCatalog/update","修改");
}

/**
 * 新增或编辑
 * @param oprURI
 * @param oprTxt
 */
function oprSubmitAjax(oprURI,oprTxt){
    //alert(oprURI+" "+oprTxt);
    BookCatalogInfoDlg.clearData();
    BookCatalogInfoDlg.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + oprURI, function(data){
        Feng.success(oprTxt+"成功!");
        window.parent.BookCatalog.table.refresh();
        BookCatalogInfoDlg.close();
    },function(data){
        Feng.error(oprTxt+"失败!" + data.responseJSON.message + "!");
    });
    ajax.set(BookCatalogInfoDlg.bookCatalogInfoData);
    ajax.start();
}









$(function() {

    //初始化表单验证
    Feng.initValidator("bookCatalogInfoForm", BookCatalogInfoDlg.validateFields);

    // 初始化图片上传
    //var avatarUp = new $WebUpload("resurl");
    //avatarUp.setUploadBarId("progressBar");
    //avatarUp.init();

    //以下是测试,可看avatar的内容
    //var objToStr = JSON.stringify(avatarUp)
    //console.log('objToStr:', objToStr);
    //alert(objToStr);

    //重新设定img尺寸,因为视频没有图片，设置为0
    //不用重新定义了，因为重写了resup.tag标签
    //$("#resurlPreId img").attr('width','0px');
    //$("#resurlPreId img").attr('height','0px');

});


//20181125,用于新增视频后可以立即播放
$(function(){
    //这里响应web_upload_object.js中的101行：$("#" + me.pictureId).change();,有了这句，下面的监听才会有效
    $("#resurl").on('change',function(){
        //监听到hidden新的值，即上传的视频的文件名
        var resurl = $('#resurl').val();
        //将文件名赋值给video,用于播放
        //$("#v_resurl").prop("src",Feng.ctxPath + "/kaptcha/kap?name="+resurl);


        //阿里云地址
        //$("#v_resurl").prop("src","http://wylrb.niqan.com/"+resurl);
        //$("#v_resurl").play();

    });
})
