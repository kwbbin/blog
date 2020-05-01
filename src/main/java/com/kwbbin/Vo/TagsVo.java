package com.kwbbin.Vo;

import com.kwbbin.bean.Tags;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TagsVo extends Tags {
    Boolean isChecked;
    Boolean DraftIsChecked;
}
