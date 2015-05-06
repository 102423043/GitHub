package model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import jxl.write.DateTime;


@Entity
public class Application {

	@Column(name="id")
	private int id;
	@Column(name="app_label")
	private String appLabel;
	@Column(name="pk_name")
	private String pkName;
	@Column(name="version")
	private String version;
	@Column(name="apk_file")
	private String apkFile;
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
	public String getAppLabel() {
		return appLabel;
	}
	public void setAppLabel(String appLabel) {
		this.appLabel = appLabel;
	}
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApkFile() {
		return apkFile;
	}
	public void setApkFile(String apkFile) {
		this.apkFile = apkFile;
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
