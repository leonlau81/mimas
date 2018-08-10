/**
 * 
 */
package com.vanseed.mimas.domain.enums.user;

import com.vanseed.mimas.common.enums.BaseEnum;

/**
 * @author leon
 *
 */
public enum UserStatus implements BaseEnum{
	DISABLE(-1,"禁用"),
    LOCK(0,"锁定"),
    NORMAL(1,"正常");
	
	private int index;
	private String name;

	private UserStatus(int index, String name) {
		this.index = index;
		this.name = name;
	}
	@Override
	public int getIndex() {
		return index;
	}
	@Override
	public String getName() {
		return name;
	}
	
	public static final UserStatus getStatusByIndex(int index){
		UserStatus status = null;
		for(UserStatus userStatus :UserStatus.values()){
			if(index == userStatus.getIndex()){
				status = userStatus;
			}
		}
		return status;
	}
	
}
