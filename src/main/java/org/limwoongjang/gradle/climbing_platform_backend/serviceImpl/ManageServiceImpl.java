package org.limwoongjang.gradle.climbing_platform_backend.serviceImpl;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao.ManageMapper;
import org.limwoongjang.gradle.climbing_platform_backend.service.ManageService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {
  private final ManageMapper ManageMapper;

  @Override
  public boolean login(String user_id, String password) {
    if (ManageMapper.login(user_id, password).isEmpty())
      return false;
    else
      return true;
  }

  @Override
  public int createNotice(Integer notice_index, String header, String body, String post_date, String modify_date,
      Integer isImage) {
    return ManageMapper.createNotice(notice_index, header, body, post_date, modify_date, isImage);
  }

  @Override
  public int checkNoticeIndex(){
    return ManageMapper.checkNoticeIndex();
  }

  @Override
  public int uploadNoticeImage(Integer image_index, int notice_index, String origin_name, Long size, String path) {
    return ManageMapper.uploadNoticeImage(image_index, notice_index, origin_name, size, path);
  }

  @Override
  public int modifyNotice(int notice_index, String header, String body, String modify_date) {
    return ManageMapper.modifyNotice(notice_index, header, body, modify_date);
  }

  @Override
  public int deleteNotice(int notice_index) {
    return ManageMapper.deleteNotice(notice_index);
  }

  @Override
  public int deletePost(int post_index) {
    return ManageMapper.deletePost(post_index);
  }

  @Override
  public int deleteComments(int comment_index, int post_index) {
    return ManageMapper.deleteComments(comment_index, post_index);
  }

  @Override
  public int deleteReplies(int idx, int post_index) {
    return ManageMapper.deleteReplies(idx, post_index);
  }

  @Override
  public int createApproach(Integer location_index, String address_province, String address_city, String address_detail,
      String latitude, String longitude, String name, String description, Integer isImage, Float score) {
    return ManageMapper.createApproach(location_index, address_province, address_city, address_detail, latitude,
        longitude, name, description, isImage, score);
  }

  @Override
  public int checkLocationIndex(){
    return ManageMapper.checkLocationIndex();
  }

  @Override
  public int uploadLocationImage(Integer image_index, int location_index, String origin_name, Long size, String path) {
    return ManageMapper.uploadLocationImage(image_index, location_index, origin_name, size, path);
  }

  @Override
  public int modifyApproach(int location_index, String address_province, String address_city, String address_detail,
      String latitude, String longitude, String name, String description) {
    return ManageMapper.modifyApproach(location_index, address_province, address_city, address_detail, latitude,
        longitude, name, description);
  }

  @Override
  public int deleteApproach(int location_index) {
    return ManageMapper.deleteApproach(location_index);
  }

  @Override
  public int createRoute(Integer route_index, int location_index, String route_name, String bolt_count,
      String difficulty, Integer isImage, int like_count) {
    return ManageMapper.createRoute(route_index, location_index, route_name, bolt_count, difficulty, isImage, like_count);
  }

  @Override
  public int checkRouteIndex(){
    return ManageMapper.checkRouteIndex();
  }

  @Override
  public int uploadRouteImage(Integer image_index, int route_index, String origin_name, Long size, String path) {
    return ManageMapper.uploadRouteImage(image_index, route_index, origin_name, size, path);
  }

  @Override
  public int modifyRoute(int route_index, String route_name, String bolt_count, String difficulty) {
    return ManageMapper.modifyRoute(route_index, route_name, bolt_count, difficulty);
  }

  @Override
  public int deleteRoute(int route_index) {
    return ManageMapper.deleteRoute(route_index);
  }
}