package com.e8.frame.service.impl;

import com.e8.frame.mapper.EmployeeMapper;
import com.e8.frame.mapper.UserMapper;
import com.e8.frame.model.Employee;
import com.e8.frame.model.User;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IEmployeeService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.PageUtil;
import com.e8.frame.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public Object getEmployeeBypage(Employee employee, PageUtil pageUtil) {
        List<Employee> list =employeeMapper.getEmployeeBypage(employee,pageUtil);
        Integer count =employeeMapper.selectCount(employee);
        pageUtil.setList(list);
        pageUtil.setCount(count);
        return PageUtil.toResult(pageUtil);
    }

    @Override
    @Transactional
    public void insertSelective(Employee employee) {
        String uuid = UUIDUtil.getUUID();
        employee.setId(uuid);
        employee.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(employee.getType().equals(true)){
            UserDto user =new UserDto();
            User u = BeanUtil.createBeanByTarget(user, User.class);
            u.setId(uuid);
            u.setPassword("14e1b600b1fd579f47433b88e8d85291");
            u.setAvatar("https://i.loli.net/2018/12/06/5c08894d8de21.jpg");
            u.setCreateTime(new Timestamp(System.currentTimeMillis()));
            u.setUsername(employee.getName());
            u.setEmail(employee.getEmail());
            u.setEnabled(true);
            userMapper.insertSelective(u);
        }
        employeeMapper.insertSelective(employee);
    }

    /**
     * 根据id逻辑删除员工
     * @param id
     */
    @Override
    @Transactional
    public void deleteByEmployeeId(String id) {
        Employee employee =employeeMapper.getEmployeeById(id);
        if(employee.getType().equals(true)){
            userMapper.deleteByPrimaryKey(id);
        }
        employeeMapper.updateStatById(id);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }
}
