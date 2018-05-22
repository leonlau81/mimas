package com.vanseed.mimas.rpc.grpc;

import java.io.IOException;

import com.vanseed.mimas.rpc.grpc.user.FindUserServiceGrpc;
import com.vanseed.mimas.rpc.grpc.user.FindUserServiceImpl;

public class GRpcServer {

    private static int port = 8883;
     private static io.grpc.Server server;

     public void run() {
         FindUserServiceGrpc.FindUserServiceImplBase findeUserServiceImpl = new FindUserServiceImpl();
         server = io.grpc.ServerBuilder.forPort(port).addService(findeUserServiceImpl.bindService())
        		 //.addService(arg0)
        		 .build();
         
         try {
             server.start();
             System.out.println("Server start success on port:" + port);
             server.awaitTermination();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
     
     public static void main(String[] args) {
    	 GRpcServer server = new GRpcServer();
         server.run();
     }
}
