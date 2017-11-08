package cn.itheima.web.action.cargo;

import cn.itheima.domain.Contract;
import cn.itheima.domain.Export;
import cn.itheima.service.IContractService;
import cn.itheima.service.IExportService;
import cn.itheima.util.Page;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author huxianguang
 * @create 2017-11-07-下午7:52
 **/
@Controller
@Scope("prototype")
public class ExportAction extends BaseAction implements ModelDriven<Export>{

    private Export model = new Export();

    public Export getModel() {
        return model;
    }

    private Page page = new Page();

    public void setModel(Export model) {
        this.model = model;
    }

    @Autowired
    private IContractService contractService;

    @Autowired
    private IExportService exportService;
    public String contractList() {
        String hql = "from Contract where state = 1";
        contractService.findPage(hql,page, Contract.class,null);
        page.setUrl("exportAction_contractList");
        push(page);
        return "contractList";
    }

    public String tocreate() {

        return "tocreate";
    }

    public String insert() {
        exportService.saveOrUpdate(model);
        return "success";
    }

}
