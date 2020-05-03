getData("/getAllArticleType",null,function (data) {
    for(var i = 0;i<data.length;i++){
        $("#tyListBox").append('<li><a href="/sort?id='+data[i].id+'">'+data[i].name+'</a></li>')
    }
});

function getData(url,obj,suFun){
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
            console.log("ajax请求错误");
            console.log(data);

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