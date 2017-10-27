package cn.itheima.service;

import cn.itheima.util.Page;

import java.io.Serializable;

public interface IDeptService {

    public <T> Page<T> findPage(String hql, Page<T> page, Class<T> entityClass, Object[] params);


    public <T> T get(Class<T> entityClass, Serializable id);
}
