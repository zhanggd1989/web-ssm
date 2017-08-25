package ssm.com.zhang.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.com.zhang.sys.domain.Msg;
import ssm.com.zhang.sys.domain.User;
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
      * @author brian.zhang
      * @param
      * @return java.lang.String
      * @date 8/23/2017 11:22
      */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "sys/user";
    }

    /**
      * 查询所有用户信息
      *
      * @author brian.zhang
      * @param page
      * @return ssm.com.zhang.sys.domain.Msg
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
}
