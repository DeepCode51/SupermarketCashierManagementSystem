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
        url: "/goods/pageGoods",
        contentType: "application/x-www-form-urlencoded",
        dataType:"json",
        method:"post",
        height:tableHeight(),//高度调整
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination: true,//是否分页
        // dataField: "res",//获取数据的别名，先省略，则为你返回的
        search: true,
        sidePagination: 'server',//在服务器分页
        queryParamsType:'limit',
        queryParams:queryParams,
        pageSize: 20,//单页记录数
        showRefresh: true,//刷新按钮
        clickToSelect: true,//是否启用点击选中行
        showToggle: true,
        showColumns: true,
        iconSize: 'outline',
        toolbar: '#exampleTableEventsToolbar',
        icons: {
            refresh: 'glyphicon-repeat',
            toggle: 'glyphicon-list-alt',
            columns: 'glyphicon-list'
        },
        columns: [
            {
                title: 'ID',
                field: 'gid',
                visible: false
            },{
                title: '图片',
                field: 'img',
                visible: false,
                formatter:showImg
            },
            {
                title: '商品编号',
                field: 'code',
                sortable: false
            },
            {
                title: '商品名',
                field: 'name',
                sortable: false,
            },
            {
                title: '价格',
                field: 'price',
                sortable: false
            },
            {
                title: '分类',
                field: 'gcid',
                sortable: false,
                formatter:showCategory
            },

            {
                title: '规格',
                field: 'specification',
                sortable: false
            },
            {
                title: "生产商",
                field: 'manufacturer',
                visible: false
            },
            {
                title: "详情",
                field: 'details',
                visible: false
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
        $('#exampleTableEvents').bootstrapTable('refresh', {url: '/goods/pageGoods'});//url为后台action
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
    //添加
    $("#add").click(function () {
        $("#title").html("添加商品");
        reqUrl = "/goods/addGoods";
        //清除表单数据
        document.getElementById("myform").reset();
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

    /*图片异步上传*/
    $("#pic").change(function () {
        //上传的请求
        let formDateObj = new FormData();

        //添加文件对象
        formDateObj.append('file',$('#pic')[0].files[0])
        //4.将对象基于ajax发送给后端
        $.ajax({
            url:'/upload_ajax',
            type:'post',
            data:formDateObj,  //直接将对象放在data后面
            //ajax发送文件必须要指定两个参数
            contentType:false,  //不要使用任何编码，django后端能够自动识别formdata对象
            processData:false,  //告诉浏览器不要对你的数据进行任何处理
            success:function (data) {
                console.log(data);
                //回填数据到隐藏域
                $("#img").val(data.data.url);
                let img = '<img src="'+data.data.url+'" style="min-width: 80%;max-width:100%;position: absolute;padding-right: 20px" alt="">';
                $("#goodsImg").after(img);
            }
        })
    });

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
/*显示图片*/
function showImg(value, row, index) {
    return "<img src="+row.img +" style='height:40px' >";
}
/*显示分类*/

function showCategory(value, row, index) {
    let fls = JSON.parse(sessionStorage.getItem("goodsFLs"));
    if(fls){
        for (let fl of fls) {
            if(fl.gcid==value){
                return fl.gcname;
            }
        }
    }
    return value;

}
//列表行‘操作’按钮
function AddFunctionAlty(value, row, index) {
    return '<button id="edit" type="button" class="btn btn-default">修改</button><button id="del" type="button" class="btn btn-default">删除</button>'

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
            gcid:$('#gcid_search').val()

        }
    }
//点击新增按钮事件
    window.operateEvents = {
        "click #edit": function (e, value, row, index) {
            $("#title").html("修改商品");
            $("#gid").val(row.gid);
            $("#code").val(row.code);
            $("#name").val(row.name);
            $("#details").val(row.details);
            $("#img").val(row.img);
            $("#price").val(row.price);
            $("#gcid").val(row.gcid);
            $("#specification").val(row.specification);
            $("#manufacturer").val(row.manufacturer);
            reqUrl = "/goods/updateGoods"
            $("#myModal5").modal("show");
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        },
        "click #del": function (e, value, row, index) {

            swal({
                title: "您确定要删除："+row.name,
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                cancelButtonText: "取消",
                confirmButtonText: "删除",
                closeOnConfirm: false
            }, function () {
                del(row);
            });
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        }
    }
    //删除
function del(row) {
    $.ajax("/goods/delGoods",{
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        method: "post",
        data:{gid:row.gid},
        success(){
            swal("删除成功！", "您已经永久删除了"+row.name+"。", "success");
            setTimeout(function () {
                swal.close();
            },1500)
            //刷新表格
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/goods/pageGoods'});
        }
    });
}

