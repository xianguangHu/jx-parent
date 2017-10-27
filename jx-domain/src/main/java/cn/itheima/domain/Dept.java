package cn.itheima.domain;

/**
 * 不猛实体类
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
