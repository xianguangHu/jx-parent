package cn.itheima.web.action.sysadmin;

import cn.itheima.domain.User;
import cn.itheima.service.IUserService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author huxianguang
 * @create 2017-10-28-下午3:45
 **/
@Controller
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<User> {

    private User model = new User();

    public User getModel() {
        return model;
    }

    @Autowired
    private IUserService userService;


    private Page page = new Page();

    public void setPage(Page page) {
        this.page = page;
    }

    public String list() {
        String hql = "from User";
        page.setUrl("userAction_list");
        Page page = userService.findPage(hql, this.page, User.class, null);
        push(page);
        return "list";
    }
}
