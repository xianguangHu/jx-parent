package cn.itheima.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门实体类
 * @author huxianguang
 * @create 2017-10-26-下午8:05
 **/
public class Dept {

//    CREATE TABLE dept_p (
//            DEPT_ID VARCHAR2(40) PRIMARY KEY NOT NULL ,
//    DEPT_NAME VARCHAR2(40) DEFAULT NULL,
//    PARENT_ID VARCHAR2(40) DEFAULT NULL,
//    STATE NUMBER(11) DEFAULT NULL
//)

    private String id;
    private String deptName;
    private Dept parent;
    private Integer state;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
