package ssm.com.zhang.sys.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.com.zhang.sys.domain.Resource;

import java.util.List;

/**
 * 资源管理
 *
 * @author brian.zhang
 * @date 10/16/2017 14:01
 */
@Repository
public interface ResourceMapper {

    /**
     * 查询所有Resource对象
     */
    @Select("SELECT a.id," +
            "       a.name," +
            "       a.sequence," +
            "       a.url," +
            "       a.icon," +
            "       a.type," +
            "       a.description," +
            "       a.pid," +
            "       a.status " +
            "FROM sys_resource a " +
            "WHERE a.del_flag = '0'")
    List<Resource> selectAllResources();

    /**
     * 根据id查询Resource对象
     */
    Resource selectByPrimaryKey(Integer id);

    /**
     * 插入Resource对象
     */
    int insert(Resource record);

    /**
     * 选择性插入Resource对象
     */
    int insertSelective(Resource record);

    /**
     * 根据id更新Resource对象
     */
    int updateByPrimaryKey(Resource record);

    /**
     * 选择性根据id更新Resource对象
     */
    int updateByPrimaryKeySelective(Resource record);

    /**
     * 根据id删除Resource对象
     */
    int deleteByPrimaryKey(Integer id);
}