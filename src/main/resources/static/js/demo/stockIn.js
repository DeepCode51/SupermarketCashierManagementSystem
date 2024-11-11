let $ = jQuery;
let reqUrl;
$(function () {

    $('#exampleTableEvents').bootstrapTable({
        method:"post",
        url: "/receipts/pageReceiptsByStock",
        contentType: "application/x-www-form-urlencoded",
        dataType:"json",
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination: true,//是否分页
        // dataField: "res",//获取数据的别名，先省略，则为你返回的
        sidePagination: 'server',//在服务器分页
        queryParamsType:'limit',
        queryParams:queryParams,
        pageSize: 3,//单页记录数
        checkbox:true,
        singleSelect:true,
        clickToSelect: true,//是否启用点击选中行
        showToggle: false,
        showColumns: false,
        columns: [
            {
                title: 'ID',
                field: 'rid',
                visible: false
            },
            {
                title: '单据编号',
                field: 'rno',
                sortable: false
            },
            {
                title: '修改时间',
                field: 'updateTime'
            },
            {
                title: '操作人',
                field: 'operator',
                sortable: true
            },
            {
                title: '审核人',
                field: 'auditor',
            }
        ],
        locale: 'zh-CN',//中文支持,
    });
    //点击搜索
    $('#search').click(function () {
        $('#exampleTableEvents').bootstrapTable('refresh', {url: '/receipts/pageReceiptsByStock'});//url为后台action
    })

    $('#exampleTableEvents').on("click-row.bs.table",function (row,el,field) {
        $.ajax("receiptsDetails/findReceiptsDetailsByRID", {
            method: "post",
            headers: {
                contentType: "application/x-www-form-urlencoded",
            },
            data: {rid:el.rid},
            success: (res) => {
                //渲染

                let list = res.data;
                //清除box中除去头部的所有标签
                $("#box>div:gt(0)").remove();
                //遍历list中的数据
                for (let data of list) {
                    let str = "<div class='row'><div class='col-xs-6'><input type='text' name='gid' hidden value="+data.gid+" /><input type='text' name='gname' disabled class='form-control' value="+data.name+" /> </div><div class='col-xs-2'><input type='text' name='number' class='form-control' value="+data.number+"></div> <div class='col-xs-4'><div class='input-group m-b'><input type='date' name='produced_date' class='form-control'></div></div></div>";
                    $("#box").append(str);
                }

                $("#rid").val(el.rid);

            }
        })
    })

})


//请求服务数据时所传查询参数
function queryParams(params){
    return{
        pageSize: params.limit,
        pageNum:parseInt(params.offset/params.limit)+1,
        typeid:$("#typeid").val(),
        status:$('#status').val(),
        rno:$('#rno').val(),
        startTime:$('#startDate').val(),
        endTime:$('#endDate').val()
    }
}


