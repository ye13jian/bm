$(function () {

    $('#addCollection').click(function () {

        var openid = $('#addCollection').attr('openid');
        var bookcatalogid = $('#addCollection').attr('bookcatalogid');
        var collstatus = $('#addCollection').attr('collstatus');

        //要在html中设置
        var ctxPath = $('#ctxPath').val();

        $.ajax({
            url: collstatus=='true'?''+ctxPath+'/mp/subCollection':''+ctxPath+'/mp/addCollection',
            data:{
                openid:openid,
                bookcatalogid:bookcatalogid
                },
            dataType:'json',
            success:function (data) {

                $('#collText').html(collstatus=='true'?"加入收藏":"取消收藏");
                $('#addCollection').attr('collstatus',collstatus=='true'?'false':'true');

            }

        })


    })

})