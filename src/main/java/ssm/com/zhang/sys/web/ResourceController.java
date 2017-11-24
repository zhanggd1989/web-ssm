package ssm.com.zhang.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.com.zhang.sys.domain.Msg;
import ssm.com.zhang.sys.domain.Resource;
import ssm.com.zhang.sys.domain.RoleSource;
import ssm.com.zhang.sys.service.ResourceService;

import java.util.List;

/**
 * 资源管理
 *
 * @author brian.zhang
 * @date 8/24/2017 14:29
 */
@RequestMapping("/resource")
@Controller
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    /**
     * 资源主界面
     *
     * @param
     * @return java.lang.String
     * @author brian.zhang
     * @date 8/24/2017 16:08
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "sys/resource";
    }

    /**
     * 查询所有资源信息
     *
     * @param []
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:32
     */
    @RequestMapping(value = "/getResources")
    @ResponseBody
    public Msg getResources() {
        List<Resource> resourceList = resourceService.selectAllResources();
        return Msg.success().add("resourceList", resourceList);
    }

    /**
     * 根据id查询资源信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:30
     */
    @RequestMapping(value = "getResourceById/{id}")
    @ResponseBody
    public Msg getResourceById(@PathVariable Integer id) {
        Resource resource = resourceService.getResourceById(id);
        return Msg.success().add("resource", resource);
    }

    /**
     * 新增资源信息
     *
     * @param [resource]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:28
     */
    @RequestMapping(value = "addResource")
    @ResponseBody
    public Msg addResource(Resource resource) {
        int rt = resourceService.addResource(resource);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id更新资源信息
     *
     * @param [id, resource]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 15:00
     */
    @RequestMapping(value = "editResourceById/{id}")
    @ResponseBody
    public Msg editResourceById(@PathVariable Integer id, Resource resource) {
        resource.setId(id);
        int rt = resourceService.editResourceById(resource);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id删除资源信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:12
     */
    @RequestMapping(value = "deleteResourceById/{id}")
    @ResponseBody
    public Msg deleteResourceById(@PathVariable Integer id) {
        int rt = resourceService.deleteResourceById(id);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id查询角色资源信息
     *
     * @param [roleId]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 11/8/2017 16:22
     */
    @RequestMapping(value = "getResourcesByRoleId/{roleId}")
    @ResponseBody
    public Msg getResourcesByRoleId(@PathVariable Integer roleId) {
        List<RoleSource> roleResource = resourceService.getResourcesByRoleId(roleId);
        return Msg.success().add("roleResource", roleResource);
    }
}
