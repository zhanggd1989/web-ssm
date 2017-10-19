package ssm.com.zhang.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.com.zhang.sys.dao.OrganizationMapper;
import ssm.com.zhang.sys.domain.Organization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构管理
 *
 * @author brian.zhang
 * @date 8/25/2017 10:08
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;

    /**
     * 查询所有机构信息
     *
     * @param
     * @return java.util.List<ssm.com.zhang.sys.domain.Organization>
     * @author brian.zhang
     * @date 8/25/2017 10:52
     */
    @Transactional(readOnly = true)
    public List<Organization> selectAllOrganizations() {
        return organizationMapper.selectAllOrganizations();
    }

    /**
     * 根据id查询机构信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Organization
     * @author brian.zhang
     * @date 10/11/2017 16:04
     */
    public Organization getOrganizationById(Integer id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增机构信息
     *
     * @param [organization]
     * @return void
     * @author brian.zhang
     * @date 10/11/2017 16:03
     */
    public int addOrganization(Organization organization) {
        return organizationMapper.insertSelective(organization);
    }

    /**
     * 根据id更新机构信息
     *
     * @param [organization]
     * @return void
     * @author brian.zhang
     * @date 10/11/2017 15:04
     */
    public int editOrganizationById(Organization organization) {
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    /**
     * 根据id删除机构信息
     *
     * @param [id]
     * @return void
     * @author brian.zhang
     * @date 10/11/2017 15:01
     */
    public int deleteOrganizationById(Integer id) {
        return organizationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询机构树-1
     *
     * @param [organizationList]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author brian.zhang
     * @date 10/11/2017 16:06
     */
    public List<Map<String, Object>> oragnizationTree(List<Organization> organizationList) {
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        for (Organization organization : organizationList) {
            if (organization.getPid() == null) {
                map1.put("text", organization.getName());
                map1.put("nodes", organizationChildrenTree(organization.getId(), organizationList));
                list1.add(map1);
            }
        }
        return list1;
    }

    /**
     * 查询机构树-2
     *
     * @param [pid, organizationList]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author brian.zhang
     * @date 10/11/2017 16:07
     */
    public List<Map<String, Object>> organizationChildrenTree(Integer pid, List<Organization> organizationList) {
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        for (Organization organization : organizationList) {
            if (String.valueOf(pid).equals(organization.getPid())) {
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("text", organization.getName());
                if (organizationChildrenTree(organization.getId(), organizationList).size() != 0) {
                    map2.put("nodes", organizationChildrenTree(organization.getId(), organizationList));
                }
                list2.add(map2);
            }
        }
        return list2;
    }
}
