package ssm.com.zhang.sys.dao;

import ssm.com.zhang.sys.domain.resource;

public interface resourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(resource record);

    int insertSelective(resource record);

    resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(resource record);

    int updateByPrimaryKey(resource record);
}