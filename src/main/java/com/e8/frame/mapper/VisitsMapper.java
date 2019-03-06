package com.e8.frame.mapper;

import com.e8.frame.model.Visits;
import com.e8.frame.model.dto.VisitsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VisitsMapper {

    int insert(Visits record);

    int insertSelective(Visits record);

    VisitsDto findByDate(String date);

    void updateByDate(Visits record);

    List<VisitsDto> findAllVisits(@Param("start") String start,@Param("end") String end);
}