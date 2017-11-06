package cn.itheima.web.action.cargo;

import cn.itheima.domain.ContractProduct;
import cn.itheima.domain.Factory;
import cn.itheima.service.IContractProductService;
import cn.itheima.service.IFactoryService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * @author huxianguang
 * @create 2017-11-03-下午8:04
 **/
@Controller
@Scope("prototype")
public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct> {

    ContractProduct model = new ContractProduct();

    public ContractProduct getModel() {
        return model;
    }

    private Page page = new Page();

    public void setPage(Page page) {
        this.page = page;
    }

    @Autowired
    private IFactoryService factoryService;

    @Autowired
    private IContractProductService contractProductService;

    public String tocreate() {
        String hql = "from Factory where ctype = '货物' and state = 1";
        List<Factory> factoryList = factoryService.find(hql, Factory.class, null);
        put("factoryList",factoryList);
        String phql = "from ContractProduct where contract.id = ?";
        Page<ContractProduct> page = contractProductService.findPage(phql, this.page, ContractProduct.class, new Object[]{model.getContract().getId()});
        page.setUrl("contractProductAction_tocreate");
        push(page);
        return "tocreate";
    }


    public String insert() {
        contractProductService.saveOrUpdate(model);
        return tocreate();
    }

    public String toupdate() {
        ContractProduct contractProduct = contractProductService.get(ContractProduct.class, model.getId());
        String hql = "from Factory where ctype = '货物' and state = 1";
        List<Factory> factoryList = factoryService.find(hql, Factory.class, null);
        put("factoryList",factoryList);
        push(contractProduct);
        return "toupdate";
    }

    public String update() {
        contractProductService.saveOrUpdate(model);
        return tocreate();
    }

    public String delete() {
        contractProductService.deleteById(ContractProduct.class,model.getId());
        return tocreate();
    }
}
