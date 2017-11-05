package cn.itheima.domain;

import java.util.HashSet;
import java.util.Set;

import cn.itheima.domain.base.BaseEntity;
import org.apache.struts2.json.annotations.JSON;

import com.alibaba.fastjson.annotation.JSONField;

public class User extends BaseEntity {
	//id
	private String id;
	//部门:用户与部门  多对一
	private Dept dept;
	
	//名字
	private String userName;
	//密码
	private String password;
	//状态
	private Integer state;
	/**
	 *    java类   ---  hbm映射文件
	 *  1 Set集合                  <set>标签
	 *  2 List集合              <list>配置繁琐
	 *               <bag>标签和<set>标签配置一模一样     
	 *  
	 */
	private Set<Role> roles = new HashSet<Role>();
	@JSONField(serialize=false)
	@JSON(serialize=false)
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	//用户扩展信息,这个真不能new
	//用户和用户扩展信息：一对一
	private UserInfo userInfo;
	@JSONField(serialize=false)
	@JSON(serialize=false)
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JSONField(serialize=false)
	@JSON(serialize=false)
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", state=" + state + "]";
	}
	
	
}
