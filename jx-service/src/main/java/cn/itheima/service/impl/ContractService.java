package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.Contract;
import cn.itheima.service.IContractService;
import cn.itheima.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-11-03-下午8:09
 **/
@Service
public class ContractService implements IContractService{

    @Autowired
    private IBaseDao baseDao;

    public List<Contract> find(String hql, Class<Contract> entityClass, Object[] params) {
        return null;
    }

    public Contract get(Class<Contract> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    public Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public void saveOrUpdate(Contract entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //插入
            baseDao.saveOrUpdate(entity);
        } else {
            //更新
            Contract contract = get(Contract.class, entity.getId());
            contract.setCustomName(entity.getCustomName());
            //利用快照来保存
        }
    }

    public void saveOrUpdateAll(Collection<Contract> entitys) {

    }

    public void deleteById(Class<Contract> entityClass, Serializable id) {
        baseDao.deleteById(entityClass,id);
    }

    public void delete(Class<Contract> entityClass, Serializable[] ids) {

    }
}
