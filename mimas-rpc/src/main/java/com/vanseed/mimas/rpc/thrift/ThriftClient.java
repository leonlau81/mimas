package com.vanseed.mimas.rpc.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.vanseed.mimas.rpc.thrift.user.QryRequest;
import com.vanseed.mimas.rpc.thrift.user.QryResult;
import com.vanseed.mimas.rpc.thrift.user.UserQuery;

public class ThriftClient {
	
	public static void main(String[] args){
		TTransport tTransport = null;
		try {
			tTransport = getTTransport();
			TProtocol protocol = new TBinaryProtocol(tTransport);
			UserQuery.Client client = new UserQuery.Client(protocol);
			
			QryRequest req = new QryRequest();
			req.setUserId(1);
			QryResult result = client.qryUser(req);
			System.out.println("code="+result.code+" msg="+result.userName + " mobile="+result.userMobile);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(tTransport!=null && tTransport.isOpen()) {
				tTransport.close();
			}
		}
	}
	
	private static TTransport getTTransport() throws Exception{
		try{
			TTransport tTransport = getTTransport("127.0.0.1", 8884, 5000);
			if(!tTransport.isOpen()){
				tTransport.open();
			}
			return tTransport;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private static TTransport getTTransport(String host, int port, int timeout) {
		final TSocket tSocket = new TSocket(host, port, timeout);
		final TTransport transport = new TFramedTransport(tSocket);
		return transport;
	}
}
