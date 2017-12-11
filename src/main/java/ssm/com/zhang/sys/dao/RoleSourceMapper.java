package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.Role;
import ssm.com.zhang.sys.domain.RoleSource;

import java.util.List;

/**
  * 角色-资源关系管理
  *
  * @author brian.zhang
  * @date 11/24/2017 10:30
  */
@Repository
public interface RoleSourceMapper {

    /**
     * 根据id查询RoleSource对象
     */
    RoleSource selectByPrimaryKey(Integer id);

    /**
     * 根据roleId查询RoleSource对象
     */
    @Select("select role_id as roleId, resource_id as resourceId from sys_role_resource where role_id = #{roleId}")
    List<RoleSource> selectRoleSourceByRoleId(Integer roleId);

    /**
     * 根据resourceId查询Role对象
     */
    @Select("select a.role_id as roleId, b.name as name from sys_role_resource a, sys_role b where a.role_id = b.id and a.resource_id = #{resourceId}")
    List<Role> selectRolesByResourceId(Integer resourceId);

    /**
     * 插入RoleSource对象
     */
    int insert(RoleSource record);
    /**
     * 选择性插入RoleSource对象
     */
    int insertSelective(RoleSource record);

    /**
     * 根据id更新RoleSource对象
     */
    int updateByPrimaryKey(RoleSource record);

    /**
     * 选择性根据id更新RoleSource对象
     */
    int updateByPrimaryKeySelective(RoleSource record);

    /**
     * 根据id删除RoleSource对象
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据roleId删除RoleSource对象
     */
    @Delete("delete from sys_role_resource where role_id = #{roleId}")
    int deleteByRoleId(Integer roleId);
}