package cn.itheima.web.action.sysadmin;

import cn.itheima.domain.Dept;
import cn.itheima.service.IDeptService;

import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huxianguang
 * @create 2017-10-26-下午8:37
 **/
public class DeptAction extends BaseAction implements ModelDriven<Dept> {

    private Dept dept = new Dept();

    public Dept getModel() {
        return dept;
    }

    @Autowired
    private IDeptService deptService;

    private Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String list() {
        String hql = "from Dept";
        page.setUrl("deptAction_list");
        deptService.findPage(hql,page,Dept.class,null);
        push(page);
        return "list";
    }

    public String toview() {
        Dept dept = deptService.get(Dept.class, this.dept.getId());
        push(dept);
        return "toview";
    }
}
