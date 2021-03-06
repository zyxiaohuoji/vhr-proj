package ccs.com.vhr.service;

import ccs.com.vhr.mapper.EmployeeMapper;
import ccs.com.vhr.model.Employee;
import ccs.com.vhr.model.RespPageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {

        if (page != null && size != null ){
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotal(employee, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public int addEmp(Employee employee) {
//      根据合同起始日期自动计算年限
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();

        Double months = (Double.parseDouble(yearFormat.format(endContract)) -
                Double.parseDouble(yearFormat.format(beginContract))) * 12 +
                Double.parseDouble(monthFormat.format(endContract)) -
                Double.parseDouble(monthFormat.format(beginContract));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(months/12)));
        if(!"离职".equals(employee.getWorkState())) {
            employee.setWorkState("在职");
        }
        int result = employeeMapper.insertSelective(employee);

        if(result == 1) {
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            LOGGER.info(emp.toString());
            rabbitTemplate.convertAndSend("vhr.mail.queue.welcome", emp);
        }
        return result;
    }

    public Integer maxWorkId() {
        return employeeMapper.maxWorkId();
    }

    public int deleteEmpById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public int updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Integer addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }

    public RespPageBean getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalary(page, size);

        RespPageBean bean = new RespPageBean();
        bean.setData(list);
        bean.setTotal(employeeMapper.getTotal(null, null));
        return bean;
    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }
}
