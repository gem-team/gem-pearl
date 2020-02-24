layui.use(['form','laydate','laypage','layer','table'], function () {
    let form = layui.form
        layer = layui.layer,
        table = layui.table;
    let i=1,j=1,k=1;

    //监听指定开关
    form.on('switch(switchTest)', function(data){
        let id = $(this).data('id');
        let checked = data.elem.checked;
        console.log(id+"的值："+checked)
        if(checked){
            $("#"+id).val(1);
        }else{
            $("#"+id).val(0);
        }
    })


    $('.add-btn').click(function () {
        i++;
        addstrs1(i);
        form.render();
    });

    $('body').on("click",".btn-del",function (obj) {
        i--;
        var pre = $(this);
        $(pre).parent().parent().remove();
        layer.closeAll('dialog');
    });


    function addstrs1(i) {
        let strs1 = '<tr rowId="">\n' +
            '           <td>\n' +
            '               <input type="text" name="attrSort" class="layui-input" value="'+i+'">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="text" name="attrName" class="layui-input">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <select name="attrType" lay-filter="">\n' +
            '                    <option value="text">文字类型</option>\n' +
            '                    <option value="email">邮箱类型</option>\n' +
            '                    <option value="number">数值类型</option>\n' +
            '                    <option value="tel">电话类型</option>\n' +
            '                    <option value="select">下拉类型</option>\n' +
            '                    <option value="checkbox">多选类型</option>\n' +
            '                    <option value="radio">单选类型</option>\n' +
            // '                    <option value="DECIMAL">DECIMAL</option>\n' +
            '                    <option value="date">时间类型</option>' +
            '                    <option value="hidden">隐藏类型</option>' +
            '                    <option value="password">密码类型</option>' +
            '               </select>\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="text" name="minLength" class="layui-input">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="text" name="maxLength" class="layui-input">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="text" name="comment" class="layui-input">\n' +
            '           </td>'+
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isNull_'+i+'" data-id="isNull_'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF" checked>\n' +
            '               <input type="hidden" id="isNull_'+i+'" name="isNull" value="1">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isVisit_'+i+'" data-id="isVisit'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF" checked>\n' +
            '               <input type="hidden" id="isVisit'+i+'" name="isVisit" value="1">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isSort_'+i+'" data-id="isSort_'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF" checked>\n' +
            '               <input type="hidden" id="isSort_'+i+'" name="isSort" value="1">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isSearch_'+i+'" data-id="isSearch_'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF" checked>\n' +
            '               <input type="hidden" id="isSearch_'+i+'" name="isSearch" value="1">\n' +
            '           </td>\n' +
            '           <td style="text-align: center">' +
            // '              <button type="button" class="layui-btn layui-btn-danger btn-del layui-btn-sm">删除</button>' +
            '              <i class="layui-icon layui-icon-reduce-circle btn-del" style="font-size: 18px; color: #1E9FFF;"></i>' +
            '          </td>\n' +
            '       </tr>';
        $('.addlists').append(strs1);
    }
})


function reViewData(data) {
    for(let i=0;i<data.length;i++){
        let editHtml = '<tr rowId="'+data[i].id+'">\n' +
            '              <td>\n' +
            '                 <input type="hidden" name="id" class="layui-input" value="'+data[i].id+'" >' +
            '                 <input type="text" name="attrSort" class="layui-input" ' +
            '                                   value="'+data[i].attrSort+'">\n' +
            '              </td>\n' +
            '              <td>\n' +
            '                 <input type="text" name="attrName" class="layui-input" ' +
            '                                   value="'+data[i].attrName+'">\n' +
            '              </td>\n' +
            '              <td>\n' +
            '                 <select name="attrType" lay-filter="" value="'+data[i].attrType+'">\n' +
            '                    <option value="text">文字类型</option>\n' +
            '                    <option value="email">邮箱类型</option>\n' +
            '                    <option value="number">数值类型</option>\n' +
            '                    <option value="tel">电话类型</option>\n' +
            '                    <option value="select">下拉类型</option>\n' +
            '                    <option value="checkbox">多选类型</option>\n' +
            '                    <option value="radio">单选类型</option>\n' +
            '                    <option value="date">时间类型</option>' +
            '                    <option value="hidden">隐藏类型</option>' +
            '                    <option value="password">密码类型</option>' +
            '               </select>\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="text" name="minLength" class="layui-input" value="'+data[i].minLength+'">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="text" name="maxLength" class="layui-input" value="'+data[i].maxLength+'">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="text" name="comment" class="layui-input" value="'+data[i].comment+'">\n' +
            '           </td>'+
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isNull_'+i+'" data-id="isNull_'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF">\n' +
            '               <input type="hidden" id="isNull_'+i+'" name="isNull" value="'+data[i].isNull+'">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isVisit_'+i+'" data-id="isVisit'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF">\n' +
            '               <input type="hidden" id="isVisit'+i+'" name="isVisit" value="'+data[i].isVisit+'">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isSort_'+i+'" data-id="isSort_'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF">\n' +
            '               <input type="hidden" id="isSort_'+i+'" name="isSort" value="'+data[i].isSort+'">\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <input type="checkbox" id="checkbox_isSearch_'+i+'" data-id="isSearch_'+i+'" lay-skin="switch" ' +
            '                  lay-filter="switchTest" lay-text="ON|OFF">\n' +
            '               <input type="hidden" id="isSearch_'+i+'" name="isSearch" value="'+data[i].isSearch+'">\n' +
            '           </td>\n' +
            '           <td style="text-align: center">' +
            '              <i class="layui-icon layui-icon-reduce-circle btn-del" style="font-size: 18px; color: #1E9FFF;"></i>' +
            '          </td>\n' +
            '       </tr>';
        $('.addlists').append(editHtml);
        $('#checkbox_isNull_'+i+'').attr('checked', data[i].isNull == 1);
        $('#checkbox_isVisit_'+i+'').attr('checked', data[i].isVisit == 1);
        $('#checkbox_isSort_'+i+'').attr('checked', data[i].isSort == 1);
        $('#checkbox_isSearch_'+i+'').attr('checked', data[i].isSearch == 1);
    }
}