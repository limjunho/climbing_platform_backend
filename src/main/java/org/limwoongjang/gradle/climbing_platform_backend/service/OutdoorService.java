package org.limwoongjang.gradle.climbing_platform_backend.service;

import java.util.List;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getClearCountGroupByLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getOutdoor_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoorLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_location_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_route_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.outdoor_route;

public interface OutdoorService {
  public List<get_outdoor_location_list_dto> getLocationList();

  public List<get_outdoor_location_list_dto> getLocationList_clear(int user_index);

  public List<get_outdoor_location_list_dto> getLocationList_dontClear(int user_index);

  public List<get_outdoorLocation_dto> getLocationInfo(int location_index, int user_index);

  public List<get_image_dto> getLocationImage(int location_index);

  public List<get_outdoor_route_dto> getRouteInfo(int location_index, int user_index, String nickname);

  public List<get_outdoor_route_dto> getRouteInfo_populer(int location_index, int user_index, String nickname);

  public List<get_outdoor_route_dto> getRouteInfo_difficulty(int location_index, int user_index, String nickname);

  public List<get_outdoor_route_dto> getRouteInfo_clear(int location_index, int user_index, String nickname);

  public List<outdoor_route> getRouteInfo_clearByMyself(int user_index);

  public List<get_image_dto> getRouteImage(int route_index);
  
  public String getNickname(int user_index);

  public List<get_outdoorLocation_dto> locationExistCheck(int location_index);

  public List<outdoor_route> routeExistCheck(int route_index);

  public List<getOutdoor_comments_dto> getComments(int route_index);

  public int createComments(Integer idx, int route_index, String nickname, String comment, String record_time);

  public int deleteComments(int idx);

  public Integer getLikes(int route_index, String nickname);

  public int createLikes(Integer idx, int route_index, String nickname, String like_time);

  public int deleteLikes(int idx);

  public Integer getClear(int route_index, int user_index);

  public Integer getClearRouteCount(int user_index);

  public List<getClearCountGroupByLocation_dto> getClearCountGroupByLocation(int user_index);

  public int createClear(Integer idx, int user_index, int route_index, String clear_date);

  public int deleteClear(int idx);

  public String clearDifficultyAverage(int user_index);

  public Integer scoreExistCheck(int user_index, int location_index);

  public int createScore(Integer idx, int user_index, int location_index, float score);

  public int modifyScore(int user_index, int location_index, float score);

  public int deleteScore(int idx, int location_index);
}