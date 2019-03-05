package com.e8.frame.controller;

import com.e8.frame.exception.BadRequestException;
import com.e8.frame.mapper.EmployeeMapper;
import com.e8.frame.model.Employee;
import com.e8.frame.service.IEmployeeService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 员工api
 * User: xc
 * Date: 2019-03-01
 * Time: 11:18
 */
@Controller
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询
     * @param employee
     * @param page
     * @return
     */
    @RequestMapping(value = "/employees",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN','Employee_ALL','Employee_SELECT')")
    public ResponseEntity getEmployee(Employee employee, PageUtil page){
        iEmployeeService.getEmployeeBypage(employee,page);
        return  new ResponseEntity(iEmployeeService.getEmployeeBypage(employee,page),HttpStatus.OK);
    }

    /**
     * 添加
     * @param employee
     * @return
     */
    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ADMIN','Employee_ALL','Employee_CREATE')")
    public ResponseEntity createEmployee(@RequestBody Employee employee){
        iEmployeeService.insertSelective(employee);
        return  new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     *删除
     * @param
     * @param id
     * @return
     */
    @RequestMapping(value = "/employees/{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ADMIN','Employee_ALL','Employee_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        iEmployeeService.deleteByEmployeeId(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 修改
     * @param employee
     * @return
     */
    @RequestMapping(value = "/employees",method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ADMIN','Employee_ALL','Employee_EDIT')")
    public ResponseEntity update(@RequestBody Employee employee){
        if (employee.getId() == null) {
            throw new BadRequestException("id Can not be empty!");
        }
        iEmployeeService.updateEmployee(employee);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

