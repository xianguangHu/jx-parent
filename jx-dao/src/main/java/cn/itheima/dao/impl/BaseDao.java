package cn.itheima.dao.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.util.Page;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-10-26-下午8:32
 **/
public class BaseDao implements IBaseDao{


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    public <T> List<T> find(String hql, Class<T> entityClass, Object[] params) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.list();
    }

    public <T> T get(Class<T> entityClass, Serializable id) {
        return getSession().get(entityClass, id);
    }

    /**
     * 分页查询
     * @param hql
     * @param page
     * @param entityClass
     * @param params
     * @param <T>
     * @return
     */
    public <T> Page<T> findPage(String hql, Page<T> page, Class<T> entityClass, Object[] params) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i,params[i]);
            }
        }
        //总记录数
        int size = query.list().size();
        page.setTotalRecord(size);

        //设置分页
        query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        page.setResults(query.list());
        return page;
    }

    public <T> void saveOrUpdate(T entity) {
        getSession().save(entity);
    }

    public <T> void saveOrUpdateAll(Collection<T> entitys) {

    }

    public <T> void deleteById(Class<T> entityClass, Serializable id) {
        getSession().delete(get(entityClass,id));
    }

    public <T> void delete(Class<T> entityClass, Serializable[] ids) {

    }
}
