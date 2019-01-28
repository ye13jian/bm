$(function () {

    $('#addBbsWx').click(function () {

        var openid = $('#openid').val();
        var content = $('#content').val();
        var title = $('#title').val();

        if(title.length<2||content.length<5){
            alert('请输入您的帖子！');
            return;
        }

        var ctxPath = $('#ctxPath').val();

        $.ajax({
            url: ctxPath+'/mp/addBbsWx',
            data:{
                openid:openid,
                content:content,
                title:title
                },
            dataType:'json',
            success:function (data) {
                alert('操作成功！');
                $('#title').val('');
                $('#content').val('');
                //location.reload();
            }

        })

    })

})