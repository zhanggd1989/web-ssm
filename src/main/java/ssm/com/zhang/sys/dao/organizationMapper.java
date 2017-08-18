package ssm.com.zhang.sys.dao;

import ssm.com.zhang.sys.domain.organization;

public interface organizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(organization record);

    int insertSelective(organization record);

    organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(organization record);

    int updateByPrimaryKey(organization record);
}