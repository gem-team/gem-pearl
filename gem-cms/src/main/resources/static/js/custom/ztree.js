
//获取所有选中节点的值
function GetCheckedAll(treeId) {
    var treeObj = $.fn.zTree.getZTreeObj(treeId);
    var nodes = treeObj.getCheckedNodes(true);
    return nodes;
}


//全选
function CheckAllNodes(treeId) {
    var treeObj = $.fn.zTree.getZTreeObj(treeId);
    treeObj.checkAllNodes(true);
}

//全取消
function CancelAllNodes(treeId) {
    var treeObj = $.fn.zTree.getZTreeObj(treeId);
    treeObj.checkAllNodes(false);
}

//自定义右键方法开始
function OnRightClick(event, treeId, treeNode) {
    zTree = $.fn.zTree.getZTreeObj(treeId);
    alert("点击了右键");
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        zTree.cancelSelectedNode();
        showRMenu("root", event.clientX, event.clientY);
    } else if (treeNode) {
        zTree.selectNode(treeNode);
        showRMenu("node", event.clientX, event.clientY);
    }
}
function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    if (type=="root") {
        $("#m_del").hide();
        $("#m_check").hide();
        $("#m_unCheck").hide();
    } else {
        $("#m_del").show();
        $("#m_check").show();
        $("#m_unCheck").show();
    }
    rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
    $("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
        rMenu.css({"visibility" : "hidden"});
    }
}


var addCount = 1;
//添加节点
function addTreeNode(names) {
    hideRMenu();
    var newNode = { name:names + (addCount++)};
    if (zTree.getSelectedNodes()[0]) {
        newNode.checked = zTree.getSelectedNodes()[0].checked;
        zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
    } else {
        zTree.addNodes(null, newNode);
    }
}
//删除节点
function removeTreeNode() {
    hideRMenu();
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        if (nodes[0].children && nodes[0].children.length > 0) {
            var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
            if (confirm(msg)==true){
                zTree.removeNode(nodes[0]);
            }
        } else {
            zTree.removeNode(nodes[0]);
        }
    }
}

// 校验节点
function checkTreeNode(checked) {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        zTree.checkNode(nodes[0], checked, true);
    }
    hideRMenu();
}
