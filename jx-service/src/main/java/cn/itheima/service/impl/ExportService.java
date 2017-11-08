package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.*;
import cn.itheima.service.IExportService;
import cn.itheima.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author huxianguang
 * @create 2017-11-07-下午8:28
 **/
@Service
@Transactional
public class ExportService implements IExportService {

    @Autowired
    private IBaseDao baseDao;

    public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
        return null;
    }

    public Export get(Class<Export> entityClass, Serializable id) {
        return null;
    }

    public void saveOrUpdate(Export entity) {
        if (StringUtils.isBlank(entity.getId())) {
            String[] contractIds = entity.getContractIds().split(", ");
            //合同确认书
            String customerContract = "";
            for (String contractId : contractIds) {
                Contract contract = baseDao.get(Contract.class, contractId);
                customerContract += contract.getContractNo() + " ";
                //修改购销合同状态  2 为以保运
                contract.setState(2);
            }

            //将合同书给Export
            entity.setCustomerContract(customerContract);
            //设置制单日期
            entity.setInputDate(new Date());
            //设置出口单状态 0 为草稿
            entity.setState(0);

            //数据迁移
            String cid = "";
            for (String contractId : contractIds) {
                cid += contractId + "','";
            }
            cid = cid.substring(0,cid.length() - 3);
            String hql = "from ContractProduct where contract.id in ('"+cid+"')";
            List<ContractProduct> cpList = baseDao.find(hql, ContractProduct.class, null);
            Set<ExportProduct> epList = new HashSet<ExportProduct>();
            //迁移货物
            for (ContractProduct cp : cpList) {
                ExportProduct ep = new ExportProduct();
                ep.setFactory(cp.getFactory());
                ep.setBoxNum(cp.getBoxNum());
                ep.setCnumber(cp.getCnumber());
                ep.setExPrice(cp.getPrice());
                ep.setProductNo(cp.getProductNo());
                epList.add(ep);

                //迁移附件
                Set<ExtCproduct> ecp = cp.getExtCproducts();
                Set<ExtEproduct> eep = new HashSet<ExtEproduct>();
                for (ExtCproduct extCproduct : ecp) {
                    ExtEproduct extEproduct = new ExtEproduct();
                    BeanUtils.copyProperties(extCproduct,extEproduct);
                    extEproduct.setId(null);
                    eep.add(extEproduct);
                }

                ep.setExtEproducts(eep);

            }

            //将迁移的货物和附件添加到出口合同
            entity.setExportProducts(epList);
            //保存
            baseDao.saveOrUpdate(entity);
        } else {
            //更新
        }
    }

    public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
        return null;
    }

    public List<Export> findSon(Export Export, List<Export> ExportList) {
        return null;
    }

    public void deleteById(Class<Export> entityClass, Serializable id) {

    }

    public void delete(Class<Export> entityClass, Serializable[] ids) {

    }

    public List<String> childrenExportById(List<String> ExportIds, String ExportId) {
        return null;
    }
}
