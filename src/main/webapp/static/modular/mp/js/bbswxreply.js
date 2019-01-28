$(function () {

    $('#addBbsReply').click(function () {

        var openid = $('#openid').val();
        var content = $('#content').val();
        var bbswxid = $('#bbswxid').val();

        if(content.length<2){
            alert('请输入您的回复！');
            return;
        }

        var ctxPath = $('#ctxPath').val();

        $.ajax({
            url: ctxPath+'/mp/addBbsWxReply',
            data:{
                openid:openid,
                content:content,
                bbswxid:bbswxid
                },
            dataType:'json',
            success:function (data) {
                alert('操作成功！');
                $('#content').val('');
                location.reload();
            }

        })


    })

})