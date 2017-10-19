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

    /**
     * 查询所有Organization对象
     */
    @Select("SELECT a.id," +
            "       a.name," +
            "       a.sequence," +
            "       a.icon," +
            "       a.type," +
            "       a.address," +
            "       a.pid," +
            "       a.status " +
            "FROM sys_organization a " +
            "WHERE a.del_flag = '0'")
    List<Organization> selectAllOrganizations();

    /**
     * 根据id查询Organization对象
     */
    Organization selectByPrimaryKey(Integer id);

    /**
     * 插入Organization对象
     */
    int insert(Organization record);

    /**
     * 选择性插入Organization对象
     */
    int insertSelective(Organization record);

    /**
     * 根据id更新Organization对象
     */
    int updateByPrimaryKey(Organization record);

    /**
     * 选择性根据id更新Organization对象
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * 根据id删除Organization对象
     */
    int deleteByPrimaryKey(Integer id);
}