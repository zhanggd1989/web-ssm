package ssm.com.zhang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author brian.zhang
 * @Date : 7/24/2017 4:32 PM
 */
@Controller
public class LoginController {
    //打开登陆界面
    @RequestMapping("/")
    public String pageIndex() {
        System.out.println("");
        return "login";
    }

    @RequestMapping("/index")
    public String login(HttpServletRequest request) {
        System.out.println(request.getParameter("userName") + ":" + request.getParameter("password"));

        return "index";
    }
}
