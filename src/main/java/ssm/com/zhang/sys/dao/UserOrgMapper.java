package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.UserOrg;

@Repository
public interface UserOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOrg record);

    int insertSelective(UserOrg record);

    UserOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrg record);

    int updateByPrimaryKey(UserOrg record);

    @Select("select user_id as userId, org_id as orgId from sys_user_org where user_id = #{userId}")
    UserOrg selectByUserId(Integer userId);

}