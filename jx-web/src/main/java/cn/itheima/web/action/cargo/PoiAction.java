package cn.itheima.web.action.cargo;

import cn.itheima.domain.Factory;
import cn.itheima.service.IFactoryService;
import cn.itheima.util.DownloadUtil;
import cn.itheima.web.action.BaseAction;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private IFactoryService factoryService;

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

    private File upload;//上传的文件
    //上传的文件类型
    private String uploadContentType;
    //上传的文件名
    private String uploadFileName;
    public String upload() throws IOException {
        Workbook workbook = new HSSFWorkbook(new FileInputStream(upload));
        Sheet sheet = workbook.getSheetAt(0);
        int rowNo = 2;
        int cellNo = 0;
        Row nRow = null;
        Cell cell  = null;

        while (true) {
            try {
                cellNo = 0;
                //获取行
                nRow = sheet.getRow(rowNo++);
                //获取列的数据
                String ctype = nRow.getCell(cellNo++).getStringCellValue();
                //工厂全称
                String fullName = nRow.getCell(cellNo++).getStringCellValue();
                //工厂简称
                String factoryName = nRow.getCell(cellNo++).getStringCellValue();
                //联系人
                String contacts = nRow.getCell(cellNo++).getStringCellValue();
                //联系电话
                String phone = nRow.getCell(cellNo++).getStringCellValue();
                //手机号
                String mobile = nRow.getCell(cellNo++).getStringCellValue();
                //传真
                String fax = nRow.getCell(cellNo++).getStringCellValue();
                //地址
                String address = nRow.getCell(cellNo++).getStringCellValue();
                //杰信代表
                String inspector = nRow.getCell(cellNo++).getStringCellValue();
                //备注
                String remark = nRow.getCell(cellNo++).getStringCellValue();
                Integer orderNo = new Integer((int)nRow.getCell(cellNo++).getNumericCellValue());
                //状态
                Integer state = new Integer((int)nRow.getCell(cellNo++).getNumericCellValue());

                Factory factory = new Factory(ctype, fullName, factoryName, contacts, phone, mobile, fax, address, inspector, remark, orderNo, state);

                factoryService.saveOrUpdate(factory);
            } catch (Exception e) {
              break;
            }
        }
        return "upload";
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
