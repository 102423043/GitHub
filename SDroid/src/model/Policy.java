package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Policy {

	@Column(name="id")
	private int id;
	@Column(name="type")
	private String type;
	@Column(name="access")
	private int access;
	@Column(name="source_app")
	private String application; 
	@Column(name="interaction_type")
	private String interactionType;
	@Column(name="action")
	private String action;
	@Column(name="destination_app")
	private String destApplication;
	@Column(name="permission")
	private String permission;
	@Column(name="other_conditions")
	private String condition;
	@Column(name="modify_time")
	private Date modifyTime;
	@Column(name="create_time")
	private Date createTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAccess() {
		return access;
	}
	public void setAccess(int access) {
		this.access = access;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getInteractionType() {
		return interactionType;
	}
	public void setInteractionType(String interactionType) {
		this.interactionType = interactionType;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDestApplication() {
		return destApplication;
	}
	public void setDestApplication(String destApplication) {
		this.destApplication = destApplication;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	


}
