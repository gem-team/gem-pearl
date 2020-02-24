(function ($) {
    $.learuntab = {
        requestFullScreen: function () {
            var de = document.documentElement;
            if (de.requestFullscreen) {
                de.requestFullscreen();
            } else if (de.mozRequestFullScreen) {
                de.mozRequestFullScreen();
            } else if (de.webkitRequestFullScreen) {
                de.webkitRequestFullScreen();
            }
        },
        exitFullscreen: function () {
            var de = document;
            if (de.exitFullscreen) {
                de.exitFullscreen();
            } else if (de.mozCancelFullScreen) {
                de.mozCancelFullScreen();
            } else if (de.webkitCancelFullScreen) {
                de.webkitCancelFullScreen();
            }
        },
        refreshTab: function () {
            var currentId = $('.page-tabs-content').find('.active').attr('data-id');
            var target = $('.LRADMS_iframe[data-id="' + currentId + '"]');
            var url = target.attr('src');
            //$.loading(true);
            target.attr('src', url).load(function () {
                //$.loading(false);
            });
        },
        activeTab: function () {
            var currentId = $(this).data('id');
            if (!$(this).hasClass('active')) {
                $('.mainContent .LRADMS_iframe').each(function () {
                    if ($(this).data('id') == currentId) {
                        $(this).show().siblings('.LRADMS_iframe').hide();
                        return false;
                    }
                });
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $.learuntab.scrollToTab(this);
            }
        },
        closeOtherTabs: function () {
            $('.page-tabs-content').children("[data-id]").find('.fa-remove').parents('a').not(".active").each(function () {
                $('.LRADMS_iframe[data-id="' + $(this).data('id') + '"]').remove();
                $(this).remove();
            });
            $('.page-tabs-content').css("margin-left", "0");
        },
        closeTab: function () {
            var closeTabId = $(this).parents('.menuTab').data('id');
            var currentWidth = $(this).parents('.menuTab').width();
            if ($(this).parents('.menuTab').hasClass('active')) {
                if ($(this).parents('.menuTab').next('.menuTab').size()) {
                    var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
                    $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');

                    $('.mainContent .LRADMS_iframe').each(function () {
                        if ($(this).data('id') == activeId) {
                            $(this).show().siblings('.LRADMS_iframe').hide();
                            return false;
                        }
                    });
                    var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
                    if (marginLeftVal < 0) {
                        $('.page-tabs-content').animate({
                            marginLeft: (marginLeftVal + currentWidth) + 'px'
                        }, "fast");
                    }
                    $(this).parents('.menuTab').remove();
                    $('.mainContent .LRADMS_iframe').each(function () {
                        if ($(this).data('id') == closeTabId) {
                            $(this).remove();
                            return false;
                        }
                    });
                }
                if ($(this).parents('.menuTab').prev('.menuTab').size()) {
                    var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
                    $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
                    $('.mainContent .LRADMS_iframe').each(function () {
                        if ($(this).data('id') == activeId) {
                            $(this).show().siblings('.LRADMS_iframe').hide();
                            return false;
                        }
                    });
                    $(this).parents('.menuTab').remove();
                    $('.mainContent .LRADMS_iframe').each(function () {
                        if ($(this).data('id') == closeTabId) {
                            $(this).remove();
                            return false;
                        }
                    });
                }
            }
            else {
                $(this).parents('.menuTab').remove();
                $('.mainContent .LRADMS_iframe').each(function () {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
                $.learuntab.scrollToTab($('.menuTab.active'));
            }
            return false;
        },
        addTab: function () {
            $(".navbar-custom-menu>ul>li.open").removeClass("open");
            var dataId = $(this).attr('data-id');
            if (dataId != "") {
                //top.$.cookie('nfine_currentmoduleid', dataId, { path: "/" });
            }
            var dataUrl = $(this).attr('href');
            var menuName = $.trim($(this).text());
            var flag = true;
            if (dataUrl == undefined || $.trim(dataUrl).length == 0) {
                return false;
            }
            $('.menuTab').each(function () {
                if ($(this).data('id') == dataUrl) {
                    if (!$(this).hasClass('active')) {
                        $(this).addClass('active').siblings('.menuTab').removeClass('active');
                        $.learuntab.scrollToTab(this);
                        $('.mainContent .LRADMS_iframe').each(function () {
                            if ($(this).data('id') == dataUrl) {
                                $(this).show().siblings('.LRADMS_iframe').hide();
                                return false;
                            }
                        });
                    }
                    flag = false;
                    return false;
                }
            });
            if (flag) {
                var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-remove"></i></a>';
                $('.menuTab').removeClass('active');
                var str1 = '<iframe class="LRADMS_iframe" id="iframe' + dataId + '" name="iframe' + dataId + '"  width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
                $('.mainContent').find('iframe.LRADMS_iframe').hide();
                $('.mainContent').append(str1);
                //$.loading(true);
                $('.mainContent iframe:visible').load(function () {
                    //$.loading(false);
                });
                $('.menuTabs .page-tabs-content').append(str);
                $.learuntab.scrollToTab($('.menuTab.active'));
            }

            var menuTabArr = document.getElementsByClassName('menuTab');
            var len = menuTabArr.length;
            console.log("设置tab右键菜单=len == "+len)
            for(var i=1;i<len;i++){
                menuTabArr[i].addEventListener('contextmenu', onClick);
            }
            var menuTabAct = document.getElementsByClassName('active');
            menuTabAct[0].addEventListener('contextmenu', onClick);

            return false;
        },
        scrollTabRight: function () {
            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
            var tabOuterWidth = $.learuntab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            var scrollVal = 0;
            if ($(".page-tabs-content").width() < visibleWidth) {
                return false;
            } else {
                var tabElement = $(".menuTab:first");
                var offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                scrollVal = $.learuntab.calSumWidth($(tabElement).prevAll());
                if (scrollVal > 0) {
                    $('.page-tabs-content').animate({
                        marginLeft: 0 - scrollVal + 'px'
                    }, "fast");
                }
            }
        },
        scrollTabLeft: function () {
            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
            var tabOuterWidth = $.learuntab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            var scrollVal = 0;
            if ($(".page-tabs-content").width() < visibleWidth) {
                return false;
            } else {
                var tabElement = $(".menuTab:first");
                var offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                offsetVal = 0;
                if ($.learuntab.calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                    while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                        offsetVal += $(tabElement).outerWidth(true);
                        tabElement = $(tabElement).prev();
                    }
                    scrollVal = $.learuntab.calSumWidth($(tabElement).prevAll());
                }
            }
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        },
        scrollToTab: function (element) {
            var marginLeftVal = $.learuntab.calSumWidth($(element).prevAll()), marginRightVal = $.learuntab.calSumWidth($(element).nextAll());
            var tabOuterWidth = $.learuntab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            var scrollVal = 0;
            if ($(".page-tabs-content").outerWidth() < visibleWidth) {
                scrollVal = 0;
            } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
                if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                    scrollVal = marginLeftVal;
                    var tabElement = element;
                    while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                        scrollVal -= $(tabElement).prev().outerWidth();
                        tabElement = $(tabElement).prev();
                    }
                }
            } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
                scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
            }
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        },
        calSumWidth: function (element) {
            var width = 0;
            $(element).each(function () {
                width += $(this).outerWidth(true);
            });
            return width;
        },
        init: function () {
            //alert("$.learuntab.addTab="+$.learuntab.addTab);
            $('.menuItem').on('click', $.learuntab.addTab);
            $('.menuTabs').on('click', '.menuTab i', $.learuntab.closeTab);
            $('.menuTabs').on('click', '.menuTab', $.learuntab.activeTab);
            $('.tabLeft').on('click', $.learuntab.scrollTabLeft);
            $('.tabRight').on('click', $.learuntab.scrollTabRight);
            $('.tabReload').on('click', $.learuntab.refreshTab);
            $('.tabCloseCurrent').on('click', function () {
                $('.page-tabs-content').find('.active i').trigger("click");
            });
            $('.tabCloseAll').on('click', function () {
                $('.page-tabs-content').children("[data-id]").find('.fa-remove').each(function () {
                    $('.LRADMS_iframe[data-id="' + $(this).data('id') + '"]').remove();
                    $(this).parents('a').remove();
                });
                $('.page-tabs-content').children("[data-id]:first").each(function () {
                    $('.LRADMS_iframe[data-id="' + $(this).data('id') + '"]').show();
                    $(this).addClass("active");
                });
                $('.page-tabs-content').css("margin-left", "0");
            });
            $('.tabCloseOther').on('click', $.learuntab.closeOtherTabs);
            $('.fullscreen').on('click', function () {
                if (!$(this).attr('fullscreen')) {
                    $(this).attr('fullscreen', 'true');
                    $.learuntab.requestFullScreen();
                } else {
                    $(this).removeAttr('fullscreen')
                    $.learuntab.exitFullscreen();
                }
            });
        }
    };
    $.learunindex = {
        load: function () {
            $("body").removeClass("hold-transition")
            $("#content-wrapper").find('.mainContent').height($(window).height() - 100);
            $(window).resize(function (e) {
                $("#content-wrapper").find('.mainContent').height($(window).height() - 100);
            });
            $(".sidebar-toggle").click(function () {
                if (!$("body").hasClass("sidebar-collapse")) {
                    $("body").addClass("sidebar-collapse");
                } else {
                    $("body").removeClass("sidebar-collapse");
                }
            })
            // $(window).load(function () {
            //     window.setTimeout(function () {
            //         $('#ajax-loader').fadeOut();
            //     }, 300);
            // });
            $(window).on('load',function(){
                window.setTimeout(function () {
                    $('#ajax-loader').fadeOut();
                }, 0);
            });
        },
        jsonWhere: function (data, action) {
            if (action == null) return;
            var reval = new Array();
            $(data).each(function (i, v) {
                if (action(v)) {
                    reval.push(v);
                }
            })
            return reval;
        },
        loadMenu: function () {

            var menusData_def = [
                {
                    "f_ModuleId": "111",
                    "f_ParentId": "0",
                    "f_EnCode": "SysManage",
                    "f_FullName": "系统管理",
                    "f_Icon": "fa fa-desktop",
                    "f_UrlAddress": "/default",
                },
                {
                    "f_ModuleId": "21",
                    "f_ParentId": "111",
                    "f_EnCode": "MenuManage",
                    "f_FullName": "菜单管理",
                    "f_Icon": "fa fa-sitemap",
                    "f_UrlAddress": "menu/list.html",
                } ];
            var _html = "";
            var mdata  ="";
            $.ajax({
                type: "get",
                url: "home/initMenus",
                data: {
                },
                async:false, // 异步请求
                cache:false, // 设置为 false 将不缓存此页面
                dataType: 'json', // 返回对象
                success: function(res) {
                    console.log(res);
                    if(res.code == 0){
                        mdata  = res.data;
                        if(mdata == "" || mdata == null || mdata.length == 0){
                            mdata = [ {
                                "f_ModuleId": "1",
                                "f_ParentId": "0",
                                "f_EnCode": "MenuManage",
                                "f_FullName": "暂无权限",
                                "f_Icon": "fa fa-sitemap",
                            } ];
                        }

                        $.each(mdata, function (i) {
                            var row = mdata[i];
                            //一级菜单
                            if (row.f_ParentId == "0") {
                                if (i == 0) {
                                    _html += '<li class="treeview active">';
                                } else {
                                    _html += '<li class="treeview">';
                                }
                                //如果有二级菜单
                                var childNodes = $.learunindex.jsonWhere(mdata, function (v) { return v.f_ParentId == row.f_ModuleId });
                                if(childNodes.length>0){
                                    _html += '<a class="menuItem" data-id="' + row.f_ModuleId + '">'
                                    _html += '<i class="' + row.f_Icon + '"></i>'
                                    _html += '<span style="background-color: #222d32;">' + row.f_FullName + '</span>';
                                    _html += '<i class="fa fa-angle-left pull-right"></i>';
                                }else{
                                    _html += '<a class="menuItem" data-id="' + row.f_ModuleId + '" href="'+ row.f_UrlAddress +'">'
                                    _html += '<i class="' + row.f_Icon + '"></i>'
                                    _html += '<span style="background-color: #222d32;">' + row.f_FullName + '</span>';
                                }
                                _html += '</a>'
                                if (childNodes.length > 0) {
                                    //二级菜单
                                    _html += '<ul class="treeview-menu">';
                                    $.each(childNodes, function (i) {
                                        var subrow = childNodes[i];
                                        //如果有三级菜单
                                        var subchildNodes = $.learunindex.jsonWhere(mdata, function (v) { return v.f_ParentId == subrow.f_ModuleId });
                                        _html += '<li>';
                                        if (subchildNodes.length > 0) {
                                            _html += '<a><i class="' + subrow.f_Icon + '"></i>' + subrow.f_FullName + '';
                                            _html += '<i class="fa fa-angle-left pull-right"></i></a>';
                                            _html += '<ul class="treeview-menu">';
                                            $.each(subchildNodes, function (i) {
                                                var subchildNodesrow = subchildNodes[i];
                                                _html += '<li><a class="menuItem" data-id="' + subchildNodesrow.f_ModuleId + '" href="' + subchildNodesrow.f_UrlAddress + '"><i class="' + subchildNodesrow.f_Icon + '"></i>' + subchildNodesrow.f_FullName + '</a></li>';
                                            });
                                            _html += '</ul>';

                                        } else {
                                            _html += '<a class="menuItem" data-id="' + subrow.f_ModuleId + '" href="' + subrow.f_UrlAddress + '"><i class="' + subrow.f_Icon + '"></i>' + subrow.f_FullName + '</a>';
                                        }
                                        _html += '</li>';
                                    });
                                    _html += '</ul>';
                                }
                                _html += '</li>'
                            }
                        });
                    }else{
                        console.log(res.msg);
                        if(mdata == "" || mdata == null || mdata.length == 0){
                            mdata = menusData_def;
                        }
                    }
                },
                error: function(res) {
                    // 请求失败函数
                    console.log(res);
                    if(mdata == "" || mdata == null || mdata.length == 0){
                        mdata = menusData_def;
                    }
                }
            })
            // console.log("_html==="+_html)
            $("#sidebar-menu").append(_html);
            $("#sidebar-menu li a").click(function () {
                var d = $(this), e = d.next();
                if (e.is(".treeview-menu") && e.is(":visible")) {
                    e.slideUp(500, function () {
                        e.removeClass("menu-open")
                    }),
                    e.parent("li").removeClass("active")
                } else if (e.is(".treeview-menu") && !e.is(":visible")) {
                    var f = d.parents("ul").first(),
                    g = f.find("ul:visible").slideUp(500);
                    g.removeClass("menu-open");
                    var h = d.parent("li");
                    e.slideDown(500, function () {
                        e.addClass("menu-open"),
                        f.find("li.active").removeClass("active"),
                        h.addClass("active");

                        var _height1 = $(window).height() - $("#sidebar-menu >li.active").position().top - 41;
                        var _height2 = $("#sidebar-menu li > ul.menu-open").height() + 10
                        if (_height2 > _height1) {
                            $("#sidebar-menu >li > ul.menu-open").css({
                                overflow: "auto",
                                height: _height1
                            })
                        }
                    })
                }
                e.is(".treeview-menu");
            });
        }
    };
    $(function () {
        $.learunindex.load();
        $.learunindex.loadMenu();
        $.learuntab.init();
    });

    var colorIndex = 0;
    $(function(){
        changeColor(colorIndex);
        $(".hidden-xs").click(function(){
            $("#color_div").hide();
        });
        $("#color").hover(function(){
            $("#color_div").show();
        });
        $(".color_ul li").each(function(index){
            $(this).click(function(){
                if(index<6){
                    changeColor(index)
                }else {
                    changeColor(0)
                }

            })
        })
    });

    var removeElement = function (nums, val) {
        for (var i = 0; i < nums.length; i++) {
            console.log(nums.length)
            if (nums[i] == val) {
                nums.splice(i, 1)
                i = i - 1
            }
        }
        return nums;
    };

    function changeColor(index){
        // alert(index);
        var logo = $(".logo");
        var navbar = $(".skin-blue .main-header .navbar");
        var left_Side = $(".skin-blue .wrapper, .skin-blue .main-sidebar, .skin-blue .left-side");
        var header = $(".skin-blue .sidebar-menu > li.header");
        var treeview_menu = $(".skin-blue .sidebar-menu > li > .treeview-menu");
        var aa = $(".skin-blue .sidebar-menu > li.active > a");
        var page_tabs_content = $(".content-wrapper .content-tabs .page-tabs .page-tabs-content a");
        if(index == ""){
            index=0;
        }
        let arr = [0,1,2,3,4,5];
        arr = removeElement(arr,index);
        for(j = 0,len=arr.length; j < len; j++) {
            let removeIndex = arr[j];
            logo.removeClass("logo"+(removeIndex+1)+"");
            navbar.removeClass("navbar"+(removeIndex+1)+"");
            left_Side.removeClass("left-side"+(removeIndex+1)+"");
            header.removeClass("header"+(removeIndex+1)+"");
            treeview_menu.removeClass("treeview-menu"+(removeIndex+1)+"");
            aa.removeClass("a"+(removeIndex)+"");
        }
        logo.addClass("logo"+(index+1)+"");
        navbar.addClass("navbar"+(index+1)+"");
        left_Side.addClass("left-side"+(index+1)+"");
        header.addClass("header"+(index+1)+"");
        treeview_menu.addClass("treeview-menu"+(index+1)+"");
        aa.addClass("a"+(index)+"");
    }
})(jQuery);
