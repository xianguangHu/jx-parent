package cn.itheima.web.action.cargo;


import cn.itheima.domain.ContractProduct;
import cn.itheima.service.IContractProductService;
import cn.itheima.util.DownloadUtil;
import cn.itheima.util.UtilFuns;
import cn.itheima.web.action.BaseAction;
import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-11-03-下午8:04
 **/
@Controller
@Scope("prototype")
public class OutProductAction extends BaseAction {

    @Autowired
    private IContractProductService contractProductService;

    private String inputDate;

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String toedit() {

        return "toedit";
    }

    public String print() throws IOException, ParseException {
        //读取模板
        String path = ServletActionContext.getRequest().getRealPath("/");
        path += "make/xlsprint/tOUTPRODUCT.xls";
        FileInputStream is = new FileInputStream(new File(path));
        //读取模板的workBook
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        //获取sheet
        HSSFSheet sheet = workbook.getSheetAt(0);
        //公共变量
        int rowNo = 0;
        int cellNo = 1;
        Row nRow = null;
        Cell nCell = null;
        //获取大标题
        //获取行
        nRow = sheet.getRow(rowNo);
        //获取单元格
        nCell = nRow.getCell(cellNo);
        //设置内容
        nCell.setCellValue(inputDate.replace("-0","-").replace("-","年")+
        "月份出货表");
        //小标题的输出
        rowNo++;
        //内容的输出
        String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-mm') = ?";
        List<ContractProduct> contractProducts = contractProductService.find(hql, ContractProduct.class, new Object[]{inputDate});

        //行号变化
        rowNo++;
        //获取行
        nRow = sheet.getRow(rowNo);
        //客人	订单号	货号	数量	工厂	工厂交期	船期	贸易条款
        CellStyle customNameCellStyle = nRow.getCell(cellNo++).getCellStyle();
        CellStyle contractNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
        CellStyle productNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
        CellStyle cNumberCellStyle = nRow.getCell(cellNo++).getCellStyle();
        CellStyle factoryNameCellStyle = nRow.getCell(cellNo++).getCellStyle();
        CellStyle deliveryPeriodCellStyle = nRow.getCell(cellNo++).getCellStyle();
        CellStyle shipTimeCellStyle = nRow.getCell(cellNo++).getCellStyle();
        CellStyle tradeTermsCellStyle = nRow.getCell(cellNo++).getCellStyle();


        for (ContractProduct contractProduct : contractProducts) {
            //cellNo归1
            cellNo = 1;
            nRow = sheet.createRow(rowNo++);
            nRow.setHeightInPoints(24f);
            //客人
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(contractProduct.getContract().getContractNo());
            nCell.setCellStyle(customNameCellStyle);

            //订单号
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(contractProduct.getContract().getContractNo());
            nCell.setCellStyle(contractNoCellStyle);
            //货号
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(contractProduct.getProductNo());
            nCell.setCellStyle(productNoCellStyle);
            //数量
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(contractProduct.getCnumber());
            nCell.setCellStyle(cNumberCellStyle);
            //工厂
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(contractProduct.getFactoryName());
            nCell.setCellStyle(factoryNameCellStyle);
            //交期
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getDeliveryPeriod()));
            nCell.setCellStyle(deliveryPeriodCellStyle);
            //船期
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getShipTime()));
            nCell.setCellStyle(shipTimeCellStyle);
            //贸易条款
            nCell = nRow.createCell(cellNo++);
            nCell.setCellValue(contractProduct.getContract().getTradeTerms());
            nCell.setCellStyle(tradeTermsCellStyle);
        }

        //下载
        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream,ServletActionContext.getResponse(),"出货表.xls");
        return NONE;
    }

    public String printPdf() throws DocumentException, IOException, ParseException {
        //创建文档对象
        Document document = new Document(PageSize.A4, 10, 10, 10, 10);
        //设置输出位置
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        PdfWriter.getInstance(document,bao);
        //打开文档
        document.open();
        //写入内容

        //写入大标题
        BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont,
                AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
        //大标题font
        Font bigTitleFont = new Font(baseFont, 25f, Font.BOLD, BaseColor.BLACK);
//		/创建标题对象
        Paragraph bigTitleParagraph = new Paragraph("出货表", bigTitleFont);
        //设置对其方式
        bigTitleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        //添加至文档
        document.add(bigTitleParagraph);

        /*********小标题***********/
        Font titleFont = new Font(baseFont, 12f, Font.NORMAL, BaseColor.BLACK);
        PdfPTable table = new PdfPTable(8);

        String[] titles = {"客户","订单号","货号","数量	","工厂","工厂交期","船期","贸易条款"};

        for(String title:titles){
            table.addCell(new PdfPCell(new Phrase(title, titleFont)));
        }

        /**************内容的输出****************/
        String hql = "from ContractProduct where  to_char(contract.shipTime,'yyyy-mm') = ? ";
        //获取这个月需要上船的货物
        List<ContractProduct> cpList = contractProductService.find(hql, ContractProduct.class, new String[]{inputDate});

        for(ContractProduct cp:cpList){

            table.addCell(new PdfPCell(new Phrase(cp.getContract().getCustomName(), titleFont)));
            table.addCell(new PdfPCell(new Phrase(cp.getContract().getContractNo(), titleFont)));
            table.addCell(new PdfPCell(new Phrase(cp.getProductNo(), titleFont)));
            table.addCell(new PdfPCell(new Phrase(cp.getCnumber()+"", titleFont)));
            table.addCell(new PdfPCell(new Phrase(cp.getFactoryName(), titleFont)));
            table.addCell(new PdfPCell(new Phrase(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod()), titleFont)));
            table.addCell(new PdfPCell(new Phrase(UtilFuns.dateTimeFormat(cp.getContract().getShipTime()), titleFont)));
            table.addCell(new PdfPCell(new Phrase(cp.getContract().getTradeTerms(), titleFont)));

        }

        //将table添加至文档中
        document.add(table);

        //关闭文档
        document.close();
        //下载dfP/
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.download(bao, ServletActionContext.getResponse(), "出货表.pdf");

        return NONE;
    }
}
