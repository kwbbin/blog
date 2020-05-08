package com.kwbbin.manage.service;

import com.kwbbin.Vo.TimeLineVo;
import com.kwbbin.bean.TimeLine;

import java.util.List;

public interface TimeLineService {
    List<TimeLine> getAllTimeline();

    Integer deleteTimeline(Integer id);

    Integer addTimeline(TimeLine timeline);

    Integer updateTimeline(TimeLine timeline);

    List<TimeLineVo> getAllTimeLineVo();
}
