package ssm.com.zhang.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.com.zhang.sys.dao.RoleMapper;
import ssm.com.zhang.sys.dao.RoleSourceMapper;
import ssm.com.zhang.sys.dao.UserRoleMapper;
import ssm.com.zhang.sys.domain.Role;
import ssm.com.zhang.sys.domain.RoleSource;
import ssm.com.zhang.sys.domain.UserRole;

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

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleSourceMapper roleSourceMapper;

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
        roleSourceMapper.deleteByRoleId(id);
        return roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id查询用户的角色信息
     *
     * @param [userId]
     * @return int
     * @author brian.zhang
     * @date 11/7/2017 14:07
     */
    public List<UserRole> getRolesByUserId(Integer userId) {
        return userRoleMapper.selectByUserId(userId);
    }

    /**
     * 新增角色-资源关心信息
     *
     * @param [roleId, resouceIds]
     * @return int
     * @author brian.zhang
     * @date 11/22/2017 17:21
     */
    public int addRoleAndResourcesByRoleId(Integer roleId, String resourceIds) {
        roleSourceMapper.deleteByRoleId(roleId);
        int rt = 0;
        if(resourceIds != null && resourceIds.length() > 0) {
            String[] resources = resourceIds.split(",");
            for (String resource : resources) {
                RoleSource roleSource = new RoleSource();
                roleSource.setRoleId(roleId);
                roleSource.setResourceId(Integer.parseInt(resource));
                rt = roleSourceMapper.insert(roleSource);
            }
        }
        return rt;
    }

    /**
     * 根据角色id查询资源信息
     *
     * @param [resourceId]
     * @return java.util.List<ssm.com.zhang.sys.domain.Role>
     * @author brian.zhang
     * @date 11/24/2017 08:42
     */
    public List<Role> getRolesByResourceId(Integer resourceId) {
        return roleSourceMapper.selectRolesByResourceId(resourceId);
    }
}
