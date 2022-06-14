package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.difficultyAverage_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getClearCountGroupByLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getOutdoor_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoorLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_location_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_route_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.outdoor_route;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OutdoorMapper {
  List<get_outdoor_location_list_dto> getLocationList();

  List<get_outdoor_location_list_dto> getLocationList_clear(@Param("user_index") int user_index);

  List<get_outdoor_location_list_dto> getLocationList_dontClear(@Param("user_index") int user_index);
  
  List<get_outdoorLocation_dto> getLocationInfo(@Param("location_index") int location_index, @Param("user_index") int user_index);

  List<get_image_dto> getLocationImage(@Param("location_index") int location_index);

  List<get_outdoor_route_dto> getRouteInfo(@Param("location_index") int location_index, @Param("user_index") int user_index, @Param("nickname") String nickname);

  List<get_outdoor_route_dto> getRouteInfo_populer(@Param("location_index") int location_index, @Param("user_index") int user_index, @Param("nickname") String nickname);

  List<get_outdoor_route_dto> getRouteInfo_difficulty(@Param("location_index") int location_index, @Param("user_index") int user_index, @Param("nickname") String nickname);

  List<get_outdoor_route_dto> getRouteInfo_clear(@Param("location_index") int location_index, @Param("user_index") int user_index, @Param("nickname") String nickname);

  List<outdoor_route> getRouteInfo_clearByMyself(@Param("user_index") int user_index);

  List<get_image_dto> getRouteImage(@Param("route_index") int route_index);
  
  String getNickname(@Param("user_index") int user_index);

  List<get_outdoorLocation_dto> locationExistCheck(@Param("location_index") int location_index);

  List<outdoor_route> routeExistCheck(@Param("route_index") int route_index);

  List<getOutdoor_comments_dto> getComments(@Param("route_index") int route_index);

  int createComments(@Param("idx") Integer idx, @Param("route_index") int route_index,
      @Param("nickname") String nickname, @Param("comment") String comment, @Param("record_time") String record_time);

  int deleteComments(@Param("idx") int idx);

  Integer getLikes(@Param("route_index") int route_index, @Param("nickname") String nickname);

  int createLikes(@Param("idx") Integer idx, @Param("route_index") int route_index,
      @Param("nickname") String nickname, @Param("like_time") String like_time);

  int deleteLikes(@Param("idx") int idx);

  Integer getClear(@Param("route_index") int route_index, @Param("user_index") int user_index);

  Integer getClearRouteCount(@Param("user_index") int user_index);

  List<getClearCountGroupByLocation_dto> getClearCountGroupByLocation(@Param("user_index") int user_index);

  int createClear(@Param("idx") Integer idx, @Param("user_index") int user_index,
      @Param("route_index") int route_index, @Param("clear_date") String clear_date);

  int deleteClear(@Param("idx") int idx);

  List<difficultyAverage_dto> clearDifficultyAverage(@Param("user_index") int user_index);

  Integer scoreExistCheck(@Param("user_index") int user_index, @Param("location_index") int location_index);

  int createScore(@Param("idx") Integer idx, @Param("user_index") int user_index, @Param("location_index") int location_index, @Param("score") float score);

  int modifyScore(@Param("user_index") int user_index, @Param("location_index") int location_index, @Param("score") float score);

  int deleteScore(@Param("idx") int idx, @Param("location_index") int location_index);
}
