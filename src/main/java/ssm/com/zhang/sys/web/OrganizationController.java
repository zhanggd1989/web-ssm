package ssm.com.zhang.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.com.zhang.sys.domain.Msg;
import ssm.com.zhang.sys.domain.Organization;
import ssm.com.zhang.sys.service.OrganizationService;

import java.util.List;

/**
 * 机构管理
 *
 * @author brian.zhang
 * @date 8/24/2017 14:29
 */
@RequestMapping("/organization")
@Controller
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    /**
     * 机构主界面
     *
     * @param
     * @return java.lang.String
     * @author brian.zhang
     * @date 8/24/2017 16:08
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "sys/organization";
    }

    /**
     * 查询所有机构信息
     *
     * @param []
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:32
     */
    @RequestMapping(value = "/getOrganizations")
    @ResponseBody
    public Msg getOrganizations() {
        List<Organization> organizationList = organizationService.selectAllOrganizations();
        return Msg.success().add("organizationList", organizationList);
    }

    /**
     * 根据id查询机构信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:30
     */
    @RequestMapping(value = "getOrganizationById/{id}")
    @ResponseBody
    public Msg getOrganizationById(@PathVariable Integer id) {
        Organization organization = organizationService.getOrganizationById(id);
        return Msg.success().add("organization", organization);
    }

    /**
     * 新增机构信息
     *
     * @param [organization]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:28
     */
    @RequestMapping(value = "addOrganization")
    @ResponseBody
    public Msg addOrganization(Organization organization) {
        int rt = organizationService.addOrganization(organization);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id更新机构信息
     *
     * @param [id, organization]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 15:00
     */
    @RequestMapping(value = "editOrganizationById/{id}")
    @ResponseBody
    public Msg editOrganizationById(@PathVariable Integer id, Organization organization) {
        organization.setId(id);
        int rt = organizationService.editOrganizationById(organization);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id删除机构信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:12
     */
    @RequestMapping(value = "deleteOrganizationById/{id}")
    @ResponseBody
    public Msg deleteOrganizationById(@PathVariable Integer id) {
        int rt = organizationService.deleteOrganizationById(id);
        return Msg.success().add("count", rt);
    }
}
