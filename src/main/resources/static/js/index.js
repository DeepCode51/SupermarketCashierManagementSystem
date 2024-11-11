var urlDate='';
$(function(){
    if(!sessionStorage.getItem("goodsFLs")){
        $.ajax("/goodsCategory/listAll",{
            method:"post",
            headers:{
                contentType: "application/x-www-form-urlencoded",
            },
            success:(res)=>{
                sessionStorage.setItem("goodsFLs",JSON.stringify(res.rows));
            }
        })

    }
    //菜单点击
    $(".J_menuItem").on('click',function(){
        var url = $(this).attr('href');
        if($(this).attr("is")){
            return true;
        }
        if(url){
            console.log(url);
            localStorage.setItem("ctlytUrlData",url);
        }

        $("#J_iframe").attr('src',url);

        //获取内嵌页面的title放到外层title标签中
        let iframe = document.getElementById("J_iframe");
        if (iframe.attachEvent) {
            iframe.attachEvent("onload", function() {
                //iframe加载完成后你需要进行的操作
                let iwindow = iframe.contentWindow;
                let idoc = iwindow.document;
                let title = idoc.head.getElementsByTagName("title")[0].innerText;
                let tl = document.title;
                document.title = tl.substring(0,tl.lastIndexOf("-")+1)+title;
            });
        } else {
            iframe.onload = function() {
                //iframe加载完成后你需要进行的操作
                let iwindow = iframe.contentWindow;
                let idoc = iwindow.document;
                let title = idoc.head.getElementsByTagName("title")[0].innerText;
                let tl = document.title;
                document.title = tl.substring(0,tl.lastIndexOf("-")+1)+title;
            };
        }
        return false;
    });
    //页面刷新
    console.log(localStorage.getItem("ctlytUrlData"));
    $("#J_iframe").attr('src',localStorage.getItem("ctlytUrlData"));

    //获取内嵌页面的title放到外层title标签中
    let iframe = document.getElementById("J_iframe");
    if (iframe.attachEvent) {
        iframe.attachEvent("onload", function() {
            //iframe加载完成后你需要进行的操作
            let iwindow = iframe.contentWindow;
            let idoc = iwindow.document;
            let title = idoc.head.getElementsByTagName("title")[0].innerText;
            let tl = document.title;
            document.title = tl.substring(0,tl.lastIndexOf("-")+1)+title;
        });
    } else {
        iframe.onload = function() {
            //iframe加载完成后你需要进行的操作
            let iwindow = iframe.contentWindow;
            let idoc = iwindow.document;
            //let title = idoc.head.getElementsByTagName("title")[0].innerText;
            console.log(idoc.head.getElementsByTagName("title")[0]);
            let title = idoc.head.getElementsByTagName("title")[0].value;
            let tl = document.title;
            document.title = tl.substring(0,tl.lastIndexOf("-")+1)+title;
        };
    }

});