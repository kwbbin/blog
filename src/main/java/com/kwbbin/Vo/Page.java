package com.kwbbin.Vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Page {
    Integer typeId;
    Integer pageNum;
    Integer pageSize;
    Integer TagsId;
}
