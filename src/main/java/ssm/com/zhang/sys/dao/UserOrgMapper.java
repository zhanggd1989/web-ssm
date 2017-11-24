package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.User;
import ssm.com.zhang.sys.domain.UserOrg;

import java.util.List;

/**
 * 用户-机构关系管理
 *
 * @author brian.zhang
 * @date 11/24/2017 11:00
 */
@Repository
public interface UserOrgMapper {

    /**
     * 根据id查询UserOrg对象
     */
    UserOrg selectByPrimaryKey(Integer id);

    /**
     * 根据userId查询UserOrg对象
     */
    @Select("select user_id as userId, org_id as orgId from sys_user_org where user_id = #{userId}")
    UserOrg selectByUserId(Integer userId);

    /**
     * 根据OrganizationId查询User对象
     */
    @Select("select a.user_id as id, b.real_name as realName from sys_user_org a, sys_user b where a.user_id = b.id and a.org_id = #{organizationId}")
    List<User> selectUsersByOrganizationId(Integer organizationId);

    /**
     * 插入UserOrg对象
     */
    int insert(UserOrg record);

    /**
     * 选择性插入UserOrg对象
     */
    int insertSelective(UserOrg record);

    /**
     * 根据id更新UserOrg对象
     */
    int updateByPrimaryKey(UserOrg record);

    /**
     * 选择性根据id更新UserOrg对象
     */
    int updateByPrimaryKeySelective(UserOrg record);

    /**
     * 根据id删除UserOrg对象
     */
    int deleteByPrimaryKey(Integer id);
}