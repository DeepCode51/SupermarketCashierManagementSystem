let $ = jQuery;
let uuid = "cahier_1";
let oid=0;
$(function () {
    //根据窗口调整表格高度
    $(window).resize(function() {
        $('#exampleTableEvents').bootstrapTable('resetView', {
            height: tableHeight()-80
        });
        $("#s_goods").css("height",tableHeight()-20-$(".ibox-content").height()+"px");
        $("#g_keys").css("height",tableHeight()+"px");
    })
    $("#s_goods").css("height",tableHeight()-20-$(".ibox-content").height()+"px");
    $("#g_keys").css("height",tableHeight()+"px");

    $('#exampleTableEvents').bootstrapTable({

        height:tableHeight()-80,//高度调整
        striped: true, //是否显示行间隔色
        locale: 'zh-CN',//中文支持,
    });
    setInterval(function () {

        $.ajax("/cashier/goods",{
            headers:{
                contentType: "application/x-www-form-urlencoded",
            },
            method: "post",
            data:{pageNum:uuid},
            success(res){
                //渲染数据
                let list = res.data.goods;
                if(list){
                    $("#cashierGoods").html("");
                    let total = 0;
                    for(let vo of list){
                        total+= vo.number*vo.price;
                        let el = "<tr code="+vo.code+" ><th data-field='name'>"+vo.name+"</th><th data-field='price' data-width='80'>&yen;"+vo.price+"</th><th data-field='number' data-width='200' data-align='center'><div class='input-group'><span class='input-group-btn'>" +
                            "<button type='button' style='margin-bottom: 0px' class='btn btn-primary'  onclick='subGoods("+vo.code+")' >-</button></span>" +
                            "<input type='text' style='width: 60px;text-align: center' class='form-control' value='"+vo.number+"' >" +
                            "<span class='input-group-btn'> <button type='button' style='margin-bottom: 0px' class='btn btn-primary' onclick='sendCode("+vo.code+")' >+" +
                            "</button></div></span></th><th data-field='total' data-width='80'>&yen;"+(vo.number*vo.price)+"</th></tr>";
                        $("#cashierGoods").append(el);
                    }

                    $("#total").html((parseInt(total*100)/100).toFixed(2));
                    $("#ys").html((parseInt(total*100)/100).toFixed(2));
                }
                let keys = res.data.hang_one_keys;
                if (keys){
                    $("#keys").html("");

                    for (let ke of keys.sort()) {
                        let el =  "<tr "+ (ke==uuid?"style=\"background:rgb(99,85,178);color:#FFF\"":"") +" onclick=setKeys('"+ke+"') ><td>"+ke+"</td></tr>";
                        $("#keys").append(el);
                    }
                }
            }
        });
    },1000)
       //url为后台action

    $("#goods_search").click(function () {
        let key = $("#key").val();
        $.ajax("/goods/findGoods",{
            data:{key:key},
            success(res){
                //渲染数据
                let goods = res.data;
                $("#goods").html("");
                goods.forEach(item=>{
                    let el =  "<tr onclick='sendCode("+item.code+")'><td class='client-avatar'><img src="+item.img+"></td><td>"+item.name+"</td></tr>";
                    $("#goods").append(el);
                })
            }
        });
    })
    /*添加条码*/
    $("#addCode").click(function () {
        let code = $("#code_search").val();
        sendCode(code);
        $("#code_search").val("")
    })
    /*挂单*/
    $("#hang_one").click(function () {
        let date = new Date();
        uuid = "cashier_"+date.getTime();
    })
    /*创建单据*/
    $("#createOrder").click(function () {
        createOrder();
    });

    //键盘事件
    $(window).keydown(function (e) {
        switch (e.keyCode) {
            case 107:
                //加号
                let $last = $("#cashierGoods").children().last();
                if($last.length){
                    sendCode($last.attr("code"))
                }
                break;
            case 109:
                //减号
                let $las = $("#cashierGoods").children().last();
                if($las.length){
                    subGoods($las.attr("code"))
                }
                break;
            case 13:
                //结算
                createOrder();
                break;
            case 9:
                //挂单
                let date = new Date();
                uuid = "cashier_"+date.getTime();
                break;

        }
    })
//    现金
    $("#zf input").change(function () {
        showTotalPrice();
    })
    /*订单支付*/
    $("#order-save").click(function () {
        let i=0;
        typeids = [];
        prices= [];
        if($("#xj").val()){
            typeids[i] = 1;
            prices[i] = parseInt($("#xj").val())-(getTotalPrice()-parseInt($("#ys").html()));
            i++;
        }
        if($("#wx").val()){
            typeids[i] = 2;
            prices[i] = $("#wx").val();
            i++;
        }
        if($("#zfb").val()){
            typeids[i] = 3;
            prices[i] = $("#zfb").val();
            i++;
        }
        if($("#sk").val()){
            typeids[i] = 4;
            prices[i] = $("#sk").val();
            i++;
        }

        $.ajax("/order/payOrder",{
            headers:{
                contentType: "application/x-www-form-urlencoded",
            },
            method: "post",
            data: {oid:oid,prices:JSON.stringify(prices),typeids:JSON.stringify(typeids),cno:uuid},
            success(res){
                $("#xj").val("");
                $("#wx").val("");
                $("#zfb").val("");
                $("#sk").val("");
                $("#ss").html("0.00");
                $("#zl").html("0.00");
                $("#myModal5").modal("hide");
                console.log(res);
            }
        });
    });

})
/*显示金额*/
function showTotalPrice() {
    $("#ss").html(getTotalPrice());
    $("#zl").html(getTotalPrice()-parseInt($("#ys").html()));
}

/*记录输入的总金额*/
function getTotalPrice() {
    let xj = parseInt($("#xj").val()?$("#xj").val():0);
    let wx = parseInt($("#wx").val()?$("#wx").val():0);
    let zfb = parseInt($("#zfb").val()?$("#zfb").val():0);
    let sk = parseInt($("#sk").val()?$("#sk").val():0);
    return xj+wx+zfb+sk;
}
/*选择单*/
function setKeys(key){
    uuid = key;
}
/*创建订单*/
function createOrder(){
    $.ajax("/order/createOrder",{
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        method: "post",
        data:{ono:uuid},
        success(res){
            oid = res.data;
            showType();
        }
    });
}
/*创建单据*/
function showType() {

    $("#myModal5").modal("show");
}

/*发送条码信息*/
function sendCode(code) {
    let no = $("#no").val();
    $.ajax("/code/jscode",{
        data:{no:no,type:"sy",code:code},
        success(){
            //提示
        }
    });
}
/*数量减1*/

function subGoods(code) {
    $.ajax("/cashier/subGoods",{
        data:{pageNum:uuid,code:code},
        success(){
            //提示
        }
    });
}

function tableHeight() {
    return $(window).height() - 150;
}

//列表行‘操作’按钮
    function AddFunctionAlty(value, row, index) {
        return '<button id="TableView" type="button" class="btn btn-default">删除</button>'

    }
//请求服务数据时所传查询参数
    function queryParams(params){
        return{
            pageSize: params.limit,
            pageNum:parseInt(params.offset/params.limit)+1,
            name:$('#searchName').val()
        }
    }
//点击新增按钮事件
    window.operateEvents = {
        "click #TableView": function (e, value, row, index) {
            window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        }
    }




