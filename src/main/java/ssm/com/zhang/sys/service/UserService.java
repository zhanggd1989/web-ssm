package ssm.com.zhang.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.com.zhang.sys.dao.OrganizationMapper;
import ssm.com.zhang.sys.dao.UserMapper;
import ssm.com.zhang.sys.dao.UserOrgMapper;
import ssm.com.zhang.sys.dao.UserRoleMapper;
import ssm.com.zhang.sys.domain.Organization;
import ssm.com.zhang.sys.domain.User;
import ssm.com.zhang.sys.domain.UserOrg;
import ssm.com.zhang.sys.domain.UserRole;

import java.util.List;

/**
 * 用户管理
 *
 * @author brian.zhang
 * @date 8/23/2017 14:08
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    UserOrgMapper userOrgMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * 查询所有用户信息
     *
     * @param
     * @return java.util.List<ssm.com.zhang.sys.domain.User>
     * @author brian.zhang
     * @date 8/23/2017 14:08
     */
    @Transactional(readOnly = true)
    public List<User> listAllUsers() {
        return userMapper.listAllUsers();
    }

    /**
     * 根据id查询用户信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.User
     * @author brian.zhang
     * @date 10/16/2017 14:22
     */
    public User getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        UserOrg userOrg = userOrgMapper.selectByUserId(id);
        Organization organization = organizationMapper.selectByPrimaryKey(userOrg.getOrgId());
        user.setOrgId(organization.getId());
        user.setOrgName(organization.getName());
        return user;
    }

    /**
     * 新增用户信息
     *
     * @param [user]
     * @return int
     * @author brian.zhang
     * @date 10/16/2017 14:23
     */
    public int addUser(User user) {
        userMapper.insertSelective(user);
        UserOrg userOrg = new UserOrg();
        userOrg.setUserId(user.getId());
        userOrg.setOrgId(user.getOrgId());
        return userOrgMapper.insert(userOrg);
    }

    /**
     * 根据id更新用户信息
     *
     * @param [user]
     * @return int
     * @author brian.zhang
     * @date 10/11/2017 15:04
     */
    public int editUserById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据id删除用户信息
     *
     * @param [id]
     * @return int
     * @author brian.zhang
     * @date 10/11/2017 15:01
     */
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增用户-角色关系信息
     *
     * @param [userId, roleIds]
     * @return int
     * @author brian.zhang
     * @date 11/6/2017 11:11
     */
    public int addUserAndRoleByUserId(Integer userId, String roleIds) {
        userRoleMapper.deleteByUserId(userId);
        String[] roles = roleIds.split(",");
        int rt = 0;
        for (String role : roles) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(role));
            rt = userRoleMapper.insert(userRole);
        }
        return rt;
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
}
