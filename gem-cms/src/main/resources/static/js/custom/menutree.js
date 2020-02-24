/**
 * Created by gemteam on 2020年1月23日 14:38:30
 */
var setting = {

    //是否异步加载 相当于ajax
    async: {
        enable : false,//设置 zTree 是否开启异步加载模式 //默认值：false
        dataFilter: null,
        dataType : "text/json",
        type: "GET",
        url: "../common/findAllMenusTree",
        autoParam: ["id=ids"],//, "name=n", "level=lv"可以设置提交时的参数名称，例如 server 只接受 zId : ["id=zId"] ; 默认值空
        otherParam : [],//其他参数 ;直接用 JSON 格式制作键值对，例如：{ key1:value1, key2:value2 }
        contentType : "application/x-www-form-urlencoded",//设置上下文类型
    },

    edit : { //可以编辑节点 : 增 删 改
        enable : false,//设置 zTree 是否处于编辑状态, true / false 分别表示 可以 / 不可以 编辑
        editNameSelectAll : true,
        showRemoveBtn : true,//设置是否显示删除按钮。[setting.edit.enable = true 时生效]
        showRenameBtn : true,//设置是否显示编辑名称按钮。[setting.edit.enable = true 时生效]
        removeTitle : "remove",//删除按钮的 Title 辅助信息。[setting.edit.enable = true & setting.edit.showRemoveBtn = true 时生效]
        renameTitle : "rename",//编辑名称按钮的 Title 辅助信息。[setting.edit.enable = true & setting.edit.showRenameBtn = true 时生效]

        //drag : {
        //    autoExpandTrigger : true,//拖拽时父节点自动展开是否触发 onExpand 事件回调函数。[setting.edit.enable = true 时生效]
        //    isCopy : true,//拖拽时, 设置是否允许复制节点。[setting.edit.enable = true 时生效]
        //    isMove : true,//拖拽时, 设置是否允许移动节点。[setting.edit.enable = true 时生效]
        //    prev : true, //拖拽到目标节点时，设置是否允许移动到目标节点前面的操作。[setting.edit.enable = true 时生效]
        //    next : true, //拖拽到目标节点时，设置是否允许移动到目标节点后面的操作。[setting.edit.enable = true 时生效]
        //    inner : true, //拖拽到目标节点时，设置是否允许成为目标节点的子节点。[setting.edit.enable = true 时生效]
        //    borderMax : 10,//拖拽节点成为根节点时的 Tree 内边界范围 (单位：px)。[setting.edit.enable = true 时生效]
        //    borderMin : -5,//拖拽节点成为根节点时的 Tree 外边界范围 (单位：px)。[setting.edit.enable = true 时生效]
        //    minMoveSize : 5,//判定是否拖拽操作的最小位移值 (单位：px)。[setting.edit.enable = true 时生效]
        //    maxShowNodeNum : 5,//拖拽多个兄弟节点时，浮动图层中显示的最大节点数。 多余的节点用...代替。[setting.edit.enable = true 时生效]
        //    autoOpenTime : 500//拖拽时父节点自动展开的延时间隔。 (单位：ms)[setting.edit.enable = true 时生效]
        //}
    },

    view: {
        dblClickExpand: true,//双击节点时，是否自动展开父节点的标识
        addDiyDom : null,//用于在节点上固定显示用户自定义控件
        addHoverDom : null,//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
        removeHoverDom : null,//用于当鼠标移出节点时，隐藏用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
        autoCancelSelected : true,//点击节点时，按下 Ctrl 或 Cmd 键是否允许取消选择操作。
        expandSpeed : "fast",//zTree 节点展开、折叠时的动画速度，设置方法同 JQuery 动画效果中 speed 参数。
        fontCss : {}, //个性化文字样式，只针对 zTree 在节点上显示的<A>对象。
        nameIsHTML : false,//设置 name 属性是否支持 HTML 脚本
        selectedMulti : true,//设置是否允许同时选中多个节点。
        showIcon : true,//设置 zTree 是否显示节点的图标。
        showLine : true,//设置 zTree 是否显示节点之间的连线。
        showTitle : true,//设置 zTree 是否显示节点的 title 提示信息(即节点 DOM 的 title 属性)。
        txtSelectedEnable : false//设置 zTree 是否允许可以选择 zTree DOM 内的文本。
    },

    check: {//设置zTree是否可以被勾选,及勾选的参数配置
        enable: false,//设置 zTree 的节点上是否显示 checkbox / radio 默认值: false
        chkStyle : "checkbox",//勾选框类型(checkbox 或 radio）[setting.check.enable = true 时生效]
        autoCheckTrigger : true,//设置自动关联勾选时是否触发 beforeCheck / onCheck 事件回调函数。[setting.check.enable = true 且 setting.check.chkStyle = "checkbox" 时生效]
        chkboxType : {"Y": "ps", "N": "ps"},//勾选 checkbox 对于父子节点的关联关系。[setting.check.enable = true 且 setting.check.chkStyle = "checkbox" 时生效]
        nocheckInherit : false,//当父节点设置 nocheck = true 时，设置子节点是否自动继承 nocheck = true 。[setting.check.enable = true 时生效]
        chkDisabledInherit : false,//当父节点设置 chkDisabled = true 时，设置子节点是否自动继承 chkDisabled = true 。[setting.check.enable = true 时生效]
        radioType : "level"//radio 的分组范围。[setting.check.enable = true 且 setting.check.chkStyle = "radio" 时生效]
    },

    data: { //非常重要
        keep: { //子节点和父节点属性设置 默认值都为false
            leaf: false,//zTree 的节点叶子节点属性锁，是否始终保持 isParent = false
            parent: false//zTree 的节点父节点属性锁，是否始终保持 isParent = true
        },
        key: { //节点数据
            checked: "checked",//zTree 节点数据中保存 check 状态的属性名称。
            children: "children",//zTree 节点数据中保存子节点数据的属性名称。
            name: "name",//zTree 节点数据保存节点名称的属性名称。
            title: "title",//zTree 节点数据保存节点提示信息的属性名称。[setting.view.showTitle = true 时生效]
            url: "url" //设置 zTree 显示节点时，将 treeNode 的 xUrl 属性当做节点链接的目标 URL
        },
        simpleData: {
            enable: false,//确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
            idKey: "id",//节点数据中保存唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]
            pIdKey: "pId",//节点数据中保存其父节点唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]
            rootPId: null//用于修正根节点父节点数据，即 pIdKey 指定的属性值。[setting.data.simpleData.enable = true 时生效]
        }
    },

    callback: { //返回函数; 根据需求选择合适的监听事件  //以下事件默全部为null 事件例子参见第83行
        onRightClick: OnRightClick,
        onClick:OnClick,

        //beforeRemove: beforeRemove,    //节点被删除之前的事件,并且根据返回值确定是否允许删除操作,用于捕获节点编辑名称结束
        //beforeAsync: beforeAsync,    //用于捕获异步加载之前的事件回调函数,zTree 根据返回值确定是否允许进行异步加载
        //onAsyncSuccess: onAsyncSuccess,    //用于捕获异步加载出现异常错误的事件回调函数
        //onAsyncError: onAsyncError,    //用于捕获异步加载正常结束的事件回调函数
        //
        //beforeDrag: beforeDrag,    //用于捕获节点被拖拽之前的事件回调函数，并且根据返回值确定是否允许开启拖拽操作
        //beforeDrop: beforeDrop,    //用于捕获节点拖拽操作结束之前的事件回调函数，并且根据返回值确定是否允许此拖拽操作
        //beforeDragOpen: beforeDragOpen,    //用于捕获拖拽节点移动到折叠状态的父节点后，即将自动展开该父节点之前的事件回调函数，并且根据返回值确定是否允许自动展开操作
        //onDrag: onDrag,    //用于捕获节点被拖拽的事件回调函数
        //onDrop: onDrop,    //用于捕获节点拖拽操作结束的事件回调函数
        //onExpand: onExpand    //用于捕获节点被展开的事件回调函数
    }
};

