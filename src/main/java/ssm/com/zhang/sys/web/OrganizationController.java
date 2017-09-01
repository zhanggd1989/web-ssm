package ssm.com.zhang.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * @param
     * @return
     * @author brian.zhang
     * @date 8/29/2017 16:47
     */
    @RequestMapping(value = "/getOrganizations")
    @ResponseBody
    public Msg getOrganizations() {
        List<Organization> organizationList = organizationService.listAllOrganizations();
        return Msg.success().add("organizationPage", organizationList);
    }
}
