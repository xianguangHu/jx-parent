package cn.itheima.service;


import cn.itheima.domain.ContractProduct;
import cn.itheima.util.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IContractProductService {
    //查询所有，带条件查询
    List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params);

    //获取一条记录
    ContractProduct get(Class<ContractProduct> entityClass, Serializable id);

    //分页查询，将数据封装到一个page分页工具类对象
    Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params);

    //新增和修改保存
    void saveOrUpdate(ContractProduct entity);

    //批量新增和修改保存
    void saveOrUpdateAll(Collection<ContractProduct> entitys);

    //单条删除，按id
    void deleteById(Class<ContractProduct> entityClass, Serializable id);

    //批量删除
    void delete(Class<ContractProduct> entityClass, Serializable[] ids);
}
