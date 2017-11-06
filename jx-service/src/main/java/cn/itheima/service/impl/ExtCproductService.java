package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.Contract;
import cn.itheima.domain.ContractProduct;
import cn.itheima.domain.ExtCproduct;
import cn.itheima.domain.Factory;
import cn.itheima.service.IContractService;
import cn.itheima.service.IExtCproductService;
import cn.itheima.service.IFactoryService;
import cn.itheima.util.Page;
import cn.itheima.util.UtilFuns;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-11-05-下午9:21
 **/
@Service
public class ExtCproductService  implements IExtCproductService{

    @Autowired
    private IBaseDao baseDao;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IFactoryService factoryService;

    public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params) {
        return null;
    }

    public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public void saveOrUpdate(ExtCproduct entity) {
        if (StringUtils.isBlank(entity.getId())) {
            Double amount = 0d;
            if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
                amount = entity.getCnumber() * entity.getPrice();
            }
            Contract contract = contractService.get(Contract.class, entity.getContractProduct().getContract().getId());
            contract.setTotalAmount(contract.getTotalAmount() + amount);
            entity.setAmount(amount);
            //插入
            baseDao.saveOrUpdate(entity);
        } else  {
            //更新 并修改金额
            ExtCproduct extCproduct = baseDao.get(ExtCproduct.class, entity.getId());
            Double amount = 0d;
            if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
                amount = entity.getCnumber() * entity.getPrice();
            }
            Contract contract = extCproduct.getContractProduct().getContract();
            contract.setTotalAmount(contract.getTotalAmount() - extCproduct.getAmount() + amount);
            Factory factory = factoryService.get(Factory.class, entity.getFactory().getId());
            extCproduct.setFactory(factory);
            extCproduct.setFactoryName(factory.getFactoryName());
            extCproduct.setProductNo(entity.getProductNo());
            extCproduct.setProductImage(entity.getProductImage());
            extCproduct.setCnumber(entity.getCnumber());
            extCproduct.setPackingUnit(entity.getPackingUnit());
            extCproduct.setPrice(entity.getPrice());
            extCproduct.setOrderNo(entity.getOrderNo());
            extCproduct.setProductDesc(entity.getProductDesc());
            extCproduct.setProductRequest(entity.getProductRequest());
            extCproduct.setAmount(amount);
        }
    }

    public void saveOrUpdateAll(Collection<ExtCproduct> entitys) {

    }

    public void deleteById(Class<ExtCproduct> entityClass, Serializable id) {
        ExtCproduct extCproduct = get(ExtCproduct.class, id);
        Contract contract = extCproduct.getContractProduct().getContract();
        contract.setTotalAmount(contract.getTotalAmount() - extCproduct.getAmount());
        baseDao.deleteById(entityClass,id);
    }

    public void delete(Class<ExtCproduct> entityClass, Serializable[] ids) {

    }
}