// 菜单树 开启checkbox
var checkbox_setting = {

    view: {
        dblClickExpand: true,//双击节点时，是否自动展开父节点的标识
        addDiyDom : null,//用于在节点上固定显示用户自定义控件
        addHoverDom : null,//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
        removeHoverDom : null,//用于当鼠标移出节点时，隐藏用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
        autoCancelSelected : true,//点击节点时，按下 Ctrl 或 Cmd 键是否允许取消选择操作。
        expandSpeed : "fast",//zTree 节点展开、折叠时的动画速度，设置方法同 JQuery 动画效果中 speed 参数。
        fontCss : {}, //个性化文字样式，只针对 zTree 在节点上显示的<A>对象。
        nameIsHTML : false,//设置 name 属性是否支持 HTML 脚本
        selectedMulti : true,//设置是否允许同时选中多个节点。
        showIcon : true,//设置 zTree 是否显示节点的图标。
        showLine : true,//设置 zTree 是否显示节点之间的连线。
        showTitle : true,//设置 zTree 是否显示节点的 title 提示信息(即节点 DOM 的 title 属性)。
        txtSelectedEnable : false//设置 zTree 是否允许可以选择 zTree DOM 内的文本。
    },

    check: {//设置zTree是否可以被勾选,及勾选的参数配置
        enable: true,//设置 zTree 的节点上是否显示 checkbox / radio 默认值: false
        chkStyle : "checkbox",//勾选框类型(checkbox 或 radio）[setting.check.enable = true 时生效]
        autoCheckTrigger : true,//设置自动关联勾选时是否触发 beforeCheck / onCheck 事件回调函数。[setting.check.enable = true 且 setting.check.chkStyle = "checkbox" 时生效]
        chkboxType : {"Y": "ps", "N": "ps"},//勾选 checkbox 对于父子节点的关联关系。[setting.check.enable = true 且 setting.check.chkStyle = "checkbox" 时生效]
        nocheckInherit : false,//当父节点设置 nocheck = true 时，设置子节点是否自动继承 nocheck = true 。[setting.check.enable = true 时生效]
        chkDisabledInherit : false,//当父节点设置 chkDisabled = true 时，设置子节点是否自动继承 chkDisabled = true 。[setting.check.enable = true 时生效]
        radioType : "level"//radio 的分组范围。[setting.check.enable = true 且 setting.check.chkStyle = "radio" 时生效]
    },

    callback: { //返回函数; 根据需求选择合适的监听事件  //以下事件默全部为null 事件例子参见第83行
        // TODO:
        onClick: function (e, treeId, treeNode, clickFlag) {
            zTree = $.fn.zTree.getZTreeObj(treeId);
            zTree.checkNode(treeNode, !treeNode.checked, true);
        }
    }
};


