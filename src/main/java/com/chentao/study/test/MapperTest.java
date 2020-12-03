package com.chentao.study.test;

import com.chentao.study.bean.Department;
import com.chentao.study.bean.Employee;
import com.chentao.study.dao.DepartmentMapper;
import com.chentao.study.dao.EmployeeMapper;
import javafx.application.Application;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Author:Ct
 * Date: 2020/12/2
 * Time: 10:10
 *
 * "@ContextConfiguration":指定Spring配置文件的位置
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    /**
     * 测试DepartMentMapper
     */
    @Test
    public void testCRUD(){
//        //1.创建springIOC容器
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从容器中获取mapper
//        ioc.getBean(DepartmentMapper.class);

        //1.插入几个部门
//        Department department = new Department();
//        department.setDeptId(1);
//        department.setDeptName("研发部");
//        Department department2 = new Department();
//        department2.setDeptId(2);
//        department2.setDeptName("测试部");
//        Department department3 = new Department();
//        department3.setDeptId(3);
//        department3.setDeptName("销售部");
//        departmentMapper.insertSelective(department);
//        departmentMapper.insertSelective(department2);
//        departmentMapper.insertSelective(department3);

        //2.生成员工数据，
//        employeeMapper.insertSelective(new Employee(null,"chentao","M","1142553965@qq.com",1));
        //3.批量插入多个员工,批量，使用可以执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i=0;i<1000;i++){
            //批量插入1000条
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
        }

    }
}
