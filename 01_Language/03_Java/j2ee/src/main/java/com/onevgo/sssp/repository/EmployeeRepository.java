package com.onevgo.sssp.repository;

import com.onevgo.sssp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getFirstByLastName(String lastName);
    List<Employee> getByLastName(String lastName);
    boolean existsByLastName(String lastName);
}
