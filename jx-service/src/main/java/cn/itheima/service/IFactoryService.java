package cn.itheima.service;


import cn.itheima.domain.Factory;
import cn.itheima.util.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IFactoryService {
    //查询所有，带条件查询
    List<Factory> find(String hql, Class<Factory> entityClass, Object[] params);

    //获取一条记录
    Factory get(Class<Factory> entityClass, Serializable id);

    //分页查询，将数据封装到一个page分页工具类对象
    Page<Factory> findPage(String hql, Page<Factory> page, Class<Factory> entityClass, Object[] params);

    //新增和修改保存
    void saveOrUpdate(Factory entity);

    //批量新增和修改保存
    void saveOrUpdateAll(Collection<Factory> entitys);

    //单条删除，按id
    void deleteById(Class<Factory> entityClass, Serializable id);

    //批量删除
    void delete(Class<Factory> entityClass, Serializable[] ids);
}
