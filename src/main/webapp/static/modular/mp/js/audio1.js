
function updateCollection(bookcatalogid,collstatus){

    var openid = $('#openid').val();

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



        }

    })


}