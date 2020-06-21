package com.onevgo.sssp.handler;

import com.onevgo.sssp.entity.Employee;
import com.onevgo.sssp.repository.DepartmentRepository;
import com.onevgo.sssp.repository.EmployeeRepository;
import com.onevgo.sssp.service.DepartmentService;
import com.onevgo.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeHandler {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
                       Model model) {
        int pageNo = 1;

        try {
            // 对 pageNo 的校验
            pageNo = Integer.parseInt(pageNoStr);
            if (pageNo < 1) {
                pageNo = 1;
            }
        } catch (Exception e) {
        }

        Page<Employee> page = employeeService.getPage(pageNo, 5);
        model.addAttribute("page", page);

        return "emp/list";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("employee", new Employee());
        return "emp/input";
    }

    @RequestMapping(value = "/ajaxValidateLastName", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> validateLastName(@RequestParam(value = "lastName", required = true) String lastName) {
        Map<String, Object> result = new HashMap<>();
        boolean exists = employeeService.existsByLastName(lastName);
        if (exists) {
            result.put("code", 1);
        } else {
            result.put("code", 0);
        }
        return result;
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee) {
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable(value = "id") Integer id,
                        Model model) {
        Employee employee = employeeService.get(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "emp/input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "id") Integer id, Employee employee) {
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable(value = "id") Integer id) {
        employeeService.delete(id);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 0);
        return res;
    }
}
