package com.example.trial.controller;

import com.example.trial.model.Employee;
import com.example.trial.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EmployeeController {

    //Display employee list
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String viewHomePage(Model model) {
      model.addAttribute("listEmployees",employeeService.getAllEmployees());
      return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value="id") long id, Model model){
        //get employee
        Employee employee = employeeService.getEmployeeById(id);

        //set employee as a model to pre-fill the form data
        model.addAttribute("employee",employee);

        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value="id") long id){
        //get employee
        Employee employee = employeeService.getEmployeeById(id);

        //delete employee
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}

