package ssm.com.zhang.sys.service;

import org.apache.shiro.crypto.hash.Md5Hash;
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
    @Transactional(readOnly = true)
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
        user.setPassword(new Md5Hash(user.getPassword(), user.getLoginName(), 1).toHex());
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
        userRoleMapper.deleteByUserId(id);
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
    public int addUserAndRolesByUserId(Integer userId, String roleIds) {
        userRoleMapper.deleteByUserId(userId);
        int rt = 0;
        if (roleIds != null && roleIds.length() > 0) {
            String[] roles = roleIds.split(",");
            for (String role : roles) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(role));
                rt += userRoleMapper.insert(userRole);
            }
        }
        return rt;
    }

    /**
     * 根据角色id查询用户信息
     *
     * @param [roleId]
     * @return java.util.List<ssm.com.zhang.sys.domain.User>
     * @author brian.zhang
     * @date 11/23/2017 09:04
     */
    @Transactional(readOnly = true)
    public List<User> getUsersByRoleId(Integer roleId) {
        return userRoleMapper.selectUsersByRoleId(roleId);
    }

    /**
     * 根据组织id查询用户信息
     * @param [organizationId]
     * @return java.util.List<ssm.com.zhang.sys.domain.User>
     * @author brian.zhang
     * @date 11/24/2017 10:58
     */
    public List<User> getUsersByOrganizationId(Integer organizationId) {
        return userOrgMapper.selectUsersByOrganizationId(organizationId);
    }


    /**
     * 根据登录名查询用户信息
     *
     * @param [loginName]
     * @return void
     * @author brian.zhang
     * @date 12/5/2017 15:01
     */
    public User getUserByUserLoginName(String loginName) {
        return userMapper.selectByUserLoginName(loginName);
    }
}
