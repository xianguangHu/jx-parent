package cn.itheima.web.action.cargo;

import cn.itheima.domain.Contract;
import cn.itheima.domain.Export;
import cn.itheima.domain.ExportProduct;
import cn.itheima.service.IContractService;
import cn.itheima.service.IExportProductService;
import cn.itheima.service.IExportService;
import cn.itheima.util.Page;
import cn.itheima.util.UtilFuns;
import cn.itheima.vo.ExportProductResult;
import cn.itheima.vo.ExportProductVo;
import cn.itheima.vo.ExportResult;
import cn.itheima.vo.ExportVo;
import cn.itheima.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    private IExportProductService exportProductService;

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

    public String list() {
        String hql = "from Export";
        exportService.findPage(hql,page,Export.class,null);
        page.setUrl("exportAction_list");
        push(page);
        return "list";
    }

    public String toupdate() {
        Export export = exportService.get(Export.class, model.getId());
        push(export);


        //查找报运单下所有的货物
        Set<ExportProduct> epSet = export.getExportProducts();

        StringBuffer sb = new StringBuffer();

        for(ExportProduct ep:epSet){
            sb.append("addTRRecord('mRecordTable',");
            sb.append("'"+ep.getId()+"',");
            sb.append("'"+ep.getProductNo()+"',");
            sb.append("'"+ep.getCnumber()+"',");
            sb.append("'"+(ep.getGrossWeight()==null?"":ep.getGrossWeight())+"',");
            sb.append("'"+UtilFuns.convertNull(ep.getNetWeight())+"',");
            sb.append("'"+UtilFuns.convertNull(ep.getSizeLength())+"',");
            sb.append("'"+UtilFuns.convertNull(ep.getSizeWidth())+"',");
            sb.append("'"+UtilFuns.convertNull(ep.getSizeHeight())+"',");
            sb.append("'"+UtilFuns.convertNull(ep.getExPrice())+"',");
            sb.append("'"+UtilFuns.convertNull(ep.getTax())+"');");
        }

        //放入值栈
        this.put("mRecordData", sb.toString());
        return "toupdate";
    }

    public String toview() {
        Export export = exportService.get(Export.class, model.getId());
        push(export);
        return "toview";
    }

    private String[] mr_id;
    private Integer[] mr_changed;
    private Integer[] mr_cnumber;
    private Double[] mr_grossWeight;
    private Double[] mr_netWeight;
    private Double[] mr_sizeLength;
    private Double[] mr_sizeWidth;
    private Double[] mr_sizeHeight;
    private Double[] mr_exPrice;
    private Double[] mr_tax;

    public String update() {
        Export export = exportService.get(Export.class, model.getId());
        //把页面你的属性赋值给export这个持久化对象
        //报运号
        export.setCustomerContract(model.getCustomerContract());
        //制单日期
        export.setInputDate(model.getInputDate());
        //信用证号
        export.setLcno(model.getLcno());
        //收货人及地址
        export.setConsignee(model.getConsignee());
        //装运港
        export.setShipmentPort(model.getShipmentPort());
        //目的港
        export.setDestinationPort(model.getDestinationPort());
        //运输方式
        export.setTransportMode(model.getTransportMode());
        //价格条件
        export.setPriceCondition(model.getPriceCondition());
        //唛头
        export.setMarks(model.getMarks());
        //备注
        export.setRemark(model.getRemark());

        //修改货物
        Set<ExportProduct> epSet = new HashSet<ExportProduct>();

        if(mr_changed.length>0){
            for(int i = 0;i<mr_id.length;i++){
                String id = mr_id[i];
                //根据id获取货物信息
                ExportProduct ep = exportProductService.get(ExportProduct.class, id);

                if(mr_changed[i]!=null&&mr_changed[i]==1){
                    ep.setCnumber(mr_cnumber[i]);
                    ep.setGrossWeight(mr_grossWeight[i]);
                    ep.setNetWeight(mr_netWeight[i]);
                    ep.setSizeLength(mr_sizeLength[i]);
                    ep.setSizeWidth(mr_sizeWidth[i]);
                    ep.setSizeHeight(mr_sizeHeight[i]);
                    ep.setExPrice(mr_exPrice[i]);
                    ep.setTax(mr_tax[i]);
                }
                epSet.add(ep);
            }
        }

        //将epSet给export
        export.setExportProducts(epSet);

        exportService.saveOrUpdate(export);
        return "success";
    }


    public String submit() throws Exception {

        String[] ids = model.getId().split(", ");

        for(String id:ids){
            Export export = exportService.get(Export.class, id);
            export.setState(1);
            exportService.saveOrUpdate(export);
        }

        return SUCCESS;
    }

    public String cancel() throws Exception {

        String[] ids = model.getId().split(", ");

        for(String id:ids){
            Export export = exportService.get(Export.class, id);
            export.setState(0);
            exportService.saveOrUpdate(export);
        }

        return SUCCESS;
    }

    public String delete() {
        String[] ids = model.getId().split(", ");
        exportService.delete(Export.class,ids);
        return SUCCESS;
    }

    public String[] getMr_id() {
        return mr_id;
    }

    public void setMr_id(String[] mr_id) {
        this.mr_id = mr_id;
    }

    public Integer[] getMr_changed() {
        return mr_changed;
    }

    public void setMr_changed(Integer[] mr_changed) {
        this.mr_changed = mr_changed;
    }

    public Integer[] getMr_cnumber() {
        return mr_cnumber;
    }

    public void setMr_cnumber(Integer[] mr_cnumber) {
        this.mr_cnumber = mr_cnumber;
    }

    public Double[] getMr_grossWeight() {
        return mr_grossWeight;
    }

    public void setMr_grossWeight(Double[] mr_grossWeight) {
        this.mr_grossWeight = mr_grossWeight;
    }

    public Double[] getMr_netWeight() {
        return mr_netWeight;
    }

    public void setMr_netWeight(Double[] mr_netWeight) {
        this.mr_netWeight = mr_netWeight;
    }

    public Double[] getMr_sizeLength() {
        return mr_sizeLength;
    }

    public void setMr_sizeLength(Double[] mr_sizeLength) {
        this.mr_sizeLength = mr_sizeLength;
    }

    public Double[] getMr_sizeWidth() {
        return mr_sizeWidth;
    }

    public void setMr_sizeWidth(Double[] mr_sizeWidth) {
        this.mr_sizeWidth = mr_sizeWidth;
    }

    public Double[] getMr_sizeHeight() {
        return mr_sizeHeight;
    }

    public void setMr_sizeHeight(Double[] mr_sizeHeight) {
        this.mr_sizeHeight = mr_sizeHeight;
    }

    public Double[] getMr_exPrice() {
        return mr_exPrice;
    }

    public void setMr_exPrice(Double[] mr_exPrice) {
        this.mr_exPrice = mr_exPrice;
    }

    public Double[] getMr_tax() {
        return mr_tax;
    }

    public void setMr_tax(Double[] mr_tax) {
        this.mr_tax = mr_tax;
    }

    public String export() {
        Export export = exportService.get(Export.class, model.getId());
        //将export转成exportVo
        ExportVo vo = new ExportVo();
        BeanUtils.copyProperties(export,vo);
        vo.setExportId(export.getId());
        //将货物exportPorduct转成vo
        Set<ExportProduct> exportProducts = export.getExportProducts();
        Set<ExportProductVo> exportProductVos = new HashSet<ExportProductVo>();
        for (ExportProduct exportProduct : exportProducts) {
            //数据迁移
            ExportProductVo exportProductVo = new ExportProductVo();
            BeanUtils.copyProperties(exportProduct,exportProductVo);
            exportProductVo.setExportProductId(exportProduct.getId());
            exportProductVos.add(exportProductVo);
        }
        vo.setProducts(exportProductVos);
        //调用远程方法
        ExportResult result = WebClient.create("http://localhost:8090/ws/exportE/exportE")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(vo, ExportResult.class);

        //9 解析数据，存入本地数据库
        //9.1 将state、remark、修改export
        export.setState(result.getState());
        export.setRemark(result.getRemark());

        //9.2 修改货物的税收
        Set<ExportProductResult> eprSet = result.getProducts();
        //9.3 遍历eprSet
        for(ExportProductResult epr:eprSet){
            //9.3.1 根据id 获取本地数据库的对象，修改的是持久态的对象
            ExportProduct ep = exportProductService.get(ExportProduct.class, epr.getExportProductId());
            ep.setTax(epr.getTax());
            exportProductService.saveOrUpdate(ep);
        }
        //9.4 更新export
        exportService.saveOrUpdate(export);
        return SUCCESS;
    }
}
