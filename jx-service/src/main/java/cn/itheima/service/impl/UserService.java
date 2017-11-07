package cn.itheima.service.impl;

import cn.itheima.dao.IBaseDao;
import cn.itheima.domain.User;
import cn.itheima.domain.UserInfo;
import cn.itheima.service.IUserService;
import cn.itheima.util.Encrypt;
import cn.itheima.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author huxianguang
 * @create 2017-10-28-下午3:55
 **/
@Service
public class UserService implements IUserService {


    @Autowired
    private SimpleMailMessage mailMessage;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private IBaseDao baseDao;

    public List<User> find(String hql, Class<User> entityClass, Object[] params) {
        return baseDao.find(hql,entityClass,params);
    }

    public User get(Class<User> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }

    public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
        return baseDao.findPage(hql,page,entityClass,params);
    }

    public void saveOrUpdate(final User entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //插入
            String uuid = UUID.randomUUID().toString();
            entity.setId(uuid);
            entity.getUserInfo().setId(uuid);
            //原始密码
            final String pwd = randomPwd();
            String md5pwd = Encrypt.md5("123456", entity.getUserName());
            entity.setPassword(md5pwd);
            new Thread(new Runnable() {
                public void run() {
                    mailMessage.setSubject("邮箱账号发送");
                    mailMessage.setText("你账号是:"+entity.getUserName()+",登录密码:"+pwd);
                    mailMessage.setTo(entity.getUserInfo().getEmail());
                    //发送
                    mailSender.send(mailMessage);
                }
            }).start();
            baseDao.saveOrUpdate(entity);
        } else {
            //更新
            User user = baseDao.get(User.class, entity.getId());
            user.setDept(entity.getDept());
            user.setState(entity.getState());
            baseDao.saveOrUpdate(user);
        }
    }


    public String randomPwd(){
        String pwd = "";

        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWSYZ0123456789!@#$%^&*()_+";

        Random random = new Random();
        for(int i=0;i<6;i++){
            int index = random.nextInt(str.length());
            pwd+=str.charAt(index);
        }

        return pwd;
    }

    public void saveOrUpdateAll(Collection<User> entitys) {

    }

    public void deleteById(Class<User> entityClass, Serializable id) {
        baseDao.deleteById(entityClass,id);
    }

    public void delete(Class<User> entityClass, Serializable[] ids) {

    }

    /**
     * 根据username来查找User
     * @param username
     * @return
     */
    public User findUserByUsername(String username) {
        String hql = "from User where userName = ?";
        List<User> users = baseDao.find(hql, User.class, new Object[]{username});
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
