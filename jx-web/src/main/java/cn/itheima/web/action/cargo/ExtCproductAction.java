package cn.itheima.web.action.cargo;

import cn.itheima.domain.ExtCproduct;
import cn.itheima.domain.Factory;
import cn.itheima.service.IExtCproductService;
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
public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct> {

    @Autowired
    private IExtCproductService extCproductService;

    @Autowired
    private IFactoryService factoryService;
    private ExtCproduct model = new ExtCproduct();

    public ExtCproduct getModel() {
        return model;
    }

    private Page page = new Page();
    public String tocreate() {
        String hql = "from Factory where ctype = '附件' and state = 1";
        List<Factory> factories = factoryService.find(hql, Factory.class, null);
        put("factoryList",factories);
        String ehql = "from ExtCproduct where contractProduct.id = ?";
        extCproductService.findPage(ehql,page,ExtCproduct.class,new Object[]{model.getContractProduct().getId()});
        page.setUrl("extCproductAction_tocreate");
        push(page);
        return "tocreate";
    }

    public String insert() {
        extCproductService.saveOrUpdate(model);
        return tocreate();
    }

    public String toupdate() {
        String hql = "from Factory where ctype = '附件' and state = 1";
        List<Factory> factories = factoryService.find(hql, Factory.class, null);
        put("factoryList",factories);
        ExtCproduct extCproduct = extCproductService.get(ExtCproduct.class, model.getId());
        push(extCproduct);
        return "toupdate";
    }

    public String update() {
        extCproductService.saveOrUpdate(model);
        return tocreate();
    }

    public String delete() {
        extCproductService.deleteById(ExtCproduct.class,model.getId());
        return tocreate();
    }
}
