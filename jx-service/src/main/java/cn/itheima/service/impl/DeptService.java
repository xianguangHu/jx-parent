package cn.itheima.service.impl;

import cn.itheima.common.SysContant;
import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.Dept;
import cn.itheima.service.IDeptService;
import cn.itheima.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author huxianguang
 * @create 2017-10-26-下午8:34
 **/
@Service
public class DeptService implements IDeptService{

    @Autowired
    private IBaseDao baseDao;

    public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public Dept get(Class<Dept> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    /**
     * 新增或更新
     * @param entity
     */
    public void saveOrUpdate(Dept entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //新增
            entity.setState(SysContant.ENABLE);
            baseDao.saveOrUpdate(entity);
        } else {
            //更新
            //先查询在更新
            Dept dept = baseDao.get(Dept.class, entity.getId());
            dept.setDeptName(entity.getDeptName());
            dept.setParent(entity.getParent());
            dept.setState(entity.getState());
            baseDao.saveOrUpdate(dept);
        }
    }

    public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
        return baseDao.find(hql,entityClass,params);
    }

    /**
     * 递归查询子部门
     * @param dept
     * @return
     */
    public List<Dept> findSon(Dept dept,List<Dept> deptList) {
        String hql = "from Dept where parent.id = ?";
        List<Dept> depts = find(hql, Dept.class, new Object[]{dept.getId()});
        deptList.addAll(depts);
        if (depts.size() > 0) {
            for (int i = 0; i < depts.size(); i++) {
                findSon(depts.get(i),deptList);
            }
        }
        return deptList;
    }

    /**
     * 递归删除子部门
     * @param entityClass
     * @param id
     */
    public void deleteById(Class<Dept> entityClass, Serializable id) {
        String hql = "from Dept where parent.id = ?";
        List<Dept> depts = find(hql, Dept.class, new Object[]{id});
        if (depts.size() > 0 ) {
            for (int i = 0; i < depts.size(); i++) {
                deleteById(Dept.class,depts.get(i).getId());
            }
        }

//        baseDao.deleteById(entityClass,id);
        //假删除
        Dept dept = get(Dept.class, id);
        dept.setState(SysContant.DISABLE);
        baseDao.saveOrUpdate(dept);
    }

    public void delete(Class<Dept> entityClass, Serializable[] ids) {
        for (int i = 0; i < ids.length; i++) {
            deleteById(entityClass,ids[i]);
        }
    }

}
