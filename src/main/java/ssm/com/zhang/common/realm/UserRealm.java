package ssm.com.zhang.common.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import ssm.com.zhang.sys.domain.Resource;
import ssm.com.zhang.sys.domain.Role;
import ssm.com.zhang.sys.domain.User;
import ssm.com.zhang.sys.service.ResourceService;
import ssm.com.zhang.sys.service.RoleService;
import ssm.com.zhang.sys.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 授权认证
 *
 * @author brian.zhang
 * @date 12/5/2017 11:10
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ResourceService resourceService;


    /**
     * 授权
     *
     * @param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author brian.zhang
     * @date 12/5/2017 13:53
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录用户的相关信息
        String loginName = (String)principalCollection.getPrimaryPrincipal();
        // 角色列表
        List<String> roleList = new ArrayList<String>();
        // 权限列表
        List<String> permissionList = new ArrayList<String>();

        // 查询当前登录用户的角色列表
        List<Role> roles = roleService.getRolesByUserLoginName(loginName);
        for (Role role : roles) {
            roleList.add(role.getName());
            Integer roleId = role.getId();
            List<Resource> resources = resourceService.getResourcesByRoleId(roleId);
            for (Resource resource : resources) {
                permissionList.add(resource.getName());
            }
        }
        // 为当前用户设置角色和权限
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        simpleAuthorInfo.addRoles(roleList);
        simpleAuthorInfo.addStringPermissions(permissionList);
        return simpleAuthorInfo;
    }

    /**
     * 认证
     *
     * @param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author brian.zhang
     * @date 12/5/2017 13:54
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByUserLoginName(token.getUsername());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userInfo", user);
        if (null != user) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
            simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getLoginName()));
            return simpleAuthenticationInfo;
        } else {
            return null;
        }
    }
}
