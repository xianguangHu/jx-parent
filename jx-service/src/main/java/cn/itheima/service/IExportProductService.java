package cn.itheima.service;

import cn.itheima.domain.ExportProduct;
import cn.itheima.util.Page;

import java.io.Serializable;
import java.util.List;

public interface IExportProductService {

    Page<ExportProduct> findPage(String hql, Page<ExportProduct> page, Class<ExportProduct> entityClass, Object[] params);


    ExportProduct get(Class<ExportProduct> entityClass, Serializable id);

    void saveOrUpdate(ExportProduct entity);

    List<ExportProduct> find(String hql, Class<ExportProduct> entityClass, Object[] params);

    List<ExportProduct> findSon(ExportProduct ExportProduct, List<ExportProduct> ExportProductList);

    void deleteById(Class<ExportProduct> entityClass, Serializable id);

    void delete(Class<ExportProduct> entityClass, Serializable[] ids);

    List<String> childrenExportProductById(List<String> ExportProductIds, String ExportProductId);
}
