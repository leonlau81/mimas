package com.vanseed.mimas.common.enums;

import com.vanseed.mimas.common.enums.BaseEnum;

public enum ClientType implements BaseEnum
{
	WEB(1,"网站"),
	APP(2,"APP");


	private int index;
	private String name;
	
	private ClientType(int index, String name) {
		this.index = index;
		this.name = name;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public static final ClientType getStatusByIndex(int index){
		ClientType clientType = null;
		for(ClientType client :ClientType.values()){
			if(index == client.getIndex()){
				clientType = client;
			}
		}
		return clientType;
	}
}
