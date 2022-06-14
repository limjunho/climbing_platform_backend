package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.user_account;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ManageMapper {
  List<user_account> login(@Param("user_id") String user_id, @Param("password") String password);

  int createNotice(@Param("notice_index") Integer notice_index, @Param("header") String header,
      @Param("body") String body,
      @Param("post_date") String post_date,
      @Param("modify_date") String modify_date,
      @Param("isImage") Integer isImage);

  int checkNoticeIndex();

  int uploadNoticeImage(@Param("image_index") Integer image_index, @Param("notice_index") int notice_index,
      @Param("origin_name") String origin_name, @Param("size") Long size,
      @Param("path") String path);

  int modifyNotice(@Param("notice_index") int notice_index, @Param("header") String header,
      @Param("body") String body,
      @Param("modify_date") String modify_date);

  int deleteNotice(@Param("notice_index") int notice_index);

  int deletePost(@Param("post_index") int post_index);

  int deleteComments(@Param("comment_index") int comment_index, @Param("post_index") int post_index);

  int deleteReplies(@Param("idx") int idx, @Param("post_index") int post_index);

  int createApproach(@Param("location_index") Integer location_index,
      @Param("address_province") String address_province, @Param("address_city") String address_city,
      @Param("address_detail") String address_detail, @Param("latitude") String latitude,
      @Param("longitude") String longitude, @Param("name") String name, @Param("description") String description,
      @Param("isImage") Integer isImage, @Param("score") Float score);

  int checkLocationIndex();

  int uploadLocationImage(@Param("image_index") Integer image_index, @Param("location_index") int location_index,
      @Param("origin_name") String origin_name, @Param("size") Long size,
      @Param("path") String path);

  int modifyApproach(@Param("location_index") int location_index,
      @Param("address_province") String address_province, @Param("address_city") String address_city,
      @Param("address_detail") String address_detail, @Param("latitude") String latitude,
      @Param("longitude") String longitude, @Param("name") String name, @Param("description") String description);

  int deleteApproach(@Param("location_index") int location_index);

  int createRoute(@Param("route_index") Integer route_index, @Param("location_index") int location_index,
      @Param("route_name") String route_name, @Param("bolt_count") String bolt_count,
      @Param("difficulty") String difficulty, @Param("isImage") Integer isImage,
      @Param("like_count") Integer like_count);

  int checkRouteIndex();

  int uploadRouteImage(@Param("image_index") Integer image_index, @Param("route_index") int route_index,
      @Param("origin_name") String origin_name, @Param("size") Long size,
      @Param("path") String path);

  int modifyRoute(@Param("route_index") int route_index, @Param("route_name") String route_name,
      @Param("bolt_count") String bolt_count, @Param("difficulty") String difficulty);

  int deleteRoute(@Param("route_index") int route_index);
}
