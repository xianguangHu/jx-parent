package cn.itheima.web.action.sysadmin;

import cn.itheima.domain.Module;
import cn.itheima.service.IModuleService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.jws.WebParam;

/**
 * @author huxianguang
 * @create 2017-10-30-上午9:37
 **/
@Controller
@Scope("prototype")
public class ModuleAction extends BaseAction implements ModelDriven<Module>{

    Module model = new Module();

    public Module getModel() {
        return model;
    }

    @Autowired
    private IModuleService moduleService;

    private Page page = new Page();

    public void setPage(Page page) {
        this.page = page;
    }

    public String list() {
        String hql = "from Module";
        page.setUrl("moduleAction_list");
        Page page = moduleService.findPage(hql, this.page, Module.class, null);
        push(page);
        return "list";
    }

    public String toview() {
        Module module = moduleService.get(Module.class, model.getId());
        push(module);
        return "toview";
    }


    public String tocreate() {
        return "tocreate";
    }


    public String insert() {
        moduleService.saveOrUpdate(model);
        return "success";
    }

    public String toupdate() {
        Module module = moduleService.get(Module.class, model.getId());
        push(module);
        return "toupdate";
    }

    public String update() {
        moduleService.saveOrUpdate(model);
        return SUCCESS;
    }

    public String delete() {
        moduleService.deleteById(Module.class,model.getId());
        return SUCCESS;
    }
}
