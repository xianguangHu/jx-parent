package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.Module;
import cn.itheima.service.IModuleService;
import cn.itheima.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-10-30-上午9:36
 **/
@Service
public class ModuleService implements IModuleService {

    @Autowired
    private IBaseDao baseDao;

    public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {
        return baseDao.find(hql,entityClass,params);
    }

    public Module get(Class<Module> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    public Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public void saveOrUpdate(Module entity) {

        if (StringUtils.isBlank(entity.getId())) {
            //插入
            baseDao.saveOrUpdate(entity);
        } else {
            Module module = get(Module.class, entity.getId());
            module.setName(entity.getName());
            module.setLayerNum(entity.getLayerNum());
            module.setCpermission(entity.getCpermission());
            module.setCurl(entity.getCurl());
            module.setCtype(entity.getCtype());
            module.setState(entity.getState());
            module.setBelong(entity.getBelong());
            module.setCwhich(entity.getCwhich());
            module.setRemark(entity.getRemark());
            module.setOrderNo(entity.getOrderNo());

            baseDao.saveOrUpdate(module);
        }
    }

    public void saveOrUpdateAll(Collection<Module> entitys) {

    }

    public void deleteById(Class<Module> entityClass, Serializable id) {
        baseDao.deleteById(entityClass,id);
    }

    public void delete(Class<Module> entityClass, Serializable[] ids) {

    }
}
