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
        url: "/order/pageOrder",
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
                field: 'oid',
                visible: false
            },
            {
                title: '订单编号',
                field: 'ono',
                sortable: false
            },
            {
                title: '订单价格',
                field: 'price',
                sortable: false,
            },
            {
                title: '创建时间',
                field: 'createTime',
                sortable: false
            },
            {
                title: '操作人',
                field: 'operator',
                sortable: false
            },

            {
                title: '状态',
                field: 'status',
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
        $('#exampleTableEvents').bootstrapTable('refresh', {url: '/order/pageOrder'});//url为后台action
    })

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

            ono:$("#ono_search").val(),
            status:$('#stauts_search').val(),
            minPrice:$('#minPrice_search').val(),
            maxPrice:$('#maxPrice_search').val(),
            startTime:$('#startTime_search').val(),
            endTime:$('#endTime_search').val(),
            operator:$('#operator_search').val()

        }
    }
//点击新增按钮事件
    window.operateEvents = {
        "click #edit": function (e, value, row, index) {
            /*详情*/
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
            reqUrl = "goods/updateGoods"
            $("#myModal5").modal("show");
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        }
    }

