/**
 * 初始化置顶文章详情对话框
 */
var BbsSysInfoDlg = {
    bbsSysInfoData : {},
    editor : null

};

/**
 * 清除数据
 */
BbsSysInfoDlg.clearData = function() {
    this.bbsSysInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BbsSysInfoDlg.set = function(key, val) {
    this.bbsSysInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BbsSysInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BbsSysInfoDlg.close = function() {
    parent.layer.close(window.parent.BbsSys.layerIndex);
}

/**
 * 收集数据
 */
BbsSysInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('content')
    .set('userid')
    .set('createuser');
}



/**
 * wangEditor
 */
BbsSysInfoDlg.editorSetBtn = function() {
    //这是设置编辑器内容
    BbsSysInfoDlg.editor.txt.html("dsafdfadsfdafdsfds");
}
BbsSysInfoDlg.editorGetBtn1 = function() {
    //获取编辑器的内容，带样式
    //一般使用这个获取数据，通过ajax发送给服务端　，然后服务端以Ｓtring接收，保存到数据库．
    var htmlstr = BbsSysInfoDlg.editor.txt.html();
    $('#content').val(htmlstr);

}
BbsSysInfoDlg.editorGetBtn2 = function() {
    //获取编辑器的内容，不带样式，纯文本
    var textstr = BbsSysInfoDlg.editor.txt.text();
    $('#content').val(textstr);
}


/**
 * 提交添加
 */
BbsSysInfoDlg.addSubmit = function() {

    var htmlstr = BbsSysInfoDlg.editor.txt.html();
    $('#content').val(htmlstr);

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bbsSys/add", function(data){
        Feng.success("添加成功!");
        window.parent.BbsSys.table.refresh();
        BbsSysInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    //alert('this:'+this.bbsSysInfoData['content']);
    ajax.set(this.bbsSysInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BbsSysInfoDlg.editSubmit = function() {

    var htmlstr = BbsSysInfoDlg.editor.txt.html();
    $('#content').val(htmlstr);

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bbsSys/update", function(data){
        Feng.success("修改成功!");
        window.parent.BbsSys.table.refresh();
        BbsSysInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bbsSysInfoData);
    ajax.start();
}

$(function() {


    //初始化编辑器
    var E = window.wangEditor;
    //这里的id为<div id="editor"中的id.
    //var editor = new E('#editorMenu','#editor');
    var editor = new E('#editorMenu','#editor');
    // 配置服务器端地址,也就是controller的请求路径，不带项目路径，前面没有/
    //editor.customConfig.uploadImgServer = 'commodity/upload/editor/img';
    //editor.customConfig.uploadImgServer = '/mgr/upload';
    //配置属性名称，绑定请求的图片数据
    //controller会用到，可以随便设置，但是一定要与controller一致
    //editor.customConfig.uploadFileName = 'img';

    // 隐藏“网络图片”tab
    editor.customConfig.showLinkImg = false;
    // 将图片大小限制为 1M
    editor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024;
    // 使用 base64 保存图片
    editor.customConfig.uploadImgShowBase64 = true;

    //创建编辑器
    editor.create();

    BbsSysInfoDlg.editor=editor;
    //$('#editor').attr('style','height:400px;');

    //var htmlstr = BbsSysInfoDlg.get('content');//input里获取html标签文本会有问题，所以放在label
    var htmlstr = $('#lbtext').text();
    //alert(htmlstr);
    BbsSysInfoDlg.editor.txt.html(htmlstr);
});
