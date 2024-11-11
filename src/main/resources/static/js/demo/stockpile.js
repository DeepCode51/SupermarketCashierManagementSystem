let $ = jQuery;
let reqUrl;
let subFlag = true;
$(function () {
    //根据窗口调整表格高度
    $(window).resize(function() {
        $('#exampleTableEvents').bootstrapTable('resetView', {
            height: tableHeight()
        })
    })

    $('#exampleTableEvents').bootstrapTable({
        url: "/stock/pageStock",
        contentType: "application/x-www-form-urlencoded",
        dataType:"json",
        method:"post",
        height:tableHeight(),//高度调整
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination: true,//是否分页
        // dataField: "res",//获取数据的别名，先省略，则为你返回的
        sidePagination: 'server',//在服务器分页
        queryParamsType:'limit',
        queryParams:queryParams,
        pageSize: 20,//单页记录数
        clickToSelect: true,//是否启用点击选中行
        columns: [
            {
                title: 'ID',
                field: 'sid',
                visible: false
            },
            {
                title: '商品名',
                field: 'name',
                sortable: false
            },
            {
                title: '超市',
                field: 'sm',
                sortable: false
            },
            {
                title: '仓库',
                field: 'stock',
                sortable: false
            },

            {
                title: '残品',
                field: 'scr',
                sortable: false
            },
            {
                title: '操作',
                field: 'Button',
                align: 'center',
                events: operateEvents,//事件
                formatter: AddFunctionAlty//添加按钮
            }
        ],
        locale: 'zh-CN',//中文支持,
    });
    //点击搜索
    $('#btn-search').click(function () {
        $('#exampleTableEvents').bootstrapTable('refresh', {url: '/stock/pageStock'});//url为后台action
    })
    //点击保存

    $('#btn-submit').click(function () {
        if(!subFlag){
            return;
        }
        subFlag = false;
        let gid = $("#gid").val();
        let code = $("#code").val();
        let name = $("#name").val();
        let details = $("#details").val();
        let img = $("#img").val();
        let price = $("#price").val();
        let gcid = $("#gcid").val();
        let specification = $("#specification").val();
        let manufacturer = $("#manufacturer").val();

        req(reqUrl,{gid:gid,code:code,name:name,details:details,img:img,price:price,gcid:gcid,specification:specification,manufacturer:manufacturer});
    })
    //显示隐藏扫码绑定二维码
    $("#bar").click(function () {
        let codeBtn = $("#codebtn").css("display");
        if ('none'== codeBtn){
            $("#barcode").hide();
            $("#codebtn").show();
        }else{
            $("#codebtn").hide();
            $("#barcode").show();
        }
    })
    setInterval(function () {
        $.ajax("/getCode", {
            method:"post",
            headers:{
                contentType: "application/x-www-form-urlencoded",
            },
            data:{type:"sp"},
            success(res){
            if(res.data){
                $("[name=code]").val(res.data);
            }
        }})
    },1500);

})

function req(url,obj) {
    $.ajax(url,{
        method:"post",
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        data:obj,
        success:(res)=>{
            subFlag = true;
            if(res.status=="success"){
                swal("操作成功", res.msg, "success");
            }else{
                swal("操作失败！", res.msg, "error");
            }
            setTimeout(function () {
                swal.close()
            },3000)
            //清除表单数据
            document.getElementById("myform").reset();
            //隐藏模板
            $("#myModal5").modal("hide");
            //刷新表格
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/goods/pageGoods'});
        }
    })

}

function tableHeight() {
    return $(window).height() - 150;
}

//列表行‘操作’按钮
    function AddFunctionAlty(value, row, index) {
        return '<button id="edit" type="button" class="btn btn-default">详情</button>'

    }
//请求服务数据时所传查询参数
    function queryParams(params){
        return{
            pageSize: params.limit,
            pageNum:parseInt(params.offset/params.limit)+1,
            name:$("#name_search").val(),
            code:$('#code_search').val(),
            minPrice:$('#minPrice_search').val(),
            maxPrice:$('#maxPrice_search').val(),
            minStock:$('#minStock_search').val(),
            maxStock:$('#maxStock_search').val(),
            gcid:$('#gcid_search').val()

        }
    }
//点击新增按钮事件
    window.operateEvents = {
        "click #edit": function (e, value, row, index) {
            $("#title").html("库存详情");
            $("#gid").val(row.gid);
            $("#code").val(row.code);
            $("#name").val(row.name);
            $("#details").val(row.details);
            $("#img").val(row.img);
            $("#price").val(row.price);
            $("#gcid").val(row.gcid);
            $("#specification").val(row.specification);
            $("#manufacturer").val(row.manufacturer);
            reqUrl = "goods/updateGoods"
            $("#myModal5").modal("show");
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        }
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
    }

