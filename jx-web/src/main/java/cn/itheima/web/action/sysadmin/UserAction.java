package cn.itheima.web.action.sysadmin;

import cn.itheima.domain.Dept;
import cn.itheima.domain.Role;
import cn.itheima.domain.User;
import cn.itheima.service.IDeptService;
import cn.itheima.service.IRoleService;
import cn.itheima.service.IUserService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IRoleService roleService;


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


    public String toview() {
        User user = userService.get(User.class, model.getId());
        push(user);
        return "toview";
    }

    /**
     * 跳转到新增页面
     * @return
     */
    public String tocreate() {
        String hql = "from Dept where state = 1";
        List<Dept> depts = deptService.find(hql, Dept.class, null);
        String uhql = "from User where state = 1";
        List<User> users = userService.find(uhql, User.class, null);
        put("userList",users);
        put("deptList",depts);
        return "tocreate";
    }

    /**
     * 添加用户
     * @return
     */
    public String insert() {
        userService.saveOrUpdate(model);
        return "success";
    }


    /**
     * 跳转到更新页面
     * @return
     */
    public String toupdate() {
        User user = userService.get(User.class, model.getId());
        String hql = "from Dept where state = 1";
        List<Dept> depts = deptService.find(hql, Dept.class, null);
        put("deptList",depts);
        push(user);
        return "toupdate";
    }

    /**
     * 更新用户
     * @return
     */
    public String update() {
        userService.saveOrUpdate(model);
        return SUCCESS;
    }

    /**
     * 删除用户
     * @return
     */
    public String delete() {
        userService.deleteById(User.class,model.getId());
        return SUCCESS;
    }

    public String ajaxUser() throws IOException {
        String hql = "from User where dept.id = ?";
        List<User> users = userService.find(hql, User.class, new Object[]{model.getDept().getId()});
        String jsonString = JSON.toJSONString(users);

        //传送到前台
        HttpServletResponse response = ServletActionContext.getResponse();
        //设置参数信息
        response.setCharacterEncoding("utf-8");
        //写出去
        response.getWriter().write(jsonString);
        return NONE;
    }

    public String torole() {
        User user = userService.get(User.class, model.getId());
        String hql = "from Role";
        List<Role> roles = roleService.find(hql, Role.class, null);
        push(user);
        put("roleList",roles);
        put("roles",user.getRoles());
        return "torole";
    }

    private String[] roleIds;

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

//    public String[] getRoleIds() {
//        return roleIds;
//    }

    public String role() {
        User user = userService.get(User.class, model.getId());
        Set<Role> roles = new HashSet<Role>();
        if (roleIds != null && roleIds.length >0) {
            for (String roleId : roleIds) {
                Role role = roleService.get(Role.class, roleId);
                roles.add(role);
            }
        }
        user.setRoles(roles);
        //多对多关系 自定维护中间表 但是要求双方都是持久化状态
        userService.saveOrUpdate(user);
        return SUCCESS;
    }
}
