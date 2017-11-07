package cn.itheima.web.action.cargo;

import cn.itheima.common.SysContant;
import cn.itheima.domain.Contract;
import cn.itheima.domain.User;
import cn.itheima.service.IContractService;
import cn.itheima.service.IDeptService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author huxianguang
 * @create 2017-11-03-下午8:04
 **/
@Controller
@Scope("prototype")
public class ContractAction extends BaseAction implements ModelDriven<Contract> {

    Contract model = new Contract();

    public Contract getModel() {
        return model;
    }


    @Autowired
    private IContractService contractService;

    @Autowired
    private IDeptService deptService;

    private Page page = new Page();

    public void setPage(Page page) {
        this.page = page;
    }

    public String list() {
        String hql="from Contract where 1 = 1 ";
        User user = (User) getSession().get(SysContant.CURRENT_USER);
        Integer degree = user.getUserInfo().getDegree();
        if (degree == 4) {
            //普通用户 只查询自己
            hql += "and createBy = '"+user.getId()+"'";
        } else if (degree == 3) {
            //部门经理 可以查询本部门
            hql += "and createDept = '"+user.getDept().getId()+"'";
        } else if (degree == 2) {
            //大部门经理
            //递归查询所有子部门
            List<String> deptIds = deptService.childrenDeptById(new ArrayList<String>(), user.getDept().getId());
            String in ="";
            for (String deptId : deptIds) {
                in += deptId + "','";
            }
            in = in.substring(0,in.length() - 3);
            System.out.println(in);
            hql += "and createDept in ( '"+in+"')";
        } else if (degree == 1) {
            //跨部门经理
        } else if (degree == 0) {
            //总裁
        }
        Page findPage = contractService.findPage(hql, page, Contract.class,null);
        this.push(findPage);
        page.setUrl("contractAction_list");
        return "list";
    }

    public String toview() {
        Contract contract = contractService.get(Contract.class, model.getId());
        if (contract.getCrequest() != null) {
            contract.setCrequest(contract.getCrequest().replace("\\r\\n", "<br/>"));
        }
        push(contract);
        return "toview";
    }

    public String tocreate() {

        return "tocreate";
    }

    public String insert() {
        User user = (User) getSession().get(SysContant.CURRENT_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());
        model.setCreateTime(new Timestamp(new Date().getTime()));
        contractService.saveOrUpdate(model);
        return "success";
    }

    public String toupdate() {
        Contract contract = contractService.get(Contract.class, model.getId());
        push(contract);
        return "toupdate";
    }

    public String update() {
        contractService.saveOrUpdate(model);
        return SUCCESS;
    }


    public String delete() {
        contractService.deleteById(Contract.class,model.getId());
        return SUCCESS;
    }
}
