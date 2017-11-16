package cn.itheima.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author:			传智播客 java学院	传智袁老师
 * @Company:		http://java.itcast.cn
 */
@XmlRootElement(name="export")
public class ExportVo implements Serializable{

	private Set<ExportProductVo> products = new HashSet<ExportProductVo>();	//报运下的货物

	private String exportId;	  	
	private Date inputDate;				//制单日期
	
	private String shipmentPort;		//装船港	
	private String destinationPort;		//目的港
	private String transportMode;		//船运SEA/空运AIR 运输方式
	private String priceCondition;		//FBO/CIF价格条件
	private Integer boxNums;			//冗余，为委托服务，一个报运的总箱数
	private Double grossWeights;		//冗余，为委托服务，一个报运的总毛重
	private Double measurements;		//冗余，为委托服务，一个报运的总体积
	public Set<ExportProductVo> getProducts() {
		return products;
	}
	public void setProducts(Set<ExportProductVo> products) {
		this.products = products;
	}
	public String getExportId() {
		return exportId;
	}
	public void setExportId(String exportId) {
		this.exportId = exportId;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getShipmentPort() {
		return shipmentPort;
	}
	public void setShipmentPort(String shipmentPort) {
		this.shipmentPort = shipmentPort;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getPriceCondition() {
		return priceCondition;
	}
	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
	}
	public Integer getBoxNums() {
		return boxNums;
	}
	public void setBoxNums(Integer boxNums) {
		this.boxNums = boxNums;
	}
	public Double getGrossWeights() {
		return grossWeights;
	}
	public void setGrossWeights(Double grossWeights) {
		this.grossWeights = grossWeights;
	}
	public Double getMeasurements() {
		return measurements;
	}
	public void setMeasurements(Double measurements) {
		this.measurements = measurements;
	}
            
		
	
}
