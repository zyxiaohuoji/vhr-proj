package ccs.com.vhr.controller.salary;

import ccs.com.vhr.model.Employee;
import ccs.com.vhr.model.RespBean;
import ccs.com.vhr.model.RespPageBean;
import ccs.com.vhr.model.Salary;
import ccs.com.vhr.service.EmployeeService;
import ccs.com.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SobController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPageWithSalary(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeByPageWithSalary(page, size);
    }

    @GetMapping("/salaries")
    public List<Salary> getAllSalary() {
        return salaryService.getAllSalaries();
    }

    @PutMapping("/")
    public RespBean updateEmployeeSalaryById(Integer eid, Integer sid){
        Integer result = employeeService.updateEmployeeSalaryById(eid, sid);
        if (result == 1 || result == 2) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

}
