function toast(mess,time){
    //alert(mess);
    var str='<div id="toast"></div>';
    $("body").append(str);
    $("#toast").fadeIn().text(mess);

    $("#toast").css({
        "width": "180px",
        "height": "45px",
        "text-align": "center",
        "position": "absolute",
        "background": "rgba(0,0,0, 0.6)",
        "left": "50%",
        "top": "85%",
        "margin": "-45px 0 0 -90px",
        "border-radius": "5px",
        "color": "#fff",
        "line-height": "45px",
        "font-size": "0.8rem",
        "z-index": "9999999"
    });

    setTimeout(function(){
        $("#toast").fadeOut();
    },time)
}