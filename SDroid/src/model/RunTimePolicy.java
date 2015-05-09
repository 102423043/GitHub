package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RunTimePolicy")
public class RunTimePolicy {

	private Integer id;
	private String access;
	private String label;
	private String sourceApplication;
	private String destinationApplication;
	private String createTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getSourceApplication() {
		return sourceApplication;
	}
	public void setSourceApplication(String sourceApplication) {
		this.sourceApplication = sourceApplication;
	}
	public String getDestinationApplication() {
		return destinationApplication;
	}
	public void setDestinationApplication(String destinationApplication) {
		this.destinationApplication = destinationApplication;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
