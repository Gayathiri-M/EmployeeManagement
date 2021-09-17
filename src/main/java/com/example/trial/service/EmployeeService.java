package com.example.trial.service;

import com.example.trial.model.Employee;
import java.util.List;


public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);

    void saveEmployee(Employee employee);

    void deleteEmployeeById(long id);

}
