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
     * 获取所有Organization对象
     *
     * @author brian.zhang
     * @param
     * @return java.util.List<ssm.com.zhang.sys.domain.Organization>
     * @date 8/25/2017 10:52
     */
    @Transactional(readOnly = true)
    public List<Object> listAllOrganization() {

        List<Organization> organizationList = organizationMapper.listAllOrganization();
        return OragnizationTree(organizationList);
    }

    public List<Object> OragnizationTree(List<Organization> organizationList) {
        List<Object> list1 = new ArrayList<Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        for(Organization organization : organizationList) {
            if(organization.getPid() == null) {
                System.out.println("bbbb" + organization.getName());
                map1.put("text", organization.getName());
                map1.put("nodes", organization.getId());
            }
        }
        list1.add(map1);
        return list1;
    }

    public List<Object> OrganizationChildrenTree(Integer pid, List<Organization> organizationList) {
        List<Object> list2 = new ArrayList<Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        for(Organization organization : organizationList) {
            if(String.valueOf(pid).equals(organization.getPid())) {
                map2.put("text",organization.getName());
                map2.put("nodes",OrganizationChildrenTree(organization.getId(), organizationList));
            }

        }
        list2.add(map2);
        return list2;
    }
}
