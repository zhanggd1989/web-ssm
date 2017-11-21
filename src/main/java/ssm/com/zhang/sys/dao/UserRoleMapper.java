package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.UserRole;

import java.util.List;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    @Select("select user_id as userId, role_id as roleId from sys_user_role where user_id = #{userId}")
    List<UserRole> selectByUserId(Integer userId);

    @Delete("delete from sys_user_role where user_id = #{userId}")
    int deleteByUserId(Integer userId);
}