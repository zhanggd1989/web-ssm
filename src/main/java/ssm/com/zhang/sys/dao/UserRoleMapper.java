package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.User;
import ssm.com.zhang.sys.domain.UserRole;

import java.util.List;

/**
  * 用户-角色关系管理
  *
  * @author brian.zhang
  * @date 11/24/2017 10:40
  */
@Repository
public interface UserRoleMapper {

    /**
     * 根据id查询UserRole对象
     */
    UserRole selectByPrimaryKey(Integer id);

    /**
     * 根据userId查询UserRole对象
     */
    @Select("select user_id as userId, role_id as roleId from sys_user_role where user_id = #{userId}")
    List<UserRole> selectByUserId(Integer userId);

    /**
     * 根据roleId查询UserRole对象
     */
    @Select("select a.user_id as userId, b.real_name as realName from sys_user_role a, sys_user b where a.user_id = b.id and a.role_id = #{roleId}")
    List<User> selectUsersByRoleId(Integer roleId);

    /**
     * 插入UserRole对象
     */
    int insert(UserRole record);

    /**
     * 选择性插入UserRole对象
     */
    int insertSelective(UserRole record);

    /**
     * 根据id更新UserRole对象
     */
    int updateByPrimaryKey(UserRole record);

    /**
     * 选择性根据id更新UserRole对象
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * 根据id删除UserRole对象
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 选择性根据id删除UserRole对象
     */
    @Delete("delete from sys_user_role where user_id = #{userId}")
    int deleteByUserId(Integer userId);
}