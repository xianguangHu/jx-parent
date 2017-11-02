package cn.itheima.web.action.sysadmin;

import cn.itheima.domain.Module;
import cn.itheima.domain.Role;
import cn.itheima.service.IModuleService;
import cn.itheima.service.IRoleService;
import cn.itheima.util.Page;
import cn.itheima.vo.ZTreeData;
import cn.itheima.web.action.BaseAction;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @Autowired
    private IModuleService moduleService;

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

    public String tomodule() {
        Role role = roleService.get(Role.class, model.getId());
        push(role);
        return "tomodule";
    }

    public String ajaxModule() throws IOException {
        Role role = roleService.get(Role.class, model.getId());
        Set<Module> moduleSet = role.getModules();
        String hql = "from Module where state = 1";
        List<Module> modules = moduleService.find(hql, Module.class, null);
        List<ZTreeData> list = new ArrayList<ZTreeData>();

        for(Module m:modules){
            String id = m.getId();
            String pId = m.getParent()==null?"0":m.getParent().getId();
            String name = m.getName();
            boolean checked = moduleSet.contains(m);

            ZTreeData data = new ZTreeData(id, pId, name, checked, true);
            list.add(data);
        }
        //转json
        String str = JSON.toJSONString(list);

        //传送到前台
        HttpServletResponse response = ServletActionContext.getResponse();
        //设置参数信息
        response.setCharacterEncoding("utf-8");
        //写出去
        response.getWriter().write(str);
        return NONE;
    }

    private String moduleIds;

    public String getModuleIds() {
        return moduleIds;
    }
    public void setModuleIds(String moduleIds) {
        this.moduleIds = moduleIds;
    }

    public String module() throws Exception {
        //1 获取角色信息
        Role role = roleService.get(Role.class, model.getId());
        //2 准备模块列表信息
        Set<Module> modules = new HashSet<Module>();
        String[] ids = moduleIds.split(",");
        for(String id:ids){
            Module module = moduleService.get(Module.class, id);
            modules.add(module);
        }
        //3 整体替换
        role.setModules(modules);
        //提交修改
        roleService.saveOrUpdate(role);

        return SUCCESS;
    }
}
