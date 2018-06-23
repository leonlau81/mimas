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
 * 测试用表
 * @author leon
 * 
 */
@Entity
@Table(name = "tb_sample")
public class Sample implements Serializable {
	private static final long serialVersionUID = 2615563821358388265l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",length=20,nullable=false)
	private Long id;
	

	@JsonProperty("name")
	@Column(name="name",length=100)
	private String name;
	
	@JsonProperty("amount")
	@Column(name="amount")
	private BigDecimal amount;
	
	@JsonProperty("status")
	@Column(name="status",length=11)
	private Integer status;
	
	@JsonProperty("apply_date")
	@Column(name = "apply_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyDate;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	
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
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Sample() {
	}
	
	public Sample(String name) {
		this.name = name;
		this.status = 1;
		this.amount = new BigDecimal(88.88d);
		this.applyDate = new Date();
		this.createTime = new Date();
	}
	
	public Map<String,Object> toJsonMap(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", this.getId());
		
		return map;
	}
}
