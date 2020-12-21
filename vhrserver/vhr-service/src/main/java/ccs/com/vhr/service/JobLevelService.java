package ccs.com.vhr.service;

import ccs.com.vhr.mapper.JoblevelMapper;
import ccs.com.vhr.model.JobLevel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelService {
    @Autowired
    JoblevelMapper joblevelMapper;

    public List<JobLevel> getAllJobLevels() {
        return joblevelMapper.getAllJobLevels();
    }

    public int addJobLevel(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        jobLevel.setEnabled(true);
        return joblevelMapper.insert(jobLevel);
    }

    public int updateJobLevel(JobLevel jobLevel) {
        return joblevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public int deleteJobLevelById(Integer id) {
        return joblevelMapper.deleteByPrimaryKey(id);
    }

    public int deleteJobLevelByIds(Integer[] ids) {
        return joblevelMapper.deleteJobLevelByIds(ids);
    }
}
