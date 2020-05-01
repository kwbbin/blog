package com.kwbbin.Vo;

import com.kwbbin.bean.Draft;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DraftVo extends Draft {
    String articleOriginStr;
    String articleTypeStr;
    String articleWayStr;
}
