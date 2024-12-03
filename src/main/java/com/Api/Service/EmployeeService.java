package com.Api.Service;

import com.Api.Entity.Employee;
import com.Api.Repository.EmployeeRepository;
import com.Api.payload.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> getAllEmployee(){
        List<Employee> employeeList =employeeRepository.findAll();
        List<EmployeeDto> dtos = employeeList.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dtos;
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        //copy to entity
       Employee employee= mapToEntity(employeeDto);
      //  Employee employee = new Employee();
      //  employee.setName(employeeDto.getName());
      //  employee.setEmail(employeeDto.getEmail());
      //  employee.setMobile(employeeDto.getMobile());

        Employee savedEntity= employeeRepository.save(employee);
        EmployeeDto dto = mapToDto(savedEntity);
        //copy to dto
       // EmployeeDto dto = new EmployeeDto();
      //  dto.setName(savedEntity.getName());
      //  dto.setEmail(savedEntity.getEmail());
     //   dto.setMobile(savedEntity.getMobile());
        return dto;
    }

    public void deleteEmployees(long id) {
        employeeRepository.deleteById(id);

    }

    public Employee updateEmployees(long id, Employee employee) {
        Employee emp = employeeRepository.findById(id).get();
        emp.setName(employee.getName());
        emp.setEmail(employee.getEmail());
        emp.setMobile(employee.getMobile());
        Employee savedEnity=employeeRepository.save(emp);
        return emp;
    }

    Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
      //  Employee employee = new Employee();
      //  employee.setName(employeeDto.getName());
      //  employee.setEmail(employeeDto.getEmail());
      //  employee.setMobile(employeeDto.getMobile());
       return employee;
    }
      EmployeeDto mapToDto(Employee  employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
      //  EmployeeDto dto = new EmployeeDto();
      //  dto.setName(employee.getName());
      //  dto.setEmail(employee.getEmail());
      //  dto.setMobile(employee.getMobile());
          return dto;
    }
}
