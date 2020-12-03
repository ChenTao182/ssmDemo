package com.chentao.study.test;

import com.chentao.study.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Author:Ct
 * Date: 2020/12/2
 * Time: 11:19
 * 使用spring测试模块提供的测试请求功能，测试CRUD请求的正确性
 */
//SpringJunit4ClassRunner
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:web/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {

    //传入springmvc的ioc
    @Autowired
    WebApplicationContext context;

    //虚拟的MVC，获取到处理结果
    MockMvc mockMvc;

    @Before
    public void initMokcMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();

        //请求成功后，会有pageInfo在返回消息中，通过pageInfo去验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码:" + pageInfo.getPageNum());
        System.out.println("总页码:" + pageInfo.getPages());
        System.out.println("总记录数:" + pageInfo.getTotal());
        System.out.println("在页面需要连续显示的页码:");
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        for (int i : navigatepageNums) {
            System.out.println("" + i);
        }

        //获取员工数据
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println("员工编号 = " + employee.getEmpId());
            System.out.println("员工姓名 = " + employee.getEmpName());
        }

    }
}
