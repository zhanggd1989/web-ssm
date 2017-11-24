package ssm.com.zhang.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssm.com.zhang.sys.domain.Msg;
import ssm.com.zhang.sys.domain.User;
import ssm.com.zhang.sys.domain.UserRole;
import ssm.com.zhang.sys.service.UserService;

import java.util.List;

/**
 * 用户管理
 *
 * @author brian.zhang
 * @date 8/23/2017 11:15
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户主界面
     *
     * @param
     * @return java.lang.String
     * @author brian.zhang
     * @date 8/23/2017 11:22
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "sys/user";
    }

    /**
     * 查询所有用户信息
     *
     * @param page
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 8/23/2017 11:22
     */
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public Msg getUsers(@RequestParam(value = "page") Integer page) {
        //当前页
        int intPage = (page == null || page == 0) ? 1 : page;
        //每页显示条数
        int number = 10;
        PageHelper.startPage(intPage, number);
        List<User> userList = userService.listAllUsers();
        PageInfo userPage = new PageInfo(userList, number);
        return Msg.success().add("userPage", userPage);
    }

    /**
     * 根据id查询用户信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:30se
     */
    @RequestMapping(value = "getUserById/{id}")
    @ResponseBody
    public Msg getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return Msg.success().add("user", user);
    }

    /**
     * 新增用户信息
     *
     * @param [user]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:28
     */
    @RequestMapping(value = "addUser")
    @ResponseBody
    public Msg addUser(User user) {
        int rt = userService.addUser(user);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id更新用户信息
     *
     * @param [id, user]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 15:00
     */
    @RequestMapping(value = "editUserById/{id}")
    @ResponseBody
    public Msg editUserById(@PathVariable Integer id, User user) {
        user.setId(id);
        int rt = userService.editUserById(user);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id删除用户信息
     *
     * @param [id]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 10/11/2017 16:12
     */
    @RequestMapping(value = "deleteUserById/{id}")
    @ResponseBody
    public Msg deleteUserById(@PathVariable Integer id) {
        int rt = userService.deleteUserById(id);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据id新增用户-角色关系信息
     *
     * @param [userId, roleIds]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 11/6/2017 11:09
     */
    @RequestMapping(value = "addUserAndRolesByUserId/{userId}")
    @ResponseBody
    public Msg addUserAndRolesByUserId(@PathVariable Integer userId, @RequestParam(value = "roleIds") String roleIds) {
        int rt = userService.addUserAndRolesByUserId(userId, roleIds);
        return Msg.success().add("count", rt);
    }

    /**
     * 根据角色id查询用户信息
     *
     * @param [roleId]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 11/23/2017 09:01
     */
    @RequestMapping(value = "getUsersByRoleId/{roleId}")
    @ResponseBody
    public Msg getUsersByRoleId(@PathVariable Integer roleId) {
        List<User> userList = userService.getUsersByRoleId(roleId);
        return Msg.success().add("userList", userList);
    }

    /**
     * 根据组织id查询用户信息
     *
     * @param [organizationId]
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 11/24/2017 10:57
     */
    @RequestMapping(value = "getUsersByOrganizationId/{organizationId}")
    @ResponseBody
    public Msg getUsersByOrganizationId(@PathVariable Integer organizationId) {
        List<User> userList = userService.getUsersByOrganizationId(organizationId);
        return Msg.success().add("userList", userList);
    }
}
