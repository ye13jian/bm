$(function () {

    $('#opinionSubmit').click(function () {

        var openid = $('#openid').val();
        var content = $('#content').val();
        var mobile = $('#mobile').val();

        if(content.length<2){
            alert('请输入您的意见或建议！');
            return;
        }

        var ctxPath = $('#ctxPath').val();

        $.ajax({
            url: ctxPath+'/mp/addOpinion',
            data:{
                openid:openid,
                content:content,
                mobile:mobile
                },
            dataType:'json',
            success:function (data) {
                alert('操作成功！');
                $('#content').val('');
                $('#mobile').val('');
            }

        })


    })

})