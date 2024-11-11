let $ = jQuery;
let reqUrl;
let downloadRid=0;
$(function () {
    //根据窗口调整表格高度
    $(window).resize(function() {
        $('#exampleTableEvents').bootstrapTable('resetView', {
            height: tableHeight()
        })
    })

    $('#exampleTableEvents').bootstrapTable({
        method:"post",
        url: "/receipts/pageReceipts",
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
                field: 'rid',
                visible: false
            },
            {
                title: '单据编号',
                field: 'rno',
                sortable: false
            },
            {
                title: '创建时间',
                field: 'createTime',
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
            },
            {
                title: '类型',
                field: 'typeid'
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
        $('#exampleTableEvents').bootstrapTable('refresh', {url: '/receipts/pageReceipts'});//url为后台action
    })
    //点击下载
    $("#down-submit").click(function () {
        downloadExcel(downloadRid);
    })

    //点击审核
    $("#btn-submit").click(function () {
        $.ajax("/receipts/updateReceipts",{
            headers:{
                contentType: "application/x-www-form-urlencoded",
            },
            method: "post",
            data:{rid:downloadRid},
            success(){
                swal("审核成功！", "您审核"+$("#rno").html()+"。", "success");
                setTimeout(function () {
                    swal.close();

                },2500)
                $("#myModal5").modal("hide");
                //刷新表格
                $('#exampleTableEvents').bootstrapTable('refresh', {url: '/receipts/pageReceipts'});
            }
        });
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

//列表行‘操作’按钮
    function AddFunctionAlty(value, row, index) {
        return '<button id="edit" type="button" class="btn btn-default">详情</button><button id="del" type="button" class="btn btn-default">删除</button>'

    }
//请求服务数据时所传查询参数
    function queryParams(params){
        return{
            pageSize: params.limit,
            pageNum:parseInt(params.offset/params.limit)+1,
            typeid:$("#typeid_search").val(),
            status:$('#status_search').val(),
            startTime:$('#startTime_search').val(),
            endTime:$('#endTime_search').val()
        }
    }
//点击新增按钮事件
    window.operateEvents = {
        "click #edit": function (e, value, row, index) {
            $("#title").html("单据详情");
            $("#attachment").html("");
            downloadRid = row.rid;

            $("#createTime").html(formatDate(row.createTime));
            $("#updateTime").html(formatDate(row.updateTime));
            $("#rno").html(row.rno);
            $("#operator").html(row.operator);
            $("#auditor").html(row.auditor);

            //查询单据详情
            $.ajax("/receiptsDetails/findReceiptsDetailsByRID",{
                method:"post",
                headers:{
                    contentType: "application/x-www-form-urlencoded",
                },
                data:{rid:row.rid},
                success:(res)=>{
                    //数据渲染
                    let data = res.data;
                    let total =0;
                    $("#data").html("");
                    for (let dataKey of data) {
                        let totalTmp = dataKey.number*dataKey.price;
                        total+=totalTmp;
                        let el = "<tr><td><div><strong>"+dataKey.name+"</strong></div></td><td>"+dataKey.number+"</td><td>&yen;"+dataKey.price+"</td><td>&yen;"+totalTmp+"</td></tr>";
                        $("#data").append(el);
                    }
                    $("#total").html(total);
                }
            })
            /*查询单据附件*/
            $.ajax("/receipts/findReceiptsFile",{
                method:"post",
                headers:{
                    contentType: "application/x-www-form-urlencoded",
                },
                data:{rid:row.rid},
                success:(res)=>{
                    //数据渲染
                    let fus = res.data;
                    if(fus){
                        $("#number").html(fus.length);
                        for (let fu of fus) {
                            if(isImage(fu.path)){
                                //图片
                                let str = "<div class='file-box'><div class='file'><a href='#'><span class='corner'></span><div class='image'><img class='img-responsive' src='"+fu.url+"' ></div><div class='file-name'>"+fu.fileName+"</div></a></div></div>";
                                $("#attachment").append(str);
                            }else{
                                //文件
                                let str = "<div class='file-box'><div class='file'><a href='#'><span class='corner'></span><div class='icon'> <i class='fa fa-file'></i></div><div class='file-name'>"+fu.fileName+"</div></a></div></div>";
                                $("#attachment").append(str);
                            }
                        }
                        $("#attachment").append("<div class='clearfix'></div>")
                    }

                }
            })
            $("#myModal5").modal("show");
            // window.location.href = "/getOneCadreInfo/" + row.id;//跳转新增页面
        },
        "click #del": function (e, value, row, index) {

            swal({
                title: "您确定要删除："+row.rno,
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
//是否是图片
function isImage(str) {
    var reg = /\.(png|jpg|gif|jpeg|webp|jfif)$/;
    return reg.test(str);
}
//删除
function del(row) {
    console.log(row)
    $.ajax("/receipts/delReceipts",{
        headers:{
            contentType: "application/x-www-form-urlencoded",
        },
        method: "post",
        data:{rid:row.rid},
        success(){
            swal("删除成功！", "您已经永久删除了"+row.rno+"。", "success");
            setTimeout(function () {
                swal.close();
            },1500)
            //刷新表格
            $('#exampleTableEvents').bootstrapTable('refresh', {url: '/receipts/pageReceipts'});
        }
    });
}
//下载Excel
function downloadExcel(rid) {
    location.href = "/downloadExcel?rid="+rid;
    // window.open("/downloadExcel?rid="+rid,1);
}

//导出PDF
function exportReportTemplet() {
    html2canvas($("#pdfDemo"), {
        allowTaint: true,
        height: $("#pdfDemo").outerHeight(),
        onrendered: function (canvas) {

            //返回图片URL，参数：图片格式和清晰度(0-1)
            var pageData = canvas.toDataURL('image/jpeg', 1.0);

            //方向默认竖直，尺寸ponits，格式a4【595.28,841.89]
            var pdf = new jsPDF('', 'pt', 'a4');

            //需要dataUrl格式
            pdf.addImage(pageData, 'JPEG', 0, 0, 595.28, 592.28 / canvas.width * canvas.height);

            pdf.save("单据_"+$("#rno").html()+'.pdf');


        }
    });
    // var element = $("#pdfDemo");    // 这个dom元素是要导出pdf的div容器
    // var w = element.width();    // 获得该容器的宽
    // var h = element.height();    // 获得该容器的高
    // console.log(w,h);
    // var offsetTop = element.offset().top;    // 获得该容器到文档顶部的距离
    // var offsetLeft = element.offset().left;    // 获得该容器到文档最左的距离
    // var canvas = document.createElement("canvas");
    // canvas.width = w * 2;    // 将画布宽&&高放大两倍
    // canvas.height = h * 2;
    // var context = canvas.getContext("2d");
    // var scale = 2;
    // context.scale(2, 2);
    // // context.translate(-offsetLeft - abs, -offsetTop);
    //
    // var opts = {
    //     scale: scale,
    //     canvas: canvas,
    //     width: w,
    //     height: h,
    //     useCORS: true,
    //     background: '#FFF'
    // }
    //
    // html2canvas(element, opts).then(function (canvas) {
    //     allowTaint: true;
    //     height: $("#myModal5").outerHeight();
    //     taintTest: false;
    //     var contentWidth = canvas.width;
    //     var contentHeight = canvas.height;
    //     //一页pdf显示html页面生成的canvas高度;
    //     var pageHeight = contentWidth / 592.28 * 841.89;
    //     //未生成pdf的html页面高度
    //     var leftHeight = contentHeight;
    //     //页面偏移
    //     var position = 0;
    //     //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
    //     var imgWidth = 595.28;
    //     var imgHeight = 592.28 / contentWidth * contentHeight;
    //
    //     var pageData = canvas.toDataURL('image/jpeg', 1.0);
    //     //   var oCanvas = document.getElementById("print");
    //     // Canvas2Image.saveAsJPEG(oCanvas);
    //     var pdf = new jsPDF('', 'pt', 'a4');
    //
    //     //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
    //     //当内容未超过pdf一页显示的范围，无需分页
    //     if (leftHeight < pageHeight) {
    //         pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
    //     } else {    // 分页
    //         while (leftHeight > 0) {
    //             pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
    //             leftHeight -= pageHeight;
    //             position -= 841.89;
    //             //避免添加空白页
    //             if (leftHeight > 0) {
    //                 pdf.addPage();
    //             }
    //         }
    //     }
    //     pdf.save("单据_"+$("#rno").html()+'.pdf');
    // })

}
