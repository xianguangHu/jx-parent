package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.Role;
import cn.itheima.service.IRoleService;
import cn.itheima.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-10-29-下午9:35
 **/
@Service
public class RoleService implements IRoleService {

    @Autowired
    private IBaseDao baseDao;

    public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {
        return null;
    }

    public Role get(Class<Role> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public void saveOrUpdate(Role entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //插入
            baseDao.saveOrUpdate(entity);
        }else {
            Role role = baseDao.get(Role.class, entity.getId());
            role.setName(entity.getName());
            role.setRemark(entity.getRemark());
            baseDao.saveOrUpdate(role);
        }
    }

    public void saveOrUpdateAll(Collection<Role> entitys) {

    }

    public void deleteById(Class<Role> entityClass, Serializable id) {
        baseDao.deleteById(Role.class,id);
    }

    public void delete(Class<Role> entityClass, Serializable[] ids) {

    }
}
