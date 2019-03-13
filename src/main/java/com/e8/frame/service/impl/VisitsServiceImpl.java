package com.e8.frame.service.impl;

import com.e8.frame.mapper.LogMapper;
import com.e8.frame.mapper.VisitsMapper;
import com.e8.frame.model.Visits;
import com.e8.frame.model.dto.VisitsDto;
import com.e8.frame.service.IVisitsService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.TimeUtil;
import com.e8.frame.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: sharps
 * @Date: 19-3-5 16:59
 * @Description:
 */
@Service
public class VisitsServiceImpl implements IVisitsService {

    @Autowired
    private VisitsMapper visitsMapper;

    @Autowired
    private LogMapper logMapper;

    @Override
    public Object get() {
//        try{
//            Thread.sleep(10);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        Map map =new HashMap();
        LocalDate localDate= LocalDate.now();
        VisitsDto visitsDto = visitsMapper.findByDate(localDate.toString());
        String start = localDate.minusDays(6).toString();
        String end = localDate.plusDays(1).toString();
        long recentVisits = 0,recentIp = 0;
        List<VisitsDto> list = visitsMapper.findAllVisits(start,end);
        recentIp = logMapper.findIp(start,end);

        for (VisitsDto data : list) {
            recentVisits += data.getPvCounts();
            //recentIp += data.getIpCounts();
        }
    if (visitsDto!=null){
        map.put("newVisits", visitsDto.getPvCounts());
        map.put("newIp",visitsDto.getIpCounts());
        map.put("recentVisits",recentVisits);
        map.put("recentIp",recentIp);
    }else{
        map.put("newVisits", 1);
        map.put("newIp",1);
        map.put("recentVisits",0);
        map.put("recentIp",0);
    }

        return map;
    }

    @Override
    public void count(){
        LocalDate localDate = LocalDate.now();
        VisitsDto visitsDto =visitsMapper.findByDate(localDate.toString());
        if(visitsDto == null){
            visitsDto = new VisitsDto();
            visitsDto.setId(UUIDUtil.getUUID());
            visitsDto.setCreateTime(new Date());
            visitsDto.setWeekDay(TimeUtil.getWeekDay());
            visitsDto.setPvCounts(1L);
            visitsDto.setIpCounts(1L);
            visitsDto.setDate(localDate.toString());
            visitsMapper.insert(BeanUtil.createBeanByTarget(visitsDto,Visits.class));
        }else {
            visitsDto.setPvCounts(visitsDto.getPvCounts()+1);
            Long ipCounts = logMapper.findIp(localDate.toString(), localDate.plusDays(1).toString());
            visitsDto.setIpCounts(ipCounts);
            visitsMapper.updateByDate(BeanUtil.createBeanByTarget(visitsDto,Visits.class));
        }

    }


    @Override
    public Object getChartData() {
        Map map = new HashMap();
        LocalDate localDate = LocalDate.now();
        List<VisitsDto> list = visitsMapper.findAllVisits(localDate.minusDays(6).toString(),localDate.plusDays(1).toString());
        map.put("weekDays",list.stream().map(VisitsDto::getWeekDay).collect(Collectors.toList()));
        map.put("visitsData",list.stream().map(VisitsDto::getPvCounts).collect(Collectors.toList()));
        map.put("ipData",list.stream().map(VisitsDto::getIpCounts).collect(Collectors.toList()));
        map.put("creatTime",list.stream().map(VisitsDto::getCreateTime).collect(Collectors.toList()));
        return map;
    }



}
