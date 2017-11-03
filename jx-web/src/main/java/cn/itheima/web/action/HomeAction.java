package cn.itheima.web.action;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class HomeAction extends BaseAction{

	private String moduleName;		//动态指定跳转的模块，在struts.xml中配置动态的result
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String fmain(){
		return "fmain";
	}
	
	public String title(){
		return "title";
	}

	//转向moduleName指向的模块
	public String tomain(){
		return "tomain";
	}

	public String toleft(){
		
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		return "toleft";
	}

}
