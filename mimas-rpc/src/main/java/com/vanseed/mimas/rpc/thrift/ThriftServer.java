package com.vanseed.mimas.rpc.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.vanseed.mimas.rpc.thrift.user.UserQuery;
import com.vanseed.mimas.rpc.thrift.user.UserQueryImpl;

public class ThriftServer {
	private final static int DEFAULT_PORT = 8884;
	private static TServer server = null;
	public static void main(String[] args){
		try {
			TNonblockingServerSocket socket = new TNonblockingServerSocket(DEFAULT_PORT);
			UserQuery.Processor<UserQuery.Iface> processor = new UserQuery.Processor<UserQuery.Iface>(new UserQueryImpl());
			TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
			arg.protocolFactory(new TBinaryProtocol.Factory());
			arg.transportFactory(new TFramedTransport.Factory());
			arg.processorFactory(new TProcessorFactory(processor));
			server = new TNonblockingServer (arg);
			System.out.println("server starting ...");
			server.serve();			
		}catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
