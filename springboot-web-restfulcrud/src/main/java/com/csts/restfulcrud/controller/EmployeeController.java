package com.csts.restfulcrud.controller;

import com.csts.restfulcrud.dao.DepartmentDao;
import com.csts.restfulcrud.dao.EmployeeDao;
import com.csts.restfulcrud.entities.Department;
import com.csts.restfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee){

        System.out.println("add Employee: " + employee);
        employeeDao.save(employee);
        // redirect: 重定向
        // forward: 转发
        return "redirect:/emps";
    }

    // 修改, 查出当前员工, 在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        //回到修改页面(add页面是一个修改添加二合一页面)
        return "emp/add";
    }

    // 员工修改
    @PutMapping("/emp")
    public String editEmp(Employee employee){
        System.out.println("edit Employee: " + employee);
        employeeDao.save(employee);
        // redirect: 重定向
        // forward: 转发
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        System.out.println("delete EmployeeId: " + id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
