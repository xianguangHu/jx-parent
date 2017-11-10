package cn.itheima.web.action.cargo;

import cn.itheima.domain.Factory;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author huxianguang
 * @create 2017-11-10-上午10:52
 **/
@Controller
@Scope("prototype")
public class FactoryAction extends BaseAction implements ModelDriven<Factory>{

    private Factory model = new Factory();

    public Factory getModel() {
        return model;
    }

    public String toview () {
        return "toview";
    }
}
