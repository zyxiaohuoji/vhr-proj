package ccs.com.vhr.controller.system.basic;

import ccs.com.vhr.model.Department;
import ccs.com.vhr.model.RespBean;
import ccs.com.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    DepartmentService departMentService;

    @GetMapping("/")
    public List<Department> getAllDepartments(){

        return departMentService.getAllDepartmentsByParentId(-1);
    }


    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        departMentService.addDep(dep);
        if(dep.getResult() == 1){
            return RespBean.ok("添加成功!", dep);
        }
        return RespBean.ok("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id){

        Department dep = new Department();
        dep.setId(id);

        departMentService.deleteDepById(dep);

        if (dep.getResult() == -2){
            return RespBean.error("该部门下有子部门，删除失败!");

        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败!");

        } else if(dep.getResult() == 1) {

            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}
