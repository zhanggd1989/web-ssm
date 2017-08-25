package ssm.com.zhang.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ssm.com.zhang.sys.service.OrganizationService;

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

    @RequestMapping(value = "/getOrganizations", method = RequestMethod.GET)
    public void getOrganization() {
        System.out.println(organizationService.listAllOrganization());
    }
}
