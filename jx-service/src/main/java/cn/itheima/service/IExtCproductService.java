package cn.itheima.service;


import cn.itheima.domain.ExtCproduct;
import cn.itheima.util.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IExtCproductService {
    //查询所有，带条件查询
    List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params);

    //获取一条记录
    ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id);

    //分页查询，将数据封装到一个page分页工具类对象
    Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params);

    //新增和修改保存
    void saveOrUpdate(ExtCproduct entity);

    //批量新增和修改保存
    void saveOrUpdateAll(Collection<ExtCproduct> entitys);

    //单条删除，按id
    void deleteById(Class<ExtCproduct> entityClass, Serializable id);

    //批量删除
    void delete(Class<ExtCproduct> entityClass, Serializable[] ids);
}
