package ccs.com.vhr.mapper;

import ccs.com.vhr.model.Salary;

import java.util.List;

public interface SalaryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    List<Salary> getAllSalaries();

}