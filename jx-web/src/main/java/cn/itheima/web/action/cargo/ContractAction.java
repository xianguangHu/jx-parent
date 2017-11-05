package cn.itheima.web.action.cargo;

import cn.itheima.domain.Contract;
import cn.itheima.service.IContractService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


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

    private Page page = new Page();

    public void setPage(Page page) {
        this.page = page;
    }

    public String list() {
        String hql="from Contract";
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
