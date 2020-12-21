package ccs.com.vhr.service;

import ccs.com.vhr.mapper.PositionMapper;
import ccs.com.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;
    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public int addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insert(position);
    }

    public int updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public int deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public int deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}
