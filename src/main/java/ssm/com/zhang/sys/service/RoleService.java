package ssm.com.zhang.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.com.zhang.sys.dao.RoleMapper;
import ssm.com.zhang.sys.domain.Role;

import java.util.List;

/**
 * 角色管理
 *
 * @author brian.zhang
 * @date 8/25/2017 10:08
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    /**
     * 查询所有角色信息
     *
     * @param
     * @return java.util.List<ssm.com.zhang.sys.domain.Role>
     * @author brian.zhang
     * @date 8/25/2017 10:52
     */
    @Transactional(readOnly = true)
    public List<Role> selectAllRoles() {
        return roleMapper.selectAllRoles();
    }

    /**
     * 根据id查询角色信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Role
     * @author brian.zhang
     * @date 10/16/2017 14:22
     */
    public Role getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增角色信息
     *
     * @param [role]
     * @return int
     * @author brian.zhang
     * @date 10/16/2017 14:23
     */
    public int addRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    /**
     * 根据id更新角色信息
     *
     * @param [role]
     * @return int
     * @author brian.zhang
     * @date 10/11/2017 15:04
     */
    public int editRoleById(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 根据id删除角色信息
     *
     * @param [id]
     * @return int
     * @author brian.zhang
     * @date 10/11/2017 15:01
     */
    public int deleteRoleById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
