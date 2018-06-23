/**
 * 
 */
package com.vanseed.mimas.domain.model.acct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 账户表
 * @author leon
 * 
 */
@Entity
@Table(name = "tb_acct_info")
public class AcctInfo implements Serializable {
	private static final long serialVersionUID = 2615563821358388265l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",length=20,nullable=false)
	private Long id;

	@JsonIgnore
	@Column(name = "user_id")
	private Long userId;

	@JsonProperty("acct_type")
	@Column(name="acct_type")
	private Integer acctType;

	@JsonProperty("acct_no")
	@Column(name="acct_no",length=100)
	private String acctNo;

	@JsonProperty("acct_name")
	@Column(name="acct_name",length=100)
	private String acctName;
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getAcctType() {
		return acctType;
	}

	public void setAcctType(Integer acctType) {
		this.acctType = acctType;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
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

}
