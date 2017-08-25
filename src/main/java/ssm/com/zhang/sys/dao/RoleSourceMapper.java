package ssm.com.zhang.sys.dao;

import ssm.com.zhang.sys.domain.RoleSource;

public interface RoleSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleSource record);

    int insertSelective(RoleSource record);

    RoleSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleSource record);

    int updateByPrimaryKey(RoleSource record);
}