


function getData(url,obj,suFun,fileFun){
    $.ajax({
        url: url,
        type: 'post',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType: "application/json",
        cache : false,
        success: function(data) {
            if(suFun!=undefined&&suFun!=null){
                suFun(data);
            }
        },
        error: function(data) {
            if(fileFun!=undefined&&fileFun!=null){
                fileFun(data);
             }else{
                console.log("ajax请求错误");
                console.log(data);
            }


        }
    })
}

function FormatTime (data,type){
    var _data = data;
    //如果是13位正常，如果是10位则需要转化为毫秒
    if (String(data).length == 13) {
        _data = data
    } else {
        _data = data*1000
    }
    const time = new Date(_data);
    const Y = time.getFullYear();
    const Mon = time.getMonth() + 1;
    const Day = time.getDate();
    const H = time.getHours();
    const Min = time.getMinutes();
    const S = time.getSeconds();
    //自定义选择想要返回的类型
    if(type=="Y"){
        return `${Y}-${Mon}-${Day}`
    }else if(type=="H"){
        return `${H}:${Min}:${S}`
    }else{
        return `${Y}-${Mon}-${Day} ${H}:${Min}:${S}`
    }
}

function searchSubmit() {
   var text = $("#searchText")[0].value;
    var parm = [
        {name:'search',value:text}
    ]
   createForm("/SearchArticles",parm);
   // alert(text);
}


function createForm(url,array) {
    //参数示例
    // [
    //     {name:sfsf,value:''},
    //     {name:sfsf,value:''}
    // ]
    var form = $('<form></form>');
    form.attr('action', url);
    form.attr("method",'post');
    form.attr("style",'display:none');
    var l = array.length;
    if(l>0){
        for(var i = 0;i<l;i++){
            form.append($('<input type="text" name="'+array[i].name+'" value="'+array[i].value+'" />'))
        }
    }
    $("body").append(form);
    form.submit();
}