package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.Role;

import java.util.List;

/**
 * 角色管理
 *
 * @author brian.zhang
 * @date 10/16/2017 14:01
 */
@Repository
public interface RoleMapper {

    /**
     * 查询所有Role对象
     */
    @Select("SELECT a.id," +
            "       a.name," +
            "       a.sequence," +
            "       a.description," +
            "       a.status " +
            "FROM sys_role a " +
            "WHERE a.del_flag = '0'")
    List<Role> selectAllRoles();

    /**
     * 根据id查询Role对象
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 插入Role对象
     */
    int insert(Role record);

    /**
     * 选择性插入Role对象
     */
    int insertSelective(Role record);

    /**
     * 根据id更新Role对象
     */
    int updateByPrimaryKey(Role record);

    /**
     * 选择性根据id更新Role对象
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 根据id删除Role对象
     */
    int deleteByPrimaryKey(Integer id);
}