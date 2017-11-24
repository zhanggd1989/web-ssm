package ssm.com.zhang.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.com.zhang.sys.dao.ResourceMapper;
import ssm.com.zhang.sys.dao.RoleSourceMapper;
import ssm.com.zhang.sys.domain.Resource;
import ssm.com.zhang.sys.domain.RoleSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源管理
 *
 * @author brian.zhang
 * @date 8/25/2017 10:08
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    RoleSourceMapper roleSourceMapper;

    /**
     * 查询所有资源信息
     *
     * @param
     * @return java.util.List<ssm.com.zhang.sys.domain.Resource>
     * @author brian.zhang
     * @date 8/25/2017 10:52
     */
    @Transactional(readOnly = true)
    public List<Resource> selectAllResources() {
        return resourceMapper.selectAllResources();
    }

    /**
     * 根据id查询资源信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Resource
     * @author brian.zhang
     * @date 10/16/2017 14:22
     */
    public Resource getResourceById(Integer id) {
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        Resource pResource = resourceMapper.selectByPrimaryKey(resource.getPid());
        resource.setpName(pResource.getName());
        return resource;
    }

    /**
     * 新增资源信息
     *
     * @param [resource]
     * @return int
     * @author brian.zhang
     * @date 10/16/2017 14:23
     */
    public int addResource(Resource resource) {
        return resourceMapper.insertSelective(resource);
    }

    /**
     * 根据id更新资源信息
     *
     * @param [resource]
     * @return int
     * @author brian.zhang
     * @date 10/11/2017 15:04
     */
    public int editResourceById(Resource resource) {
        return resourceMapper.updateByPrimaryKeySelective(resource);
    }

    /**
     * 根据id删除资源信息
     *
     * @param [id]
     * @return int
     * @author brian.zhang
     * @date 10/11/2017 15:01
     */
    public int deleteResourceById(Integer id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据id查询角色的资源信息
     *
     * @param [roleId]
     * @return java.util.List<ssm.com.zhang.sys.domain.RoleSource>
     * @author brian.zhang
     * @date 11/8/2017 16:26
     */
    public List<RoleSource> getResourcesByRoleId(Integer roleId) {
        return roleSourceMapper.selectByRoleId(roleId);
    }

//        /**
//         * 查询资源树-1
//         *
//         * @param [resourceList]
//         * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//         * @author brian.zhang
//         * @date 10/11/2017 16:06
//         */
//    public List<Map<String, Object>> resourceTree(List<Resource> resourceList) {
//        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
//        Map<String, Object> map1 = new HashMap<String, Object>();
//        for (Resource Resource : resourceList) {
//            if (Resource.getPid() == null) {
//                map1.put("text", Resource.getName());
//                map1.put("nodes", ResourceChildrenTree(Resource.getId(), resourceList));
//                list1.add(map1);
//            }
//        }
//        return list1;
//    }
//
//    /**
//     * 查询资源树-2
//     *
//     * @param [pid, resourceList]
//     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//     * @author brian.zhang
//     * @date 10/11/2017 16:07
//     */
//    public List<Map<String, Object>> ResourceChildrenTree(Integer pid, List<Resource> resourceList) {
//        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
//        for (Resource Resource : resourceList) {
//            if (String.valueOf(pid).equals(Resource.getPid())) {
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put("text", Resource.getName());
//                if (ResourceChildrenTree(Resource.getId(), resourceList).size() != 0) {
//                    map2.put("nodes", ResourceChildrenTree(Resource.getId(), resourceList));
//                }
//                list2.add(map2);
//            }
//        }
//        return list2;
//    }

}
