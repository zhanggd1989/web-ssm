package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.User;

import java.util.List;

/**
 * 用户管理
 *
 * @author brian.zhang
 * @date 10/20/2017 10:14
 */
@Repository
public interface UserMapper {

    /**
     * 获取所有User对象
     */
    @Select("SELECT a.id,\n" +
            "       a.login_name AS loginName,\n" +
            "       a.password,\n" +
            "       a.real_name AS realName,\n" +
            "       a.gender,\n" +
            "       a.email,\n" +
            "       a.phone,\n" +
            "       a.type,\n" +
            "       a.description,\n" +
            "       a.status,\n" +
            "       c.id AS orgId,\n" +
            "       c.name AS orgName,\n" +
            "       a.del_flag\n" +
            "FROM sys_user a\n" +
            "  LEFT JOIN sys_user_org b ON a.id = b.user_id\n" +
            "  LEFT JOIN sys_organization c ON b.org_id = c.id AND c.del_flag = '0'\n" +
            "  LEFT JOIN sys_organization d ON c.pid = d.id AND d.del_flag = '0'\n" +
            "WHERE a.del_flag = '0'\n" +
            "ORDER BY a.id")
    List<User> listAllUsers();

    /**
     * 根据id查询User对象
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 插入User对象
     */
    int insert(User record);

    /**
     * 选择性插入User对象
     */
    int insertSelective(User record);

    /**
     * 根据id更新User对象
     */
    int updateByPrimaryKey(User record);

    /**
     * 选择性根据id更新User对象
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据id删除User对象
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据登录名查询User对象
     */
    @Select("SELECT id, login_name as loginName, real_name as realName, password " +
            "FROM sys_user " +
            "WHERE login_name = #{loginName}")
    User selectByUserLoginName(String loginName);
}