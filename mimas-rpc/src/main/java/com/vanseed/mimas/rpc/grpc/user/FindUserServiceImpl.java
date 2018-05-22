package com.vanseed.mimas.rpc.grpc.user;

import com.vanseed.mimas.rpc.grpc.user.FindUserServiceGrpc.FindUserServiceImplBase;
import com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse;

public class FindUserServiceImpl extends FindUserServiceImplBase {
	@Override
	public void find(com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest request,
	        io.grpc.stub.StreamObserver<com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> responseObserver) {
	      
		System.out.println("qry " + request.getUserId());
		UserInfoResponse response = UserInfoResponse.newBuilder().setRc(1).setUserMobile("leon").setUserId(1).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
		
	 }
}
