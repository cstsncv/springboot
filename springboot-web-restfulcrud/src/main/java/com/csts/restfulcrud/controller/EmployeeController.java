package com.csts.restfulcrud.controller;

import com.csts.restfulcrud.dao.DepartmentDao;
import com.csts.restfulcrud.dao.EmployeeDao;
import com.csts.restfulcrud.entities.Department;
import com.csts.restfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    // 查询所有员工
    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();

        // 放到请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //添加页面
    @GetMapping("/emp")
    public String addPage(Model model){
        // 来到添加页面, 先查出所有的部门, 在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }


    @PostMapping("/emp")
    public String addEmp(Employee employee){

        System.out.println(employee);
        employeeDao.save(employee);
        // redirect: 重定向
        // forward: 转发
        return "redirect:/emps";
    }
}
