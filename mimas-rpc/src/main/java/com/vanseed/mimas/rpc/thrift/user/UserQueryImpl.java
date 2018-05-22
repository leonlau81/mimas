package com.vanseed.mimas.rpc.thrift.user;

public class UserQueryImpl implements UserQuery.Iface{
	
	public QryResult qryUser(QryRequest request) throws org.apache.thrift.TException{
		QryResult result = new QryResult();
		result.setCode(1);
		result.setUserMobile("13900138000");
		result.setUserName("leon");
			
		return result;
	}

}
