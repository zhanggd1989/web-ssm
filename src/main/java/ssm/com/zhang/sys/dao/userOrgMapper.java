package ssm.com.zhang.sys.dao;

import ssm.com.zhang.sys.domain.userOrg;

public interface userOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(userOrg record);

    int insertSelective(userOrg record);

    userOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(userOrg record);

    int updateByPrimaryKey(userOrg record);
}