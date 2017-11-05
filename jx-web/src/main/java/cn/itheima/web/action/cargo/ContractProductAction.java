package cn.itheima.web.action.cargo;

import cn.itheima.domain.Contract;
import cn.itheima.domain.ContractProduct;
import cn.itheima.domain.Factory;
import cn.itheima.service.IContractService;
import cn.itheima.service.IFactoryService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.javac.tree.JCTree;
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

    public String tocreate() {
        String hql = "from Factory";
        List<Factory> factoryList = factoryService.find(hql, Factory.class, null);
        put("factoryList",factoryList);
        return "tocreate";
    }
}
