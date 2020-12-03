package com.chentao.study.dao;

import com.chentao.study.bean.Employee;
import com.chentao.study.bean.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * sql联合查询 FROM tbl_emp e LEFT JOIN tbl_dept d ON e.`d_id`=d.`dept_id`
 *  tbl_emp代理e   LEFT JOIN--联合 tbl_dept代理d , 当e的d_id外键字段 等于 d中dept_id
 */
public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    List<Employee> selectByExampleWithDept(EmployeeExample example);

    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}