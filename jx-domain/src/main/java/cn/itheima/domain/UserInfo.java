package cn.itheima.domain;

import cn.itheima.domain.base.BaseEntity;

import java.sql.Timestamp;

public class UserInfo extends BaseEntity {
	//主键
	private String id;
	//名字
	private String name;
//	/经理
	private User manager;
	//入职时间
	private Timestamp joinDate;
	//薪资
	private Double salary;
	//生日
	private Timestamp birthday;
	//性别
	private String gender;
	//职位
	private String station;
	//电话
	private String telephone;
	//级别
	private Integer degree;
	//备注
	private String remark;
	//排序号
	private Integer orderNo;
	//邮箱
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
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
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", joinDate="
				+ joinDate + ", salary=" + salary + ", birthday=" + birthday
				+ ", gender=" + gender + ", station=" + station
				+ ", telephone=" + telephone + ", degree=" + degree
				+ ", remark=" + remark + ", orderNo=" + orderNo + "]";
	}
		
	
	
	
}
