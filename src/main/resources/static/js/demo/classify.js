let $ = jQuery;
$(function () {
    //根据窗口调整表格高度
    $(window).resize(function() {
        $('#exampleTableEvents').bootstrapTable('resetView', {
            height: tableHeight()
        })
    })

    $('#exampleTableEvents').bootstrapTable({
        url: "/classify/selectAll",
        contentType: "application/x-www-form-urlencoded",
        dataType:"json",
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
                field: 'id',
                visible: false
            },
            {
                title: '分类名',
                field: 'name',
                sortable: true
            },
            {
                title: "排序",
                field: 'sort',
                visible: false
            },
            {
                title: '备注',
                field: 'remark',
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
    // //点击搜索
    // $('#btn-search').click(function () {
    //     $('#exampleTableEvents').bootstrapTable('refresh', {url: '/user/pageUser'});//url为后台action
    // })
    //点击保存
    $('#btn-submit').click(function () {
        let id = $("#id").val();
        let name = $("#name").val();
        let remark = $("#remark").val();
        let sort = $("#sort").val();

        req("/classify/addClassify",{id:id,name:name,remark:remark,sort:sort});
    })
    //添加
    $("#add").click(function () {
        $("#title").html("添加类型");
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
            if(res=="add"){
                swal("添加成功！", "您已经添加了一条信息。", "success");
            }else if (res == 'edit'){
                swal("修改成功！", "您已经修改了一条信息。", "success");
            }
            setTimeout(function () {
                swal.close()
            },2000)
            //清除表单数据
            document.getElementById("myform").reset();
            //隐藏模板
            $("#myModal5").modal("hide");
            //刷新表格
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/classify/selectAll'});
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
//请求服务数据时所传查询参数
function queryParams(params){
    return{
        pageSize: params.limit,
        pageNum:parseInt(params.offset/params.limit)+1,
        uno:$("#uno_search").val(),
        real_name:$('#real_name_search').val(),
        tel:$('#tel_search').val()
    }
}
//点击新增按钮事件
window.operateEvents = {
    "click #edit": function (e, value, row, index) {
        $("#title").html("修改用户");
        $("#id").val(row.id);
        $("#name").val(row.name);
        $("#remark").val(row.remark);
        $("#sort").val(row.sort);
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
    $.ajax("/classify/delClassify",{
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        method: "post",
        data:{id:row.id},
        success(){
            swal("删除成功！", "您已经永久删除了"+row.name+"。", "success");
            setTimeout(function () {
                swal.close();
            },1500)
            //刷新表格
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/classify/selectAll'});
        }
    });
}

