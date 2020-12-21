package ccs.com.vhr.service;

import ccs.com.vhr.mapper.MenuMapper;
import ccs.com.vhr.mapper.MenuRoleMapper;
import ccs.com.vhr.model.Hr;
import ccs.com.vhr.model.Menu;
import ccs.com.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenuByHrId(){

        return menuMapper.getMenuByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

//    @Cacheable
    public List<Menu> getAllMenusWithRole(){

        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        if (null == mids || mids.length == 0){
            return true;
        } else {
            Integer result = menuMapper.insertRecord(rid, mids);
            return result == mids.length;
        }
    }
}
