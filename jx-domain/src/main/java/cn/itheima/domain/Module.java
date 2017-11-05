package cn.itheima.domain;

import cn.itheima.domain.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

public class Module extends BaseEntity {
	//id
	private String id;
	//parent：自关联
	private Module parent;
	//父亲名字,冗余
	private String parentName;
	//名字
	private String name;
	//层数 1 一级菜单  2 左侧菜单  3 三级按钮
	private Integer layerNum;
	//是否是叶子
	private Integer isLeaf;
	//图标：三级菜单
	private String ico;
	//权限标识
	private String cpermission;
	//路径
	private String curl;	
	//1主菜单/2左侧菜单/3按钮/4链接/5状态 这个字段与layerNum，只需要保留一个就够了
	private Integer ctype;	
	//状态
	private Integer state;
	//belong：从属，冗余
	private String belong;
	//复用标识:冗余
	private String cwhich;
	//引用次数
	private Integer quoteNum;
	//备注
	private String remark;
	//排序号
	private Integer orderNo;
	
	private Set<Role> roles = new HashSet<Role>();
	
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Module getParent() {
		return parent;
	}
	public void setParent(Module parent) {
		this.parent = parent;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLayerNum() {
		return layerNum;
	}
	public void setLayerNum(Integer layerNum) {
		this.layerNum = layerNum;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public String getCpermission() {
		return cpermission;
	}
	public void setCpermission(String cpermission) {
		this.cpermission = cpermission;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getCwhich() {
		return cwhich;
	}
	public void setCwhich(String cwhich) {
		this.cwhich = cwhich;
	}
	public Integer getQuoteNum() {
		return quoteNum;
	}
	public void setQuoteNum(Integer quoteNum) {
		this.quoteNum = quoteNum;
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
