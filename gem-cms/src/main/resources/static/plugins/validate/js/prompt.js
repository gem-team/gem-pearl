$.validator.setDefaults({

    submitHandler:function(form){
        form.submit();//提交时拦截

    },
    errorElement:'div',
    errorPlacement: function(error, element) {
        error.addClass('tooltip tooltip-inner arrow-left');
        if (element.is(":radio")){
            error.appendTo(element.parent().next().next());
        }else if (element.is(":checkbox")){
            error.appendTo(element.next());
        }else{
            element.after(error);
        }
        var pos = $.extend({}, element.offset(), {
                width: element.outerWidth()
                , height: element.outerHeight()
            }),
            actualWidth = error.outerWidth(),
            actualHeight = error.outerHeight();
        if((pos.top - actualHeight)<0){actualHeight=0;pos.width+=10;}//如果输入框距离顶端为0情况把提示放右边
        if(element.parents(".blockPage").attr("class")=="blockUI blockMsg blockPage"){//如果是弹出框的，那么设置如下
            error.css({display:'block',opacity:'0.6' ,left:300,top:pos.top - $(document).scrollTop() - actualHeight - 100, "border-left": '0px'});
        }
        else if (element.is(":radio")){//类型为radio的显示如下
            error.css({display:'block',opacity:'0.6',top: pos.top - actualHeight, left: pos.left + pos.width / 2 });
        }else{//其他均为以下显示
            error.css({display:'block',opacity:'0.6',top: pos.top - actualHeight, left: pos.left + pos.width-10 });
        }
    },
    highlight: function(element, errorClass) {
        //高亮显示
        $(element).addClass(errorClass);
        $(element).parents('li:first').children('label').addClass(errorClass);
    },
    unhighlight:function(element, errorClass){
        $(element).removeClass(errorClass);
        $(element).parents('li:first').children('label').removeClass(errorClass);
    }
});