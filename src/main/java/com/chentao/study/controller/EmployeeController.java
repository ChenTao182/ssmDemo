package com.chentao.study.controller;

import com.chentao.study.bean.Employee;
import com.chentao.study.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author:Ct
 * Date: 2020/12/2
 * Time: 10:35
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 查询员工数据(分页查询)
     *
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        //这不是一个分页
        //引入PageHelper分页插件
        //在查询这钱只需要调用,传入页码，以及分页每页的大小
        PageHelper.startPage(pn, 5);
        //PageHelper.startPage 后面跟一个查询，就是一个分页查询
        List<Employee> employees = employeeService.getAll();
        //因为要展示分页信息，所以可以用PageHelper的PageInfo来进行包装
        //PageInfo中含有分页信息，比如每页多少条、当前第几页等
        PageInfo pageInfo = new PageInfo(employees, 5);
        //model用来在网页上进行信息携带，跟android intent有点相似
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }
}
