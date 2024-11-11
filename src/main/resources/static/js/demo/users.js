let $ = jQuery;
let reqUrl;
let roles;
$(function () {
    getRoles();
    //根据窗口调整表格高度
    $(window).resize(function() {
        $('#exampleTableEvents').bootstrapTable('resetView', {
            height: tableHeight()
        })
    })

    $('#exampleTableEvents').bootstrapTable({
        method:"post",
        url: "user/pageUsers",
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
                field: 'uid',
                visible: false
            },
            {
                title: '工号',
                field: 'uno',
                sortable: false
            },
            {
                title: '用户名',
                field: 'username',
                sortable: false
            },
            {
                title: '密码',
                field: 'password',
                visible: false
            },
            {
                title: '姓名',
                field: 'realName',
                sortable: true
            },
            {
                title: '联系方式',
                field: 'tel',
            },
            {
                title: '身份证号',
                field: 'cardId',
                visible: false
            },
            {
                title: '角色',
                field: 'rid',
                formatter: getRoleName
            },
            {
                title: '状态',
                field: 'status',
                sortable: true
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
        $('#exampleTableEvents').bootstrapTable('refresh', {url: '/user/pageUsers'});//url为后台action
    })
    //点击保存
    $('#btn-submit').click(function () {
        let uid = $("#uid").val();
        let uno = $("#uno").val();
        let username = $("#username").val();
        let password = $.md5($("#password").val());
        let tel = $("#tel").val();
        let card_id = $("#card_id").val();
        let real_name = $("#real_name").val();
        let rid = $("#rid").val();

        req(reqUrl,{uid:uid,uno:uno,username:username,password:password,tel:tel,realName:real_name,cardId:card_id,rid:rid});
    })
    //添加
    $("#add").click(function () {
        $("#title").html("添加用户");
        reqUrl = "/user/addUser";
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
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/user/pageUsers'});
        }
    })

}

function tableHeight() {
    return $(window).height() - 150;
}
//查询所有角色信息

function getRoles() {
    $.ajax("/role/listAll",{
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        method: "post",
        success(res){
            console.log(res);
            roles = res.rows;
        }
    });
}
//获得角色名
function getRoleName(value, row, index) {
    if(roles){
        for (let role of roles) {
            if(role.rid==value){
                return role.rname;
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
            uno:$("#uno_search").val(),
            name:$('#real_name_search').val(),
            tel:$('#tel_search').val()
        }
    }
//点击新增按钮事件
    window.operateEvents = {
        "click #edit": function (e, value, row, index) {
            $("#title").html("修改用户");
            $("#uid").val(row.uid);
            $("#uno").val(row.uno);
            $("#username").val(row.username);
            $("#password").val(row.password);
            $("#tel").val(row.tel);
            $("#real_name").val(row.realName);
            $("#card_id").val(row.cardId);
            $("#rid").val(row.rid);
            $("#myModal5").modal("show");
            reqUrl = "/user/updateUser";
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        },
        "click #del": function (e, value, row, index) {

            swal({
                title: "您确定要删除："+row.realName,
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
    $.ajax("/user/delUser",{
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        method: "post",
        data:{uid:row.uid},
        success(){
            swal("删除成功！", "您已经永久删除了"+row.realName+"。", "success");
            setTimeout(function () {
                swal.close();
            },1500)
            //刷新表格
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/user/pageUsers'});
        }
    });
}

