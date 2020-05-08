package com.kwbbin.manage.service;

import com.kwbbin.Vo.TimeLineVo;
import com.kwbbin.bean.TimeLine;
import com.kwbbin.bean.TimeLineExample;
import com.kwbbin.dao.TimeLineMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TimeLineServiceImpl implements  TimeLineService {

    @Autowired
    TimeLineMapper timeLineMapper;

    @Override
    public List<TimeLine> getAllTimeline() {
        return timeLineMapper.selectByExample(new TimeLineExample());
    }

    @Override
    public Integer deleteTimeline(Integer id) {
        Integer i=-1;
        try {
            timeLineMapper.deleteByPrimaryKey(id);
            i=0;
        }catch (Exception e){

        }
        return i;
    }

    @Override
    public Integer addTimeline(TimeLine timeline) {
        Integer i=-1;
        try {
            timeLineMapper.insert(timeline);
            i=0;
        }catch (Exception e){

        }
        return i;
    }

    @Override
    public Integer updateTimeline(TimeLine timeline) {
        Integer i=-1;
        try {
            timeLineMapper.updateByPrimaryKey(timeline);
            i=0;
        }catch (Exception e){

        }
        return i;
    }

    @Override
    public List<TimeLineVo> getAllTimeLineVo() {
        TimeLineExample timeLineExample = new TimeLineExample();
        timeLineExample.setOrderByClause("post_time desc");
        List<TimeLine> list = timeLineMapper.selectByExample(timeLineExample);
        List<TimeLineVo> listVo = new ArrayList<>();
        for (TimeLine tl : list){
            TimeLineVo timeLineVo = new TimeLineVo();
            BeanUtils.copyProperties(tl,timeLineVo);
            Date dt=tl.getPostTime();
            String year=String.format("%tY", dt);
            String mon=String .format("%tm", dt);
            String day=String .format("%td", dt);
            timeLineVo.setYear(year);
            timeLineVo.setMonthDay(mon+day);
            listVo.add(timeLineVo);
        }
        return listVo;
    }


}
