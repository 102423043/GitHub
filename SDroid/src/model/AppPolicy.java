package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("AppPolicy")
public class AppPolicy {

	private Integer id;
	private String label;
	private String application;
	private boolean isRead;
	private boolean isWrite;
	private boolean isExecute;
	private String createTime;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public boolean isWrite() {
		return isWrite;
	}
	public void setWrite(boolean isWrite) {
		this.isWrite = isWrite;
	}
	public boolean isExecute() {
		return isExecute;
	}
	public void setExecute(boolean isExecute) {
		this.isExecute = isExecute;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
