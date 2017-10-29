package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.User;
import cn.itheima.service.IUserService;
import cn.itheima.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huxianguang
 * @create 2017-10-28-下午3:55
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private IBaseDao baseDao;

    public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }
}
