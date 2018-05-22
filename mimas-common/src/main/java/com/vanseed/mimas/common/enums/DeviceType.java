package com.vanseed.mimas.common.enums;

import com.vanseed.mimas.common.enums.BaseEnum;

public enum DeviceType implements BaseEnum{

	Other(0,"other"),
	ANDROID(1,"android"),
	IOS(2,"ios");

	
	private int index;
	private String name;
	
	private DeviceType(int index, String name) {
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
	
	public static final DeviceType getStatusByIndex(int index){
		DeviceType status = null;
		for(DeviceType sign :DeviceType.values()){
			if(index == sign.getIndex()){
				status = sign;
			}
		}
		return status;
	}
}
