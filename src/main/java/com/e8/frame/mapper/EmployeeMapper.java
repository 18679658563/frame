package com.e8.frame.mapper;

import com.e8.frame.model.Employee;

public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbggenerated
     */
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbggenerated
     */
    int insertSelective(Employee record);
}