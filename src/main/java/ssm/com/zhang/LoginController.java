package ssm.com.zhang;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.com.zhang.sys.domain.Msg;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录管理
 *
 * @Author brian.zhang
 * @Date : 7/24/2017 4:32 PM
 */
@Controller
public class LoginController {

    /**
     * 登录主界面
     *
     * @param []
     * @return java.lang.String
     * @author brian.zhang
     * @date 12/5/2017 17:13
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录
     *
     * @param [request]
     * @return java.lang.String
     * @author brian.zhang
     * @date 12/7/2017 16:44
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        System.out.println(request.getParameter("userName") + ":" + request.getParameter("password"));
        System.out.println(request.getParameter("remember"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String msg = "";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            if (rememberMe != null && "1".equals(rememberMe)) {
                token.setRememberMe(true);
            }
            subject.login(token);
            if (subject.isAuthenticated()) {
                return "index";
            } else {
                return "login";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
        } finally {
        }
        return "login";
    }


    /**
     * 越权主界面
     *
     * @param []
     * @return java.lang.String
     * @author brian.zhang
     * @date 12/6/2017 16:16
     */
    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    /**
     * 登出
     *
     * @param []
     * @return ssm.com.zhang.sys.domain.Msg
     * @author brian.zhang
     * @date 12/7/2017 16:46
     */
    @RequestMapping("/logout")
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }
}
