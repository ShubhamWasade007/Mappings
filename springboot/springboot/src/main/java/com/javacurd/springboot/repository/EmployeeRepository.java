package com.javacurd.springboot.repository;

import com.javacurd.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailId(String emailId);
    List<Employee> findByDepartmentId(Long department_id);
    //all crud database methods
}
