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


    public <User> List<User> find(String hql, Class<User> entityClass, Object[] params) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.list();
    }

    public <User> User get(Class<User> entityClass, Serializable id) {
        return getSession().get(entityClass, id);
    }

    /**
     * 分页查询
     * @param hql
     * @param page
     * @param entityClass
     * @param params
     * @param <User>
     * @return
     */
    public <User> Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
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

    public <User> void saveOrUpdate(User entity) {
        getSession().save(entity);
    }

    public <User> void saveOrUpdateAll(Collection<User> entitys) {

    }

    public <User> void deleteById(Class<User> entityClass, Serializable id) {
        getSession().delete(get(entityClass,id));
    }

    public <User> void delete(Class<User> entityClass, Serializable[] ids) {

    }
}
