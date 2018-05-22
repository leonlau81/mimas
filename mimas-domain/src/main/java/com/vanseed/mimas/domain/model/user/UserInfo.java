/**
 * 
 */
package com.vanseed.mimas.domain.model.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 用户表
 * @author leon
 * 
 */
@Entity
@Table(name = "tb_user_info")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 2615563821358388265l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",length=20,nullable=false)
	private Long id;	

	@JsonProperty("name")
	@Column(name="name",length=100)
	private String name;
	
	@JsonProperty("credentials_salt")
	@Column(name="credentials_salt")
	private String credentialsSalt;
	
	@JsonProperty("password")
	@Column(name="password",length=100)
	private String password;
	
	@JsonProperty("status")
	@Column(name="status",length=11)
	private Integer status;
	
	@JsonIgnore
	@Column(name = "create_id")
	private Long createId;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	@JsonIgnore
	@Column(name = "update_id")
	private Long updateId;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredentialsSalt() {
		return credentialsSalt;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String,Object> toJsonMap(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", this.getId());
		
		return map;
	}
}
