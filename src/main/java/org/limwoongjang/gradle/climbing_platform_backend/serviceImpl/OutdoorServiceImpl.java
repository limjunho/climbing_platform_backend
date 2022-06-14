package org.limwoongjang.gradle.climbing_platform_backend.serviceImpl;

import java.util.List;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao.OutdoorMapper;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.difficultyAverage_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getClearCountGroupByLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getOutdoor_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoorLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_location_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_route_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.outdoor_route;
import org.limwoongjang.gradle.climbing_platform_backend.service.OutdoorService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutdoorServiceImpl implements OutdoorService {
  private final OutdoorMapper outdoorMapper;

  @Override
  public List<get_outdoor_location_list_dto> getLocationList() {
    return outdoorMapper.getLocationList();
  }

  @Override
  public List<get_outdoor_location_list_dto> getLocationList_clear(int user_index) {
    return outdoorMapper.getLocationList_clear(user_index);
  }

  @Override
  public List<get_outdoor_location_list_dto> getLocationList_dontClear(int user_index) {
    List<get_outdoor_location_list_dto> locationList = outdoorMapper.getLocationList();
    List<get_outdoor_location_list_dto> clearLocationList = outdoorMapper.getLocationList_clear(user_index);

    // 전체 암장 리스트와 단 하나라도 클리어한 루트가 있는 암장을 비교하여 단 하나의 루트도 완등하지 않은 암장만 남김
    while (clearLocationList.size() > 0) {
      for (int i = 0; i < locationList.size(); i++) {
        if (clearLocationList.get(0).getLocation_index() == locationList.get(i).getLocation_index()) {
          locationList.remove(i);
          clearLocationList.remove(0);
          break;
        } else {
        }
      }
    }

    return locationList;
  }

  @Override
  public List<get_outdoorLocation_dto> getLocationInfo(int location_index, int user_index) {
    return outdoorMapper.getLocationInfo(location_index, user_index);
  }

  @Override
  public List<get_image_dto> getLocationImage(int location_index) {
    return outdoorMapper.getLocationImage(location_index);
  }

  @Override
  public List<get_outdoor_route_dto> getRouteInfo(int location_index, int user_index, String nickname) {
    return outdoorMapper.getRouteInfo(location_index, user_index, nickname);
  }

  @Override
  public List<get_outdoor_route_dto> getRouteInfo_populer(int location_index, int user_index, String nickname) {
    return outdoorMapper.getRouteInfo_populer(location_index, user_index, nickname);
  }

  @Override
  public List<get_outdoor_route_dto> getRouteInfo_difficulty(int location_index, int user_index, String nickname) {
    return outdoorMapper.getRouteInfo_difficulty(location_index, user_index, nickname);
  }

  @Override
  public List<get_outdoor_route_dto> getRouteInfo_clear(int location_index, int user_index, String nickname) {
    return outdoorMapper.getRouteInfo_clear(location_index, user_index, nickname);
  }

  @Override
  public List<outdoor_route> getRouteInfo_clearByMyself(int user_index) {
    return outdoorMapper.getRouteInfo_clearByMyself(user_index);
  }

  @Override
  public List<get_image_dto> getRouteImage(int route_index) {
    return outdoorMapper.getRouteImage(route_index);
  }
  
  @Override
  public String getNickname(int user_index) {
    return outdoorMapper.getNickname(user_index);
  }

  @Override
  public List<get_outdoorLocation_dto> locationExistCheck(int location_index) {
    return outdoorMapper.locationExistCheck(location_index);
  }

  @Override
  public List<outdoor_route> routeExistCheck(int route_index) {
    return outdoorMapper.routeExistCheck(route_index);
  }

  @Override
  public List<getOutdoor_comments_dto> getComments(int route_index) {
    return outdoorMapper.getComments(route_index);
  }

  @Override
  public int createComments(Integer idx, int route_index, String nickname, String comment, String record_time) {
    return outdoorMapper.createComments(idx, route_index, nickname, comment, record_time);
  }

  @Override
  public int deleteComments(int idx) {
    return outdoorMapper.deleteComments(idx);
  }

  @Override
  public Integer getLikes(int route_index, String nickname) {
    return outdoorMapper.getLikes(route_index, nickname);
  }

  @Override
  public int createLikes(Integer idx, int route_index, String nickname, String like_time) {
    return outdoorMapper.createLikes(idx, route_index, nickname, like_time);
  }

  @Override
  public int deleteLikes(int idx) {
    return outdoorMapper.deleteLikes(idx);
  }

  @Override
  public Integer getClear(int route_index, int user_index) {
    return outdoorMapper.getClear(route_index, user_index);
  }

  @Override
  public Integer getClearRouteCount(int user_index) {
    return outdoorMapper.getClearRouteCount(user_index);
  }

  @Override
  public List<getClearCountGroupByLocation_dto> getClearCountGroupByLocation(int user_index) {
    return outdoorMapper.getClearCountGroupByLocation(user_index);
  }

  @Override
  public int createClear(Integer idx, int user_index, int route_index, String clear_date) {
    return outdoorMapper.createClear(idx, user_index, route_index, clear_date);
  }

  @Override
  public int deleteClear(int idx) {
    return outdoorMapper.deleteClear(idx);
  }

  @Override
  public String clearDifficultyAverage(int user_index) {
    String[] difficulty = { "5.10a", "5.10b", "5.10c", "5.10d", "5.11a", "5.11b", "5.11c", "5.11d", "5.12a", "5.12b",
        "5.12c", "5.12d", "5.13a", "5.13b", "5.13c", "5.13d", "5.14a", "5.14b", "5.14c", "5.14d", "5.15a", "5.15b",
        "5.15c" };
    List<difficultyAverage_dto> result = outdoorMapper.clearDifficultyAverage(user_index);

    if(result.isEmpty()){
      return null;
    }

    double Average = 0;
    String tempString = "";
    char alpha = ' ';
    int ascii = 0;

    for (int i = 0; i < result.size(); i++) {
      // 5.10a인 경우 a를 분리해 ascii값으로 캐스팅
      tempString = result.get(i).getDifficulty().toString().substring(4);
      alpha = tempString.charAt(0);
      ascii = alpha;

      tempString = result.get(i).getDifficulty().toString().substring(2, 4);
      switch (Integer.parseInt(tempString)) {
        case 10:
          Average += ascii - 96;
          break;

        case 11:
          Average += ascii - 92;
          break;

        case 12:
          Average += ascii - 88;
          break;

        case 13:
          Average += ascii - 84;
      }

    }
    // 평균 계산 후 반올림 처리
    Average /= result.size();
    Average = Math.round(Average);

    return difficulty[(int) Average - 1];
  }

  @Override
  public Integer scoreExistCheck(int user_index, int location_index) {
    return outdoorMapper.scoreExistCheck(user_index, location_index);
  }

  @Override
  public int createScore(Integer idx, int user_index, int location_index, float score) {
    return outdoorMapper.createScore(idx, user_index, location_index, score);
  }

  @Override
  public int modifyScore(int user_index, int location_index, float score) {
    return outdoorMapper.modifyScore(user_index, location_index, score);
  }

  @Override
  public int deleteScore(int idx, int location_index) {
    return outdoorMapper.deleteScore(idx, location_index);
  }
}