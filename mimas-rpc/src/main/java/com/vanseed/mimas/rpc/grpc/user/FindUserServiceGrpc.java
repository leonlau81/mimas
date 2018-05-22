package com.vanseed.mimas.rpc.grpc.user;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 **
 * 用户查询服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.10.1)",
    comments = "Source: UserQuery.proto")
public final class FindUserServiceGrpc {

  private FindUserServiceGrpc() {}

  public static final String SERVICE_NAME = "userService.FindUserService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getFindMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest,
      com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> METHOD_FIND = getFindMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest,
      com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> getFindMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest,
      com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> getFindMethod() {
    return getFindMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest,
      com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> getFindMethodHelper() {
    io.grpc.MethodDescriptor<com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest, com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> getFindMethod;
    if ((getFindMethod = FindUserServiceGrpc.getFindMethod) == null) {
      synchronized (FindUserServiceGrpc.class) {
        if ((getFindMethod = FindUserServiceGrpc.getFindMethod) == null) {
          FindUserServiceGrpc.getFindMethod = getFindMethod = 
              io.grpc.MethodDescriptor.<com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest, com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "userService.FindUserService", "find"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FindUserServiceMethodDescriptorSupplier("find"))
                  .build();
          }
        }
     }
     return getFindMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FindUserServiceStub newStub(io.grpc.Channel channel) {
    return new FindUserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FindUserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FindUserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FindUserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FindUserServiceFutureStub(channel);
  }

  /**
   * <pre>
   **
   * 用户查询服务
   * </pre>
   */
  public static abstract class FindUserServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *用户查询方法
     * </pre>
     */
    public void find(com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest request,
        io.grpc.stub.StreamObserver<com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFindMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest,
                com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse>(
                  this, METHODID_FIND)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * 用户查询服务
   * </pre>
   */
  public static final class FindUserServiceStub extends io.grpc.stub.AbstractStub<FindUserServiceStub> {
    private FindUserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FindUserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FindUserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FindUserServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *用户查询方法
     * </pre>
     */
    public void find(com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest request,
        io.grpc.stub.StreamObserver<com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * 用户查询服务
   * </pre>
   */
  public static final class FindUserServiceBlockingStub extends io.grpc.stub.AbstractStub<FindUserServiceBlockingStub> {
    private FindUserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FindUserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FindUserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FindUserServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *用户查询方法
     * </pre>
     */
    public com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse find(com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * 用户查询服务
   * </pre>
   */
  public static final class FindUserServiceFutureStub extends io.grpc.stub.AbstractStub<FindUserServiceFutureStub> {
    private FindUserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FindUserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FindUserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FindUserServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *用户查询方法
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse> find(
        com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FindUserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FindUserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND:
          serviceImpl.find((com.vanseed.mimas.rpc.grpc.user.UserProto.FindUserRequest) request,
              (io.grpc.stub.StreamObserver<com.vanseed.mimas.rpc.grpc.user.UserProto.UserInfoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FindUserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FindUserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.vanseed.mimas.rpc.grpc.user.UserProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FindUserService");
    }
  }

  private static final class FindUserServiceFileDescriptorSupplier
      extends FindUserServiceBaseDescriptorSupplier {
    FindUserServiceFileDescriptorSupplier() {}
  }

  private static final class FindUserServiceMethodDescriptorSupplier
      extends FindUserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FindUserServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FindUserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FindUserServiceFileDescriptorSupplier())
              .addMethod(getFindMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
