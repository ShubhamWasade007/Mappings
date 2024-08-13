package com.javacurd.springboot.service;

import com.javacurd.springboot.exception.EmployeeAlreadyExistException;
import com.javacurd.springboot.exception.NoDepartmentsFoundException;
import com.javacurd.springboot.exception.NoSuchEmployeeException;
import com.javacurd.springboot.model.Department;
import com.javacurd.springboot.model.Employee;
import com.javacurd.springboot.model.Project;
import com.javacurd.springboot.repository.AddressRepository;
import com.javacurd.springboot.repository.DepartmentRepository;
import com.javacurd.springboot.repository.EmployeeRepository;
import com.javacurd.springboot.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Employee> getAllEmployee(){
        List<Employee> employee =  employeeRepository.findAll();
        if (employee.isEmpty()){
            throw new NoSuchEmployeeException("No Data Found");
        }
        return employee;
    }

    public Employee createEmployee(Employee employee){
        if(employeeRepository.findByEmailId(employee.getEmailId()).isPresent()){
            throw new EmployeeAlreadyExistException("Already Exist employee");
        }
        if (employee.getAddress() != null){
            addressRepository.save(employee.getAddress());
        }
        if (employee.getDepartment() != null){
            departmentRepository.save(employee.getDepartment());
        }
        if (employee.getProject() != null){
            for(Project project : employee.getProject()){
                projectRepository.save(project);
            }
        }
        return employeeRepository.save(employee);

    }

    public Employee getEmployeeById(Long id)
    {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new NoSuchEmployeeException("Employee Not Found:" + id));
        return employee;
    }

    public void deleteEmployee(Long id)
    {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new NoSuchEmployeeException("Employee Not Found:" + id));
        employeeRepository.delete(employee);
    }

    public  void deleteAllEmployee(){
            employeeRepository.deleteAll();
    }


    public Employee updateEmployee(Long id, Employee employeeDetails)
    {
        Employee updateEmployee = employeeRepository.findById(id).
                orElseThrow(()-> new NoSuchEmployeeException("Employee Not Found:" + id));
        if(employeeDetails.getFirstName() != null){
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        }
        if(employeeDetails.getLastName() != null){
        updateEmployee.setLastName(employeeDetails.getLastName());
        }
        if (employeeDetails.getEmailId() != null){
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        }
        if (employeeDetails.getAddress() != null){
            updateEmployee.setAddress(employeeDetails.getAddress());
        }
        if (employeeDetails.getDepartment() != null){
            updateEmployee.setDepartment(employeeDetails.getDepartment());
        }
        if (employeeDetails.getProject() != null){
            updateEmployee.setProject(employeeDetails.getProject());
        }

        return employeeRepository.save(updateEmployee);
    }

    //find employee by department_Id
    public List<Employee> findEmployeesByDepartment(Long department_id){
        List<Employee> employee = employeeRepository.findByDepartmentId(department_id);
        if(employee.isEmpty()){
            throw new NoSuchEmployeeException("Not Found Any");
        }
        return employee;
    }

    //get all department
    public List<Department> getAllDepartment(){
        List<Department> departments = departmentRepository.findAll();
        if(departments.isEmpty()){
            throw new NoDepartmentsFoundException("No Department Fount");
        }
        return departments;
    }
}
