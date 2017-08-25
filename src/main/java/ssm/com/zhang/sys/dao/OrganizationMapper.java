package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.Organization;

import java.util.List;

/**
 * 机构管理
 *
 * @author brian.zhang
 * @date 8/25/2017 10:53
 */
@Repository
public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    /**
     * 获取所有Organization对象
     *
     * @param
     * @return java.util.List<ssm.com.zhang.sys.domain.Organization>
     * @author brian.zhang
     * @date 8/25/2017 10:52
     */
    @Select("SELECT a.id,\n" +
            "       a.name,\n" +
            "       a.sequence,\n" +
            "       a.icon,\n" +
            "       a.type,\n" +
            "       a.address,\n" +
            "       a.pid,\n" +
            "       a.status\n" +
            "FROM sys_organization a\n" +
            "WHERE a.del_flag = '0'")
    List<Organization> listAllOrganization();
}