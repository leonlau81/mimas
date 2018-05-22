package com.vanseed.mimas.rpc.grpc;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.vanseed.mimas.rpc.grpc.user.FindUserServiceGrpc;
import com.vanseed.mimas.rpc.grpc.user.FindUserServiceGrpc.FindUserServiceBlockingStub;
import com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest;
import com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GRpcClient {

    public static void main( String[] args ) throws FileNotFoundException, IOException{
    	FindUserRequest request = FindUserRequest.newBuilder().setUserId("1").setRequestId("123").build();
        ManagedChannel channel = ManagedChannelBuilder
                  .forAddress("localhost", 8883)
                  .usePlaintext(true)
                  .build();
        FindUserServiceBlockingStub stub = FindUserServiceGrpc.newBlockingStub(channel);
        for (int j = 0; j < 10; j++) {
        	long start = System.currentTimeMillis();
        	for(int i=0; i<10; i++){
        		UserInfoResponse rsp = stub.find(request);
        		System.out.println(rsp);
        	}
        	System.out.println(System.currentTimeMillis() - start + " MS");
        }
        channel.shutdown();
    }
}
