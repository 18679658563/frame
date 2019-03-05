package com.e8.frame.mapper;

import com.e8.frame.model.Employee;
import com.e8.frame.tools.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xc
 * Date: 2019-03-01
 * Time:
 */
@Mapper
public interface EmployeeMapper {
    List<Employee> getEmployeeBypage(@Param("employee") Employee employee,@Param("page") PageUtil pageUtil);

    int selectCount(Employee employee);

    int insertSelective(Employee employee);

    //逻辑删除
    int updateStatById(String id);

    int updateByPrimaryKeySelective(Employee record);

    Employee getEmployeeById(String id);



}
