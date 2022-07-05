package com.xwsdislinkt.userservice;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.35.0)",
    comments = "Source: user.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "com.xwsdislinkt.userservice.UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.FollowersRequest,
      com.xwsdislinkt.userservice.FollowersResponse> getFollowersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "followers",
      requestType = com.xwsdislinkt.userservice.FollowersRequest.class,
      responseType = com.xwsdislinkt.userservice.FollowersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.FollowersRequest,
      com.xwsdislinkt.userservice.FollowersResponse> getFollowersMethod() {
    io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.FollowersRequest, com.xwsdislinkt.userservice.FollowersResponse> getFollowersMethod;
    if ((getFollowersMethod = UserServiceGrpc.getFollowersMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFollowersMethod = UserServiceGrpc.getFollowersMethod) == null) {
          UserServiceGrpc.getFollowersMethod = getFollowersMethod =
              io.grpc.MethodDescriptor.<com.xwsdislinkt.userservice.FollowersRequest, com.xwsdislinkt.userservice.FollowersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "followers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.FollowersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.FollowersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("followers"))
              .build();
        }
      }
    }
    return getFollowersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.ApiKeyRequest,
      com.xwsdislinkt.userservice.ApiKeyResponse> getFindByApiKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findByApiKey",
      requestType = com.xwsdislinkt.userservice.ApiKeyRequest.class,
      responseType = com.xwsdislinkt.userservice.ApiKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.ApiKeyRequest,
      com.xwsdislinkt.userservice.ApiKeyResponse> getFindByApiKeyMethod() {
    io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.ApiKeyRequest, com.xwsdislinkt.userservice.ApiKeyResponse> getFindByApiKeyMethod;
    if ((getFindByApiKeyMethod = UserServiceGrpc.getFindByApiKeyMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFindByApiKeyMethod = UserServiceGrpc.getFindByApiKeyMethod) == null) {
          UserServiceGrpc.getFindByApiKeyMethod = getFindByApiKeyMethod =
              io.grpc.MethodDescriptor.<com.xwsdislinkt.userservice.ApiKeyRequest, com.xwsdislinkt.userservice.ApiKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findByApiKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.ApiKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.ApiKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("findByApiKey"))
              .build();
        }
      }
    }
    return getFindByApiKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.NotificationRequest,
      com.xwsdislinkt.userservice.NotificationResponse> getAddNotificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addNotification",
      requestType = com.xwsdislinkt.userservice.NotificationRequest.class,
      responseType = com.xwsdislinkt.userservice.NotificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.NotificationRequest,
      com.xwsdislinkt.userservice.NotificationResponse> getAddNotificationMethod() {
    io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.NotificationRequest, com.xwsdislinkt.userservice.NotificationResponse> getAddNotificationMethod;
    if ((getAddNotificationMethod = UserServiceGrpc.getAddNotificationMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getAddNotificationMethod = UserServiceGrpc.getAddNotificationMethod) == null) {
          UserServiceGrpc.getAddNotificationMethod = getAddNotificationMethod =
              io.grpc.MethodDescriptor.<com.xwsdislinkt.userservice.NotificationRequest, com.xwsdislinkt.userservice.NotificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addNotification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.NotificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.NotificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("addNotification"))
              .build();
        }
      }
    }
    return getAddNotificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.UsersFollowersRequest,
      com.xwsdislinkt.userservice.UsersFollowersResponse> getUsersFollowersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "usersFollowers",
      requestType = com.xwsdislinkt.userservice.UsersFollowersRequest.class,
      responseType = com.xwsdislinkt.userservice.UsersFollowersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.UsersFollowersRequest,
      com.xwsdislinkt.userservice.UsersFollowersResponse> getUsersFollowersMethod() {
    io.grpc.MethodDescriptor<com.xwsdislinkt.userservice.UsersFollowersRequest, com.xwsdislinkt.userservice.UsersFollowersResponse> getUsersFollowersMethod;
    if ((getUsersFollowersMethod = UserServiceGrpc.getUsersFollowersMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getUsersFollowersMethod = UserServiceGrpc.getUsersFollowersMethod) == null) {
          UserServiceGrpc.getUsersFollowersMethod = getUsersFollowersMethod =
              io.grpc.MethodDescriptor.<com.xwsdislinkt.userservice.UsersFollowersRequest, com.xwsdislinkt.userservice.UsersFollowersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "usersFollowers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.UsersFollowersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xwsdislinkt.userservice.UsersFollowersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("usersFollowers"))
              .build();
        }
      }
    }
    return getUsersFollowersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void followers(com.xwsdislinkt.userservice.FollowersRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.FollowersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFollowersMethod(), responseObserver);
    }

    /**
     */
    public void findByApiKey(com.xwsdislinkt.userservice.ApiKeyRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.ApiKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindByApiKeyMethod(), responseObserver);
    }

    /**
     */
    public void addNotification(com.xwsdislinkt.userservice.NotificationRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.NotificationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddNotificationMethod(), responseObserver);
    }

    /**
     */
    public void usersFollowers(com.xwsdislinkt.userservice.UsersFollowersRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.UsersFollowersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUsersFollowersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFollowersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xwsdislinkt.userservice.FollowersRequest,
                com.xwsdislinkt.userservice.FollowersResponse>(
                  this, METHODID_FOLLOWERS)))
          .addMethod(
            getFindByApiKeyMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xwsdislinkt.userservice.ApiKeyRequest,
                com.xwsdislinkt.userservice.ApiKeyResponse>(
                  this, METHODID_FIND_BY_API_KEY)))
          .addMethod(
            getAddNotificationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xwsdislinkt.userservice.NotificationRequest,
                com.xwsdislinkt.userservice.NotificationResponse>(
                  this, METHODID_ADD_NOTIFICATION)))
          .addMethod(
            getUsersFollowersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xwsdislinkt.userservice.UsersFollowersRequest,
                com.xwsdislinkt.userservice.UsersFollowersResponse>(
                  this, METHODID_USERS_FOLLOWERS)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void followers(com.xwsdislinkt.userservice.FollowersRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.FollowersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFollowersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findByApiKey(com.xwsdislinkt.userservice.ApiKeyRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.ApiKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindByApiKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addNotification(com.xwsdislinkt.userservice.NotificationRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.NotificationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddNotificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void usersFollowers(com.xwsdislinkt.userservice.UsersFollowersRequest request,
        io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.UsersFollowersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUsersFollowersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xwsdislinkt.userservice.FollowersResponse followers(com.xwsdislinkt.userservice.FollowersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFollowersMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xwsdislinkt.userservice.ApiKeyResponse findByApiKey(com.xwsdislinkt.userservice.ApiKeyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindByApiKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xwsdislinkt.userservice.NotificationResponse addNotification(com.xwsdislinkt.userservice.NotificationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddNotificationMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xwsdislinkt.userservice.UsersFollowersResponse usersFollowers(com.xwsdislinkt.userservice.UsersFollowersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUsersFollowersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xwsdislinkt.userservice.FollowersResponse> followers(
        com.xwsdislinkt.userservice.FollowersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFollowersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xwsdislinkt.userservice.ApiKeyResponse> findByApiKey(
        com.xwsdislinkt.userservice.ApiKeyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindByApiKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xwsdislinkt.userservice.NotificationResponse> addNotification(
        com.xwsdislinkt.userservice.NotificationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddNotificationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xwsdislinkt.userservice.UsersFollowersResponse> usersFollowers(
        com.xwsdislinkt.userservice.UsersFollowersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUsersFollowersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FOLLOWERS = 0;
  private static final int METHODID_FIND_BY_API_KEY = 1;
  private static final int METHODID_ADD_NOTIFICATION = 2;
  private static final int METHODID_USERS_FOLLOWERS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FOLLOWERS:
          serviceImpl.followers((com.xwsdislinkt.userservice.FollowersRequest) request,
              (io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.FollowersResponse>) responseObserver);
          break;
        case METHODID_FIND_BY_API_KEY:
          serviceImpl.findByApiKey((com.xwsdislinkt.userservice.ApiKeyRequest) request,
              (io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.ApiKeyResponse>) responseObserver);
          break;
        case METHODID_ADD_NOTIFICATION:
          serviceImpl.addNotification((com.xwsdislinkt.userservice.NotificationRequest) request,
              (io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.NotificationResponse>) responseObserver);
          break;
        case METHODID_USERS_FOLLOWERS:
          serviceImpl.usersFollowers((com.xwsdislinkt.userservice.UsersFollowersRequest) request,
              (io.grpc.stub.StreamObserver<com.xwsdislinkt.userservice.UsersFollowersResponse>) responseObserver);
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

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xwsdislinkt.userservice.User.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getFollowersMethod())
              .addMethod(getFindByApiKeyMethod())
              .addMethod(getAddNotificationMethod())
              .addMethod(getUsersFollowersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
