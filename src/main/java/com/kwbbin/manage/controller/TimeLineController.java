package com.kwbbin.manage.controller;

import com.kwbbin.Vo.TimeLineVo;
import com.kwbbin.bean.FriendLink;
import com.kwbbin.bean.TimeLine;
import com.kwbbin.manage.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class TimeLineController {
    @Autowired
    TimeLineService timeLineService;

//    @RequestMapping("/getAllTimeLine")
//    public ModelAndView selectAllTimeLine(){
//        ModelAndView mv = new ModelAndView();
//        List<TimeLine> list = timeLineService.getAllTimeline();
//        mv.addObject("timeLine",list);
//        mv.setViewName("/manage/timeLine");
//        return mv;
//    }

    @RequestMapping("/updateTimeLine")
    public ModelAndView updateTimeLine(String date , String content, String title, Integer id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=null;
        try {
            date1= sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView();
        TimeLine timeLine = new TimeLine();
        timeLine.setContent(content);
        timeLine.setTitle(title);
        timeLine.setPostTime(date1);
        timeLine.setId(id);
        timeLineService.updateTimeline(timeLine);
        mv.setViewName("redirect:/manage/timeLine");
        return mv;
    }

    @RequestMapping("/addTimeLine")
    public ModelAndView addTimeLine(String content, String title){
        ModelAndView mv = new ModelAndView();
        TimeLine timeLine  = new TimeLine();
        timeLine.setTitle(title);
        timeLine.setContent(content);
        timeLine.setPostTime(new Date());
        timeLineService.addTimeline(timeLine);
        mv.setViewName("redirect:/manage/timeLine");
        return mv;
    }

    @RequestMapping("/deleteTimeLine")
    public ModelAndView deleteTimeLine(Integer id){
        ModelAndView mv = new ModelAndView();
        timeLineService.deleteTimeline(id);
        mv.setViewName("redirect:/manage/timeLine");
        return mv;
    }

//    @RequestMapping("/getTimeLineVoList")
//    @ResponseBody
//    public List<TimeLineVo> getTimeLineVoList(){
//
//        return timeLineService.getAllTimeLineVo();
//    }

}
