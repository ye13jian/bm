/**
 * 初始化礼品管理详情对话框
 */
var GiftInfoDlg = {
    giftInfoData : {}
};

/**
 * 清除数据
 */
GiftInfoDlg.clearData = function() {
    this.giftInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftInfoDlg.set = function(key, val) {
    this.giftInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GiftInfoDlg.close = function() {
    parent.layer.close(window.parent.Gift.layerIndex);
}

/**
 * 收集数据
 */
GiftInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('attributes')
    .set('describes')
    .set('imgurl1')
    .set('imgurl2')
    .set('imgurl3')
    .set('imgurl4')
    .set('score')
    .set('stock')
    .set('sale');
}

/**
 * 提交添加
 */
GiftInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/add", function(data){
        Feng.success("添加成功!");
        window.parent.Gift.table.refresh();
        GiftInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GiftInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/update", function(data){
        Feng.success("修改成功!");
        window.parent.Gift.table.refresh();
        GiftInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftInfoData);
    ajax.start();
}


/**
 * 删除图片
 */
function delImg(delImgId) {
    //alert(delImgId);
    $('#'+delImgId).val('');
    $('#img_'+delImgId).attr('src',Feng.ctxPath+'/static/img/noimg.png');
    //$('#img_'+delImgId).attr('src','');
}

$(function() {
    var avatarUp = new $WebUpload("imgurl");
    avatarUp.init();
    $("#imgurlPreId img").attr('width','0px');
    $("#imgurlPreId img").attr('height','0px');
});


$(function(){
    //alert(2);
    //这里响应web_upload_object.js中的101行：$("#" + me.pictureId).change();,有了这句，下面的监听才会有效
    $("#imgurl").on('change',function(){
        //监听到hidden新的值，即上传的视频的文件名
        var imgurl = $('#imgurl').val();
        //alert(imgurl);

        var imgurls = $("input[name='imgurls']");
        //alert(imgurls.length);
        var flag = false;
        for(var i=0;i<imgurls.length;i++){
            var val = $('#'+imgurls[i].id).val();
            //alert(val);
            if(val==''){
                $('#'+imgurls[i].id).val(imgurl);
                $('#img_'+imgurls[i].id).attr('src',Feng.ctxPath+'/kaptcha/kap?name='+imgurl);
                flag = true;
                break;
            }
        }
        if(!flag){
            Feng.error("最多上传4张!");
        }
    });
})
