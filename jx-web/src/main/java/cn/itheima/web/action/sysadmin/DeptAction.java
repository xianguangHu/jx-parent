package cn.itheima.web.action.sysadmin;

import cn.itheima.domain.Dept;
import cn.itheima.service.IDeptService;

import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 跳转到新增页面
     * @return
     */
    public String tocreate() {
        //先查询出所有部门
        String hql = "from Dept where state = 1";
        List<Dept> depts = deptService.find(hql, Dept.class, null);
        put("deptList",depts);
        return "tocreate";
    }

    /**
     * 添加部门
     * @return
     */
    public String insert() {
        deptService.saveOrUpdate(dept);
        return "success";
    }


    /**
     * 跳转到更新页面
     * @return
     */
    public String toupdate() {
        //先查询要修改
        Dept dept = deptService.get(Dept.class, this.dept.getId());
        List<Dept> depts = deptService.find("from Dept where state = 1", Dept.class, null);
        //查询修改的部门有没有子部门  有就要在remove
        List<Dept> sonList = deptService.findSon(dept, new ArrayList<Dept>());
        depts.remove(dept);
        depts.removeAll(sonList);
        push(dept);
        put("deptList",depts);
        return "toupdate";
    }

    /**
     * 更新部门
     * @return
     */
    public String update() {
        deptService.saveOrUpdate(dept);
        return SUCCESS;
    }

    /**
     * 删除部门
     * @return
     */
    public String delete() {

       // deptService.deleteById(Dept.class,dept.getId());
        if (StringUtils.isNotBlank(dept.getId())) {
            String[] split = dept.getId().split(", ");
            deptService.delete(Dept.class,split);
        }
        return SUCCESS;
    }


}
