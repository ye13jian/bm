@/*
    音视频资源上传的说明:
    name : 名称
    id : 头像的id
@*/

<div id="${id}PreId" style="display: none;">
    <img width="0px" height="0px">
</div>
<div class="upload-btn" id="${id}BtnId">
    <i class="fa fa-upload"></i>&nbsp;上传
</div>
<input type="hidden" id="${id}" value="${resurl!}"/>

@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


