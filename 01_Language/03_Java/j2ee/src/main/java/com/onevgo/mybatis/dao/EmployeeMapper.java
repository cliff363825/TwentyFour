package com.onevgo.mybatis.dao;

import com.onevgo.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpByMap(Map<String, Object> map);

    Employee getEmpByIds(List<Integer> ids, String lastName);

    List<Employee> getEmpsByLastNameLike(String lastName);

    /**
     * 返回一条记录的map，key就是列名，值就是对应的值
     *
     * @param id
     * @return
     */
    Map<String, Object> getEmpByIdReturnMap(Integer id);

    /**
     * 多条记录封装一个map：Map&lt;Integer, Employee&gt;: 键是这条记录的主键，值是记录封装后的javaBean
     * 告诉 mybatis 封装这个map 的时候使用哪个属性作为主键
     *
     * @param lastName
     * @return
     */
    @MapKey("id")
    Map<Integer, Employee> getEmpsByLastNameLikeReturnMap(String lastName);

    Employee getEmpAndDept(Integer id);

    Employee getEmpByIdStep(Integer id);

    List<Employee> getEmpsByDeptId(Integer deptId);

    Employee getEmpByIdDis(Integer id);

    /**
     * 携带了哪个字段查询条件就带上这个字段的值
     *
     * @param employee
     * @return
     */
    List<Employee> getEmpsByConditionIf(Employee employee);

    List<Employee> getEmpsByConditionTrim(Employee employee);

    List<Employee> getEmpsByConditionChoose(Employee employee);

    List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    List<Employee> getEmpsByInnerParameter(Employee employee);

    List<Employee> getEmps();

    Long addEmp(Employee employee);

    void addEmps(List<Employee> emps);

    Long updateEmp(Employee employee);

    void updateEmpBySet(Employee employee);

    void updateEmpByTrim(Employee employee);

    Long deleteEmpById(Integer id);
}
