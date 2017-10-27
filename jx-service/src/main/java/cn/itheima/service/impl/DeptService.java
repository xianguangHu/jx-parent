package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.service.IDeptService;
import cn.itheima.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author huxianguang
 * @create 2017-10-26-下午8:34
 **/
@Service
public class DeptService implements IDeptService{

    @Autowired
    private IBaseDao baseDao;

    public <T> Page<T> findPage(String hql, Page<T> page, Class<T> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public <T> T get(Class<T> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

}
