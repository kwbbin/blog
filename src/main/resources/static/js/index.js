function initjs () {
    getData("/getIndexMotto",null,function (data) {
        str = data;
        insertMotto(str);
    });

    getData("/getAllArticleType",null,function (data) {
        for(var i = 0;i<data.length;i++){
            $("#tyListBox").append('<li><a href="/sort?id='+data[i].id+'">'+data[i].name+'</a></li>')
        }
    });
}


function insertMotto(str) {
    var classContain =['one','two','three','four','five','six','seven']
    var time=0;
    for(var j = 0 ; j<str.length;j++){
        // var classAnimate = $("<div class=\"animate "+classContain[j]+"\"></div>");
        var obj='';
        for (var i = 0 ;i<str[j].length;i++){
            time=time+0.1;
            obj = obj + "<span style='animation-delay: "+time+"s;'>"+str[j][i]+"</span>"
        }
        $("#index-show").append($("<div class=\"animate "+classContain[j]+"\"></div>").append($(obj)));

    }
}