package ssm.com.zhang.sys.dao;

import ssm.com.zhang.sys.domain.roleSource;

public interface roleSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(roleSource record);

    int insertSelective(roleSource record);

    roleSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(roleSource record);

    int updateByPrimaryKey(roleSource record);
}