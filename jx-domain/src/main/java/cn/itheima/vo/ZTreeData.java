package cn.itheima.vo;

public class ZTreeData {
	private String id;
	private String pId;
	private String name;
	private boolean checked;
	private boolean open;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPId() {
		return pId;
	}
	public void setPId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public ZTreeData() {
		super();
	}
	public ZTreeData(String id, String pId, String name, boolean checked,
			boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.open = open;
	}
	@Override
	public String toString() {
		return "ZTreeData [id=" + id + ", pId=" + pId + ", name=" + name
				+ ", checked=" + checked + ", open=" + open + "]";
	}
	
}
