/**
 * 
 */
package com.vanseed.mimas.common.mvc.support;

/**
 * @author kunrey
 * 
 */
public class CombResponseHeader extends CombHeader {
	private Long duration;

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "protocol:" + super.getProtocolId() +";c-time:" + super.getcTime()
				+ ";s-time:" + super.getsTime()
				+ ";s-ip:" + super.getsIp()
				+ ";duration:" + this.getDuration() 
				+ ";client-version:" + super.getClientVersion() 
				+ ";";
	}
}
