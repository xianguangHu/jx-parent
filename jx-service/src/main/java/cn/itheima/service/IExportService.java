package cn.itheima.service;

import cn.itheima.domain.Export;
import cn.itheima.util.Page;

import java.io.Serializable;
import java.util.List;

public interface IExportService {

    Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params);


    Export get(Class<Export> entityClass, Serializable id);

    void saveOrUpdate(Export entity);

    List<Export> find(String hql, Class<Export> entityClass, Object[] params);

    List<Export> findSon(Export Export, List<Export> ExportList);

    void deleteById(Class<Export> entityClass, Serializable id);

    void delete(Class<Export> entityClass, Serializable[] ids);

    List<String> childrenExportById(List<String> ExportIds, String ExportId);
}
