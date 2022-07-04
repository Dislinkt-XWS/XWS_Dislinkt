// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.xwsdislinkt.userservice;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_xwsdislinkt_userservice_FollowersRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_xwsdislinkt_userservice_FollowersRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_xwsdislinkt_userservice_FollowersResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_xwsdislinkt_userservice_FollowersResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_xwsdislinkt_userservice_ApiKeyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_xwsdislinkt_userservice_ApiKeyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_xwsdislinkt_userservice_ApiKeyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_xwsdislinkt_userservice_ApiKeyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_xwsdislinkt_userservice_NotificationRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_xwsdislinkt_userservice_NotificationRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_xwsdislinkt_userservice_NotificationResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_xwsdislinkt_userservice_NotificationResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\022\033com.xwsdislinkt.userservic" +
      "e\"\"\n\020FollowersRequest\022\016\n\006userId\030\001 \001(\t\"&\n" +
      "\021FollowersResponse\022\021\n\tfollowers\030\001 \003(\t\"\037\n" +
      "\rApiKeyRequest\022\016\n\006apiKey\030\001 \001(\t\" \n\016ApiKey" +
      "Response\022\016\n\006userId\030\001 \001(\t\"E\n\023Notification" +
      "Request\022\016\n\006userId\030\001 \001(\t\022\020\n\010senderId\030\002 \001(" +
      "\t\022\014\n\004text\030\003 \001(\t\".\n\024NotificationResponse\022" +
      "\026\n\016notificationId\030\001 \001(\t2\340\002\n\013UserService\022" +
      "l\n\tfollowers\022-.com.xwsdislinkt.userservi" +
      "ce.FollowersRequest\032..com.xwsdislinkt.us" +
      "erservice.FollowersResponse\"\000\022i\n\014findByA" +
      "piKey\022*.com.xwsdislinkt.userservice.ApiK" +
      "eyRequest\032+.com.xwsdislinkt.userservice." +
      "ApiKeyResponse\"\000\022x\n\017addNotification\0220.co" +
      "m.xwsdislinkt.userservice.NotificationRe" +
      "quest\0321.com.xwsdislinkt.userservice.Noti" +
      "ficationResponse\"\000B\037\n\033com.xwsdislinkt.us" +
      "erserviceP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_xwsdislinkt_userservice_FollowersRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_xwsdislinkt_userservice_FollowersRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_xwsdislinkt_userservice_FollowersRequest_descriptor,
        new java.lang.String[] { "UserId", });
    internal_static_com_xwsdislinkt_userservice_FollowersResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_xwsdislinkt_userservice_FollowersResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_xwsdislinkt_userservice_FollowersResponse_descriptor,
        new java.lang.String[] { "Followers", });
    internal_static_com_xwsdislinkt_userservice_ApiKeyRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_xwsdislinkt_userservice_ApiKeyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_xwsdislinkt_userservice_ApiKeyRequest_descriptor,
        new java.lang.String[] { "ApiKey", });
    internal_static_com_xwsdislinkt_userservice_ApiKeyResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_xwsdislinkt_userservice_ApiKeyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_xwsdislinkt_userservice_ApiKeyResponse_descriptor,
        new java.lang.String[] { "UserId", });
    internal_static_com_xwsdislinkt_userservice_NotificationRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_xwsdislinkt_userservice_NotificationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_xwsdislinkt_userservice_NotificationRequest_descriptor,
        new java.lang.String[] { "UserId", "SenderId", "Text", });
    internal_static_com_xwsdislinkt_userservice_NotificationResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_com_xwsdislinkt_userservice_NotificationResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_xwsdislinkt_userservice_NotificationResponse_descriptor,
        new java.lang.String[] { "NotificationId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
