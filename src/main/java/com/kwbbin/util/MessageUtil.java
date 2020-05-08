package com.kwbbin.util;

import com.kwbbin.bean.Comment;
import com.kwbbin.bean.MyMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageUtil {

    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;


    public MyMessage messageFiller(MyMessage myMessage){
        String content =myMessage.getContent();
        content = translateStr(content);
        myMessage.setContent(content);
        if ("".equals(myMessage.getName())||myMessage.getName()==null){
            myMessage.setName("匿名网友");
        }
        return myMessage;
    }


    public List<MyMessage> messageListFiller(List<MyMessage> list){
        List<MyMessage> list1 = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            list1.add(messageFiller(list.get(i)));
        }
        return list1;
    }

    public Comment commentFiller(Comment comment){
        String content =comment.getContent();
        content = translateStr(content);
        comment.setContent(content);
        if ("".equals(comment.getName())||comment.getName()==null){
            comment.setName("匿名网友");
        }
        return comment;
    }


    public List<Comment> commentListFiller(List<Comment> list){
        List<Comment> list1 = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            list1.add(commentFiller(list.get(i)));
        }
        return list1;
    }

    private String translateStr(String content){
        content = content.replace("[/emoji2_1]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_1.png\">");
        content = content.replace("[/emoji2_2]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_2.png\">");
        content = content.replace("[/emoji2_3]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_3.png\">");
        content = content.replace("[/emoji2_4]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_4.png\">");
        content = content.replace("[/emoji2_5]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_5.png\">");
        content = content.replace("[/emoji2_6]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_6.png\">");
        content = content.replace("[/emoji2_7]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_7.png\">");
        content = content.replace("[/emoji2_8]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_8.png\">");
        content = content.replace("[/emoji2_9]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_9.png\">");
        content = content.replace("[/emoji2_10]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_10.png\">");
        content = content.replace("[/emoji2_11]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_11.png\">");
        content = content.replace("[/emoji2_12]","<img width=\"25\" height=\"25\" src=\""+localInfoPath+"/blog/file/emoji/emoji2/emoji2_12.png\">");

        content = content.replace("[/emoji1_1]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_1.jpg\">");
        content = content.replace("[/emoji1_2]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_2.jpg\">");
        content = content.replace("[/emoji1_3]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_3.jpg\">");
        content = content.replace("[/emoji1_4]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_4.jpg\">");
        content = content.replace("[/emoji1_5]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_5.jpg\">");
        content = content.replace("[/emoji1_6]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_6.jpg\">");
        content = content.replace("[/emoji1_7]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_7.jpg\">");
        content = content.replace("[/emoji1_8]","<img width=\"30\" height=\"30\" src=\""+localInfoPath+"/blog/file/emoji/emoji1/emoji1_8.jpg\">");


        content = content.replace("[/emoji3_1]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_1.gif\">");
        content = content.replace("[/emoji3_2]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_2.gif\">");
        content = content.replace("[/emoji3_3]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_3.gif\">");
        content = content.replace("[/emoji3_4]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_4.gif\">");
        content = content.replace("[/emoji3_5]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_5.gif\">");
        content = content.replace("[/emoji3_6]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_6.gif\">");
        content = content.replace("[/emoji3_7]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_7.gif\">");
        content = content.replace("[/emoji3_8]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_8.gif\">");
        content = content.replace("[/emoji3_9]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_9.gif\">");
        content = content.replace("[/emoji3_10]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_10.gif\">");
        content = content.replace("[/emoji3_11]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_11.gif\">");
        content = content.replace("[/emoji3_12]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_12.gif\">");
        content = content.replace("[/emoji3_13]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_13.gif\">");
        content = content.replace("[/emoji3_14]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_14.gif\">");
        content = content.replace("[/emoji3_15]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_15.gif\">");
        content = content.replace("[/emoji3_16]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_16.gif\">");
        content = content.replace("[/emoji3_17]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_17.gif\">");
        content = content.replace("[/emoji3_18]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_18.gif\">");
        content = content.replace("[/emoji3_19]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_19.gif\">");
        content = content.replace("[/emoji3_20]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji3/emoji3_20.gif\">");


        content = content.replace("[/emoji4_1]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_1.gif\">");
        content = content.replace("[/emoji4_2]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_2.gif\">");
        content = content.replace("[/emoji4_3]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_3.gif\">");
        content = content.replace("[/emoji4_4]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_4.gif\">");
        content = content.replace("[/emoji4_5]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_5.gif\">");
        content = content.replace("[/emoji4_6]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_6.gif\">");
        content = content.replace("[/emoji4_7]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_7.gif\">");
        content = content.replace("[/emoji4_8]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_8.gif\">");
        content = content.replace("[/emoji4_9]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_9.gif\">");
        content = content.replace("[/emoji4_10]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_10.gif\">");
        content = content.replace("[/emoji4_11]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_11.gif\">");
        content = content.replace("[/emoji4_12]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_12.gif\">");
        content = content.replace("[/emoji4_13]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_13.gif\">");
        content = content.replace("[/emoji4_14]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_14.gif\">");
        content = content.replace("[/emoji4_15]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_15.gif\">");
        content = content.replace("[/emoji4_16]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_16.gif\">");
        content = content.replace("[/emoji4_17]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_17.gif\">");
        content = content.replace("[/emoji4_18]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_18.gif\">");
        content = content.replace("[/emoji4_19]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_19.gif\">");
        content = content.replace("[/emoji4_20]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_20.gif\">");
        content = content.replace("[/emoji4_21]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_21.gif\">");
        content = content.replace("[/emoji4_22]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_22.gif\">");
        content = content.replace("[/emoji4_23]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_23.gif\">");
        content = content.replace("[/emoji4_24]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_24.gif\">");
        content = content.replace("[/emoji4_25]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_25.gif\">");
        content = content.replace("[/emoji4_26]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_26.gif\">");
        content = content.replace("[/emoji4_27]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_27.gif\">");
        content = content.replace("[/emoji4_28]","<img width=\"80\" height=\"80\" src=\""+localInfoPath+"/blog/file/emoji/emoji4/emoji4_28.gif\">");
        return content;
    }

}
