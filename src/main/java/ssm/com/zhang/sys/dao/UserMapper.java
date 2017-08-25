package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.User;

import java.util.List;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
      * 获取所有User对象
      *
      * @author brian.zhang
      * @param
      * @return java.util.List<ssm.com.zhang.sys.domain.User>
      * @date 8/23/2017 14:09
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
            "       a.del_flag\n" +
            "FROM sys_user a\n" +
            "  LEFT JOIN sys_user_org b ON a.id = b.user_id\n" +
            "  LEFT JOIN sys_organization c ON b.org_id = c.id AND c.del_flag = '0'\n" +
            "  LEFT JOIN sys_organization d ON c.pid = d.id AND d.del_flag = '0'\n" +
            "WHERE a.del_flag = '0'")
    List<User> listAllUsers();
}