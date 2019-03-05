package com.e8.frame.service;

import com.e8.frame.model.Employee;
import com.e8.frame.model.LogModel;
import com.e8.frame.tools.PageUtil;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xc
 * Date: 2019-03-01
 * Time:
 */
public interface IEmployeeService {
    /**
     * 分页查询
     * @param employee
     * @param pageUtil
     * @return
     */
    Object getEmployeeBypage(Employee employee,PageUtil pageUtil);

    void insertSelective(Employee employee);

    void deleteByEmployeeId(String id);

    void  updateEmployee(Employee employee);

}
