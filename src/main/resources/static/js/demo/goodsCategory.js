let $ = jQuery;
let reqUrl;
$(function () {
    //根据窗口调整表格高度
    $(window).resize(function() {
        $('#exampleTableEvents').bootstrapTable('resetView', {
            height: tableHeight()
        })
    })

    $('#exampleTableEvents').bootstrapTable({
        method:"post",
        url: "/goodsCategory/listAll",
        contentType: "application/x-www-form-urlencoded",
        dataType:"json",
        height:tableHeight(),//高度调整
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination: false,//是否分页
        // dataField: "res",//获取数据的别名，先省略，则为你返回的
        search: true,
        sidePagination: 'server',//在服务器分页
        queryParamsType:'limit',
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
                field: 'gcid'
            },
            {
                title: '商品分类名',
                field: 'gcname',
                sortable: false
            },
            {
                title: '备注',
                field: 'comment'
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
    //点击保存
    $('#btn-submit').click(function () {
        let gcid = $("#gcid").val();
        let gcname = $("#gcname").val();
        let comment = $("#comment").val();
        req(reqUrl,{gcid:gcid,gcname:gcname,comment:comment});
    })
    //添加
    $("#add").click(function () {
        $("#title").html("添加商品分类");
        reqUrl = "/goodsCategory/addGoodsCategory";
        //清除表单数据
        document.getElementById("myform").reset();
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
            console.log(res);
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
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/goodsCategory/listAll'});
        }
    })

}

function tableHeight() {
    return $(window).height() - 150;
}

//列表行‘操作’按钮
    function AddFunctionAlty(value, row, index) {
        return '<button id="edit" type="button" class="btn btn-default">修改</button><button id="del" type="button" class="btn btn-default">删除</button>'

    }

//点击新增按钮事件
    window.operateEvents = {
        "click #edit": function (e, value, row, index) {
            $("#title").html("修改商品分类");
            $("#gcid").val(row.gcid);
            $("#gcname").val(row.gcname);
            $("#comment").val(row.comment);
            $("#myModal5").modal("show");
            reqUrl = "/goodsCategory/updateGoodsCategory";
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        },
        "click #del": function (e, value, row, index) {

            swal({
                title: "您确定要删除："+row.gcname,
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
    $.ajax("/goodsCategory/delGoodsCategory",{
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        method: "post",
        data:{gcid:row.gcid},
        success(){
            swal("删除成功！", "您已经永久删除了"+row.gcname+"。", "success");
            setTimeout(function () {
                swal.close();
            },1500)
            //刷新表格
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/goodsCategory/listAll'});
        }
    });
}

