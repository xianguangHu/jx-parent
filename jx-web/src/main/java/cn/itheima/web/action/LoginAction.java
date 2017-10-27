package cn.itheima.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author huxianguang
 * @create 2017-10-26-下午7:52
 **/
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction{


    public String login() {
        return SUCCESS;
    }
}
