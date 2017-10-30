package cn.itheima.service;


import cn.itheima.domain.Role;
import cn.itheima.util.Page;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IRoleService {
    //查询所有，带条件查询
    List<Role> find(String hql, Class<Role> entityClass, Object[] params);

    //获取一条记录
    Role get(Class<Role> entityClass, Serializable id);

    //分页查询，将数据封装到一个page分页工具类对象
    Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params);

    //新增和修改保存
    void saveOrUpdate(Role entity);

    //批量新增和修改保存
    void saveOrUpdateAll(Collection<Role> entitys);

    //单条删除，按id
    void deleteById(Class<Role> entityClass, Serializable id);

    //批量删除
    void delete(Class<Role> entityClass, Serializable[] ids);
}
