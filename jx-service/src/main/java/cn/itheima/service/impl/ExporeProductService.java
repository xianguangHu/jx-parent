package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.ExportProduct;
import cn.itheima.service.IExportProductService;
import cn.itheima.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-11-09-下午9:00
 **/
@Service
public class ExporeProductService implements IExportProductService {

    @Autowired
    private IBaseDao baseDao;


    public Page<ExportProduct> findPage(String hql, Page<ExportProduct> page, Class<ExportProduct> entityClass, Object[] params) {
        return null;
    }

    public ExportProduct get(Class<ExportProduct> entityClass, Serializable id) {
        return baseDao.get(ExportProduct.class,id);
    }

    public void saveOrUpdate(ExportProduct entity) {
        baseDao.saveOrUpdate(entity);
    }

    public List<ExportProduct> find(String hql, Class<ExportProduct> entityClass, Object[] params) {
        return null;
    }

    public List<ExportProduct> findSon(ExportProduct ExportProduct, List<ExportProduct> ExportProductList) {
        return null;
    }

    public void deleteById(Class<ExportProduct> entityClass, Serializable id) {

    }

    public void delete(Class<ExportProduct> entityClass, Serializable[] ids) {

    }

    public List<String> childrenExportProductById(List<String> ExportProductIds, String ExportProductId) {
        return null;
    }
}
