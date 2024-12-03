package com.Api.Controller;

import com.Api.EmployeeApplication;
import com.Api.Entity.Employee;
import com.Api.Service.EmployeeService;
import com.Api.payload.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDto = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employDto,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployees(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employee){
        Employee updateEmp = employeeService.updateEmployees(id,employee);
        return new ResponseEntity<>(updateEmp,HttpStatus.OK);
    }
}
