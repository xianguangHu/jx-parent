package cn.itheima.domain;

import java.util.HashSet;
import java.util.Set;

public class Dept {
	//主键，id
	private String id;
	//部门名称
	private String deptName;
	//父部门：自关联
	private Dept parent;
	//状态:1代表启用，0代表停用，默认为1
	private Integer state;
	
	//部门与用户：一对多,此处必须手动new，否则会报错
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
	@Override
	public String toString() {
		return "Dept [id=" + id + ", deptName=" + deptName + ", parent="
				+ parent + ", state=" + state + "]";
	}
	
	

	
	
}
