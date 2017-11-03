package cn.itheima.web.action;

import cn.itheima.common.SysContant;
import cn.itheima.domain.User;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author huxianguang
 * @create 2017-10-26-下午7:52
 **/
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {

        //获取Subeject
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return SUCCESS;
        }

        if (StringUtils.isBlank(username)) {
            return "login";
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //启用安全管理器
        subject.login(token);
        //登陆成功
        User user = (User) subject.getPrincipal();
        session.put(SysContant.CURRENT_USER, user);
        return SUCCESS;
    }

        public String logout() {
            SecurityUtils.getSubject().logout();
            return "logout";
        }
}
