//列表页面上点击删除按钮弹出删除框
$(function () {
    $('.delete').click(function () {
        //灰背景遮挡效果
        $('.zhezhao').css('display', 'block');
        $('#removeProv').fadeIn();

        //将删除按钮的属性绑定到deleteForm上；
        $("#deleteForm").attr("action", $(this).attr("del_url"));
    });
    //点击 确定
    $('#yes').click(function () {
        $("#deleteForm").submit();
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();
    });
    //点击 取消
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();
    });
});
