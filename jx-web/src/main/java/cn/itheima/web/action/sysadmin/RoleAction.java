package cn.itheima.web.action.sysadmin;

import cn.itheima.domain.Role;
import cn.itheima.service.IRoleService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



/**
 * @author huxianguang
 * @create 2017-10-29-下午9:36
 **/
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction implements ModelDriven<Role>{

    Role model = new Role();

    public Role getModel() {
        return model;
    }

    @Autowired
    private IRoleService roleService;

    private Page page = new Page();

    public void setPage(Page page) {
        this.page = page;
    }

    public String list() {
        String hql = "from Role";
        page.setUrl("roleAction_list");
        Page page = roleService.findPage(hql, this.page, Role.class, null);
        push(page);
        return "list";
    }

    public String toview() {
        Role role = roleService.get(Role.class, model.getId());
        push(role);
        return "toview";
    }

    /**
     * 跳转到新增页面
     * @return
     */
    public String tocreate() {
        return "tocreate";
    }

    /**
     * 添加角色
     * @return
     */
    public String insert() {
        roleService.saveOrUpdate(model);
        return "success";
    }

    public String toupdate() {
        Role role = roleService.get(Role.class, model.getId());
        push(role);
        return "toupdate";
    }

    public String update() {
        roleService.saveOrUpdate(model);
        return SUCCESS;
    }

    public String delete() {
        roleService.deleteById(Role.class,model.getId());
        return SUCCESS;
    }
}
