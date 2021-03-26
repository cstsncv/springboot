package com.csts.restfulcrud.controller;

import com.csts.restfulcrud.dao.EmployeeDao;
import com.csts.restfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    // 查询所有员工
    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();

        // 放到请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }
}
