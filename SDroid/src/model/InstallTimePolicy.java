package model;

import java.util.Date;

public class InstallTimePolicy {

	private Integer id;
	private String access;
	private String application;
	private String permission;
	private Condition minVersion;
	private Condition permissionLevel;
	private Date modifyTime;
	private Date createTime;
	
	public InstallTimePolicy(){
		minVersion = new Condition();
		permissionLevel = new Condition();
	}
	

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

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	

	public Condition getMinVersion() {
		return minVersion;
	}


	public void setMinVersion(Condition minVersion) {
		this.minVersion = minVersion;
	}


	public Condition getPermissionLevel() {
		return permissionLevel;
	}


	public void setPermissionLevel(Condition permissionLevel) {
		this.permissionLevel = permissionLevel;
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

