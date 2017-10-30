package cn.itheima.domain.base;

import java.sql.Timestamp;

/**
 * @author huxianguang
 * @create 2017-10-28-下午3:14
 *
 * **/
public class BaseEntity {
    private String createBy;//创建者id
    private String createTime;//创建者所在的部门id
    private Timestamp createDept;//创建时间
    private String updateBy;//更新者的id
    private Timestamp updateTime;//更新时间

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCreateDept() {
        return createDept;
    }

    public void setCreateDept(Timestamp createDept) {
        this.createDept = createDept;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
