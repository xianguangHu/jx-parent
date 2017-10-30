package cn.itheima.service;

import cn.itheima.domain.Dept;
import cn.itheima.util.Page;

import java.io.Serializable;
import java.util.List;

public interface IDeptService {

    Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params);


    Dept get(Class<Dept> entityClass, Serializable id);

    void saveOrUpdate(Dept entity);

    List<Dept> find(String hql, Class<Dept> entityClass, Object[] params);

    List<Dept> findSon(Dept dept,List<Dept> deptList);

    void deleteById(Class<Dept> entityClass, Serializable id);

    void delete(Class<Dept> entityClass, Serializable[] ids);
}
