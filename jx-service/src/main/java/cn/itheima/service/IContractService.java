package cn.itheima.service;


import cn.itheima.domain.Contract;
import cn.itheima.util.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IContractService {
    //查询所有，带条件查询
    List<Contract> find(String hql, Class<Contract> entityClass, Object[] params);

    //获取一条记录
    Contract get(Class<Contract> entityClass, Serializable id);

    //分页查询，将数据封装到一个page分页工具类对象
    Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params);

    //新增和修改保存
    void saveOrUpdate(Contract entity);

    //批量新增和修改保存
    void saveOrUpdateAll(Collection<Contract> entitys);

    //单条删除，按id
    void deleteById(Class<Contract> entityClass, Serializable id);

    //批量删除
    void delete(Class<Contract> entityClass, Serializable[] ids);
}
