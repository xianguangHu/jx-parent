package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.Factory;
import cn.itheima.service.IFactoryService;
import cn.itheima.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-11-05-下午3:40
 **/
@Service
public class FactoryService implements IFactoryService{

    @Autowired
    private IBaseDao baseDao;
    public List<Factory> find(String hql, Class<Factory> entityClass, Object[] params) {
        return baseDao.find(hql,entityClass,params);
    }

    public Factory get(Class<Factory> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    public Page<Factory> findPage(String hql, Page<Factory> page, Class<Factory> entityClass, Object[] params) {
        return null;
    }

    public void saveOrUpdate(Factory entity) {

    }

    public void saveOrUpdateAll(Collection<Factory> entitys) {

    }

    public void deleteById(Class<Factory> entityClass, Serializable id) {

    }

    public void delete(Class<Factory> entityClass, Serializable[] ids) {

    }
}
