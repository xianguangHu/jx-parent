package cn.itheima.web.action.cargo;

import cn.itheima.util.DownloadUtil;
import cn.itheima.web.action.BaseAction;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;

/**
 * @author huxianguang
 * @create 2017-11-10-上午10:57
 **/
@Controller
@Scope("prototype")
public class PoiAction extends BaseAction{


    public String download() throws IOException {
        String path = ServletActionContext.getRequest().getRealPath("/");
        path += "make/xlsprint/tFactory.xls";
        FileInputStream is = new FileInputStream(new File(path));
        Workbook workbook = new HSSFWorkbook(is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.download(bos,ServletActionContext.getResponse(),"工厂模板.xls");
        return NONE;
    }

    public String upload() {

        return "upload";
    }
}
