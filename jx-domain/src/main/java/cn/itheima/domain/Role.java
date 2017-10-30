package cn.itheima.domain;

import cn.itheima.domain.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huxianguang
 * @create 2017-10-29-下午9:15
 **/
public class Role extends BaseEntity {

    private String id;
    private String name;
    private String remark;
    private Integer orderNo;

    private Set<Module> modules = new HashSet<Module>();

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    private Set<User> users = new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}
