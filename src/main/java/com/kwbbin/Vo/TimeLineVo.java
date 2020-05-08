package com.kwbbin.Vo;

import com.kwbbin.bean.TimeLine;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TimeLineVo extends TimeLine {
    String year;
    String monthDay;
}
