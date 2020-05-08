package com.kwbbin.Vo;

import com.kwbbin.bean.MyMessage;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MessageVo{
    Long dateLong;
    MyMessage myMessage;
    List<MyMessage> replay;
}
