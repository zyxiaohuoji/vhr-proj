package ccs.com.vhr.mapper;

import ccs.com.vhr.model.JobLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JoblevelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    List<JobLevel> getAllJobLevels();

    int deleteJobLevelByIds(@Param("ids") Integer[] ids);
}
