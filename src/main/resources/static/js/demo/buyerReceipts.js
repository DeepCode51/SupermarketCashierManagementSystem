let $ = jQuery;
$(function () {
    /*添加按钮*/
    $("#addTab").click(function () {
        $(this).parent().clone(true).insertAfter($(this).parent());
        findGoods();
    })
    /*删除按钮*/
    $("#delTab").click(function () {
        if($(this).parent().parent().children().length<=2){
            return;
        }
        $(this).parent().remove();
    })
    /*搜索*/
    $("#search").click(function () {
        findGoods();
    });
})
//    查询
function findGoods() {
    var val = $("#key").val();
    $.ajax("/goods/findGoods", {
        data: {key: val},
        success(res) {
            //渲染
            var $select = $("#box>div:last select");
            //删除所有元素
            $select.children().remove()
            //添加查询的数据到选择项
            for (let dataKey of res.data) {
                let str = "<option  value=" + dataKey.gid + " >" + dataKey.name + "</option>"
                $select.append(str);
            }
            $("#key").val("");
        }
    });
}