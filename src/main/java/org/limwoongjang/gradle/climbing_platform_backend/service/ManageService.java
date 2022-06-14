package org.limwoongjang.gradle.climbing_platform_backend.service;

public interface ManageService {

  public boolean login(String user_id, String password);

  public int createNotice(Integer notice_index, String header, String body, String post_date, String modify_date,
      Integer isImage);

  public int checkNoticeIndex();

  public int uploadNoticeImage(Integer image_index, int notice_index, String origin_name, Long size, String path);

  public int modifyNotice(int notice_index, String header, String body,
      String modify_date);

  public int deleteNotice(int notice_index);

  public int deletePost(int post_index);

  public int deleteComments(int comment_index, int post_index);

  public int deleteReplies(int idx, int post_index);

  public int createApproach(Integer location_index, String address_province, String address_city,
      String address_detail, String latitude,
      String longitude, String name, String description, Integer isImage, Float score);

  public int checkLocationIndex();

  public int uploadLocationImage(Integer image_index, int location_index, String origin_name, Long size, String path);

  public int modifyApproach(int location_index, String address_province, String address_city,
      String address_detail, String latitude,
      String longitude, String name, String description);

  public int deleteApproach(int location_index);

  public int createRoute(Integer route_index, int location_index, String route_name, String bolt_count,
      String difficulty, Integer isImage, int like_count);

  public int checkRouteIndex();

  public int uploadRouteImage(Integer image_index, int route_index, String origin_name, Long size, String path);

  public int modifyRoute(int route_index, String route_name, String bolt_count, String difficulty);

  public int deleteRoute(int route_index);
}