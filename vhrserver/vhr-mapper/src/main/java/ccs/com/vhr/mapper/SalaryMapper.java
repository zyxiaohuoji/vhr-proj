package ccs.com.vhr.mapper;

import ccs.com.vhr.model.Salary;

public interface SalaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salary
     *
     * @mbggenerated Thu Nov 05 10:03:24 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salary
     *
     * @mbggenerated Thu Nov 05 10:03:24 CST 2020
     */
    int insert(Salary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salary
     *
     * @mbggenerated Thu Nov 05 10:03:24 CST 2020
     */
    int insertSelective(Salary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salary
     *
     * @mbggenerated Thu Nov 05 10:03:24 CST 2020
     */
    Salary selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salary
     *
     * @mbggenerated Thu Nov 05 10:03:24 CST 2020
     */
    int updateByPrimaryKeySelective(Salary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salary
     *
     * @mbggenerated Thu Nov 05 10:03:24 CST 2020
     */
    int updateByPrimaryKey(Salary record);
}