function resetTree() {
    hideRMenu();
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    $.fn.zTree.init($("#checkboxTree"), setting, zNodes);
}

//添加/编辑菜单form引用
function OnClick(event, treeId, treeNode){
    $(".dropdown_select").val(treeNode.name);
    $("#pid").val(treeNode.id);
    if(treeNode.level == 3){
        $("#type1").attr('disabled',true);
        $("#type1").removeAttr('checked');
        $("#type2").prop("checked", true);
        $("#tr_menu_icon").css('display','none');
    }else{
        $("#type1").removeAttr('disabled');
        $("#type1").prop("checked", true);
        $("#tr_menu_icon").css('display','');
    }
    $("#level").val(treeNode.level+1);
}


// =========================ajxa方法开始================================
//获取所有菜单
function getMenuTree() {
    //获取所有资源树（包括按钮，其他资源）
    $.ajax({
        type: "get",
        url: "../common/findAllMenusTree",
        data: {
            // "id": id
        },
        async: false, // 异步请求
        cache: false, // 设置为 false 将不缓存此页面
        dataType: 'json', // 返回对象
        success: function (res) {
            console.log(res);
            if(res.code == 0){
                allData  = res.data;
            }
        },
        error: function(res){
            // 请求失败函数
            console.log(res);
        }
    })
    //获取所有菜单树
    $.ajax({
        type: "get",
        url: "../common/findMenusTree",
        data: {
            // "id": id
        },
        async: false, // 异步请求
        cache: false, // 设置为 false 将不缓存此页面
        dataType: 'json', // 返回对象
        success: function (res) {
            console.log(res);
            if(res.code == 0){
                menuData  = res.data;
            }
        },
        error: function(res){
            // 请求失败函数
            console.log(res);
        }
    })
}

var allData  ="";
var menuData  ="";
var zTree, rMenu;
$(document).ready(function(){
    getMenuTree();
    $.fn.zTree.init($("#treeDemo"), setting, menuData);
    $.fn.zTree.init($("#checkboxTree"), checkbox_setting, allData);
    rMenu = $("#rMenu");
});
