package ssm.com.zhang.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssm.com.zhang.sys.domain.Msg;
import ssm.com.zhang.sys.domain.Role;
import ssm.com.zhang.sys.service.RoleService;

import java.util.List;

/**
 * 角色管理
 *
 * @author brian.zhang
 * @date 8/24/2017 14:29
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 角色主界面
     *
     * @param
     * @return java.lang.String
     * @author brian.zhang
     * @date 8/24/2017 16:08
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "sys/role";
    }

    /**
     * 查询所有角色信息
     *
     * @param []
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:32
     */
    @RequestMapping(value = "/getRoles")
    @ResponseBody
    public Msg getRoles(@RequestParam(value = "page") Integer page) {
        // 当前页
        int intPage = (page == null || page == 0) ? 1 : page;
        //每页显示条数
        int number = 10;
        PageHelper.startPage(intPage,number);
        List<Role> roles = roleService.selectAllRoles();
        PageInfo roleList = new PageInfo(roles, number);
        return Msg.success().add("roleList", roleList);
    }

    /**
     * 根据id查询角色信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:30
     */
    @RequestMapping(value = "getRoleById/{id}")
    @ResponseBody
    public Msg getRoleById(@PathVariable Integer id) {
        Role role = roleService.getRoleById(id);
        return Msg.success().add("role", role);
    }

    /**
     * 新增角色信息
     *
     * @param [role]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:28
     */
    @RequestMapping(value = "addRole")
    @ResponseBody
    public Msg addRole(Role role) {
        int rt = roleService.addRole(role);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id更新角色信息
     *
     * @param [id, role]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 15:00
     */
    @RequestMapping(value = "editRole/{id}")
    @ResponseBody
    public Msg editRoleById(@PathVariable Integer id, Role role) {
        role.setId(id);
        int rt = roleService.editRoleById(role);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id删除角色信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:12
     */
    @RequestMapping(value = "deleteRoleById/{id}")
    @ResponseBody
    public Msg deleteRoleById(@PathVariable Integer id) {
        int rt = roleService.deleteRoleById(id);
        return Msg.success().add("count", rt);
    }
}
