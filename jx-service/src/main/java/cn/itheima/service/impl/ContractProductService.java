package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.Contract;
import cn.itheima.domain.ContractProduct;
import cn.itheima.domain.Factory;
import cn.itheima.service.IContractProductService;
import cn.itheima.service.IContractService;
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
 * @create 2017-11-05-下午7:38
 **/
@Service
public class ContractProductService implements IContractProductService {

    @Autowired
    private IBaseDao baseDao;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IFactoryService factoryService;
    public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params) {
        return baseDao.find(hql,entityClass,params);
    }

    public ContractProduct get(Class<ContractProduct> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public void saveOrUpdate(ContractProduct entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //保存
            Double amount = 0d;
            if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
                amount = entity.getCnumber() * entity.getPrice();
            }

            entity.setAmount(amount);
            Contract contract = contractService.get(Contract.class, entity.getContract().getId());
            contract.setTotalAmount(contract.getTotalAmount() + amount);
            baseDao.saveOrUpdate(entity);

        } else {
            //更新
            ContractProduct contractProduct = get(ContractProduct.class, entity.getId());
            Double amount = 0d;
            if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
                amount = entity.getCnumber() * entity.getPrice();
            }

            Contract contract = contractProduct.getContract();
            contract.setTotalAmount(contract.getTotalAmount() - contractProduct.getAmount() + amount);

            contractProduct.setAmount(amount);
            Factory factory = factoryService.get(Factory.class, entity.getFactory().getId());
            contractProduct.setFactory(factory);
            contractProduct.setFactoryName(factory.getFactoryName());
            contractProduct.setProductNo(entity.getProductNo());
            contractProduct.setProductImage(entity.getProductImage());
            contractProduct.setCnumber(entity.getCnumber());
//            contractProduct.setAmount(entity.getAmount());
            contractProduct.setPackingUnit(entity.getPackingUnit());
            contractProduct.setLoadingRate(entity.getLoadingRate());
            contractProduct.setBoxNum(entity.getBoxNum());
            contractProduct.setPrice(entity.getPrice());
            contractProduct.setOrderNo(entity.getOrderNo());
            contractProduct.setProductDesc(entity.getProductDesc());
            contractProduct.setProductRequest(entity.getProductRequest());



        }
    }

    public void saveOrUpdateAll(Collection<ContractProduct> entitys) {

    }

    public void deleteById(Class<ContractProduct> entityClass, Serializable id) {
        ContractProduct contractProduct = get(entityClass, id);
        //修改价格
        Contract contract = contractProduct.getContract();
        contract.setTotalAmount(contract.getTotalAmount() - contractProduct.getAmount());
        baseDao.deleteById(entityClass,id);
    }

    public void delete(Class<ContractProduct> entityClass, Serializable[] ids) {

    }
}
