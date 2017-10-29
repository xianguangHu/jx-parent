package cn.itheima.service;


import cn.itheima.domain.User;
import cn.itheima.util.Page;

public interface IUserService {
    Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params);

}
