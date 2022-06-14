package org.limwoongjang.gradle.climbing_platform_backend.controller.Outdoor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.create_outdoor_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.create_outdoor_likes_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.create_outdoor_score_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.create_user_clearlist_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.delete_outdoor_score_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getClearCountGroupByLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.getOutdoor_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoorLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_location_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoor_route_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.outdoor_route;
import org.limwoongjang.gradle.climbing_platform_backend.model.http.StatusCode;
import org.limwoongjang.gradle.climbing_platform_backend.service.OutdoorService;
import org.limwoongjang.gradle.climbing_platform_backend.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/outdoor")
@RestController
public class OutdoorController {
  private final OutdoorService outdoorService;
  private final UserService userService;

  @Operation(summary = "지도에 표시할 암장 리스트 조회 API")
  @GetMapping("/location-list")
  public List<get_outdoor_location_list_dto> getLocationList(HttpServletResponse res) {
    List<get_outdoor_location_list_dto> result = outdoorService.getLocationList();
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "완등한 문제가 있는 암장 리스트 조회 API")
  @GetMapping("/location-list/clear")
  public List<get_outdoor_location_list_dto> getLocationList_clear(@RequestParam int user_index,
      HttpServletResponse res) {
    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<get_outdoor_location_list_dto> result = outdoorService.getLocationList_clear(user_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "완등한 문제가 없는 암장 리스트 조회 API")
  @GetMapping("/location-list/dontClear")
  public List<get_outdoor_location_list_dto> getLocationList_dontClear(@RequestParam int user_index,
      HttpServletResponse res) {
    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<get_outdoor_location_list_dto> result = outdoorService.getLocationList_dontClear(user_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "암장 정보 조회 API")
  @GetMapping("/location")
  public List<get_outdoorLocation_dto> getLocationInfo(@RequestParam int location_index, int user_index,
      HttpServletResponse res) {
    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<get_outdoorLocation_dto> result = outdoorService.getLocationInfo(location_index, user_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "암장 이미지 조회 API")
  @GetMapping("/locationImage")
  public List<get_image_dto> getLocationImage(@RequestParam int location_index, HttpServletResponse res) {

    List<get_image_dto> result = outdoorService.getLocationImage(location_index);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "루트 조회 API")
  @GetMapping("/route")
  public List<get_outdoor_route_dto> getRouteInfo(@RequestParam int location_index, int user_index,
      HttpServletResponse res) {
    // comments exist check -> parent table constraint check
    List<get_outdoorLocation_dto> commentConstraintCheck = outdoorService.locationExistCheck(location_index);
    if (commentConstraintCheck.isEmpty()) {
      // 존재하지 않는 암장의 루트를 조회하는 경우
      res.setStatus(StatusCode.notFound);
      return null;
    }

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    // nickname을 parameter로 받을 경우 user_index와 nickname이 동일한 유저인지 확인필요
    // user_index로 nickname을 조회하는것이 예외를 줄일 수 있는 쉬운방법
    String nickname = outdoorService.getNickname(user_index);

    List<get_outdoor_route_dto> result = outdoorService.getRouteInfo(location_index, user_index, nickname);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "인기순 루트 조회 API")
  @GetMapping("/route/populer")
  public List<get_outdoor_route_dto> getRouteInfo_populer(@RequestParam int location_index, int user_index,
      HttpServletResponse res) {
    // comments exist check -> parent table constraint check
    List<get_outdoorLocation_dto> commentConstraintCheck = outdoorService.locationExistCheck(location_index);
    if (commentConstraintCheck.isEmpty()) {
      // 존재하지 않는 암장의 루트를 조회하는 경우
      res.setStatus(StatusCode.notFound);
      return null;
    }

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    // nickname을 parameter로 받을 경우 user_index와 nickname이 동일한 유저인지 확인필요
    // user_index로 nickname을 조회하는것이 예외를 줄일 수 있는 쉬운방법
    String nickname = outdoorService.getNickname(user_index);

    List<get_outdoor_route_dto> result = outdoorService.getRouteInfo_populer(location_index, user_index, nickname);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "난이도순 루트 조회 API")
  @GetMapping("/route/difficulty")
  public List<get_outdoor_route_dto> getRouteInfo_difficulty(@RequestParam int location_index, int user_index,
      HttpServletResponse res) {
    // comments exist check -> parent table constraint check
    List<get_outdoorLocation_dto> commentConstraintCheck = outdoorService.locationExistCheck(location_index);
    if (commentConstraintCheck.isEmpty()) {
      // 존재하지 않는 암장의 루트를 조회하는 경우
      res.setStatus(StatusCode.notFound);
      return null;
    }

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    // nickname을 parameter로 받을 경우 user_index와 nickname이 동일한 유저인지 확인필요
    // user_index로 nickname을 조회하는것이 예외를 줄일 수 있는 쉬운방법
    String nickname = outdoorService.getNickname(user_index);

    List<get_outdoor_route_dto> result = outdoorService.getRouteInfo_difficulty(location_index, user_index, nickname);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "완등한 루트 조회 API")
  @GetMapping("/route/clear")
  public List<get_outdoor_route_dto> getRouteInfo_clear(@RequestParam int location_index, int user_index,
      HttpServletResponse res) {
    // comments exist check -> parent table constraint check
    List<get_outdoorLocation_dto> commentConstraintCheck = outdoorService.locationExistCheck(location_index);
    if (commentConstraintCheck.isEmpty()) {
      // 존재하지 않는 암장의 루트를 조회하는 경우
      res.setStatus(StatusCode.notFound);
      return null;
    }

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    // nickname을 parameter로 받을 경우 user_index와 nickname이 동일한 유저인지 확인필요
    // user_index로 nickname을 조회하는것이 예외를 줄일 수 있는 쉬운방법
    String nickname = outdoorService.getNickname(user_index);

    List<get_outdoor_route_dto> result = outdoorService.getRouteInfo_clear(location_index, user_index, nickname);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "유저가 완등한 전체 루트 조회 API")
  @GetMapping("/route/clear-all")
  public List<outdoor_route> getRouteInfo_clearByMyself(@RequestParam int user_index, HttpServletResponse res) {
    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<outdoor_route> result = outdoorService.getRouteInfo_clearByMyself(user_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "루트 이미지 조회 API")
  @GetMapping("/routeImage")
  public List<get_image_dto> getRouteImage(@RequestParam int route_index, HttpServletResponse res) {

    List<get_image_dto> result = outdoorService.getRouteImage(route_index);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "완등한 루트 난이도 평균 API")
  @GetMapping("/route/diffAVG")
  public String clearDifficultyAverage(@RequestParam int user_index, HttpServletResponse res) {
    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    String result = outdoorService.clearDifficultyAverage(user_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "해당 유저가 완등한 루트 개수 API")
  @GetMapping("/route/count")
  public Integer getClearRouteCount(@RequestParam int user_index, HttpServletResponse res) {
    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    Integer result = outdoorService.getClearRouteCount(user_index);
    if (result != null) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "해당 유저가 완등한 암벽장 별 루트 개수 API")
  @GetMapping("/route/countGroupByLocation")
  public List<getClearCountGroupByLocation_dto> getClearCountGroupByLocation(@RequestParam int user_index,
      HttpServletResponse res) {
    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<getClearCountGroupByLocation_dto> result = outdoorService.getClearCountGroupByLocation(user_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "루트에 대한 댓글 조회 API")
  @GetMapping("/route/comments")
  public List<getOutdoor_comments_dto> getComments(@RequestParam int route_index, HttpServletResponse res) {
    // route exist check -> parent table constraint check
    List<outdoor_route> routeConstraintCheck = outdoorService.routeExistCheck(route_index);
    if (routeConstraintCheck.isEmpty()) {
      // 존재하지 않는 루트의 댓글을 조회하는 경우
      res.setStatus(StatusCode.notFound);
      return null;
    }

    List<getOutdoor_comments_dto> result = outdoorService.getComments(route_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "루트에 대한 댓글 작성 API")
  @PostMapping("/comments")
  public int createComments(@RequestBody create_outdoor_comments_dto reqbody, HttpServletResponse res) {
    int route_index = reqbody.getRoute_index();
    String nickname = reqbody.getNickname();
    String comment = reqbody.getComment();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date record_time = new Date();

    // null check
    if (nickname == null || comment == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (nickname.isEmpty() || comment.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // nickname exist check -> parent table constraint check
    boolean constraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!constraintCheck) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // route exist check -> parent table constraint check
    List<outdoor_route> routeConstraintCheck = outdoorService.routeExistCheck(route_index);
    if (routeConstraintCheck.isEmpty()) {
      // 존재하지 않는 루트에 댓글을 다는 경우
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    int result = outdoorService.createComments(null, route_index, nickname, comment, format1.format(record_time));
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "루트에 대한 댓글 삭제 API")
  @DeleteMapping("/comments")
  public int deleteComments(@RequestParam int idx, HttpServletResponse res) {
    int result = outdoorService.deleteComments(idx);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "루트 좋아요 API")
  @PostMapping("/like")
  public int createLikes(@RequestBody create_outdoor_likes_dto reqbody, HttpServletResponse res) {
    int route_index = reqbody.getRoute_index();
    String nickname = reqbody.getNickname();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    // null check
    if (nickname == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (nickname.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // nickname exist check -> parent table constraint check
    boolean constraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!constraintCheck) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // route exist check -> parent table constraint check
    List<outdoor_route> routeConstraintCheck = outdoorService.routeExistCheck(route_index);
    if (routeConstraintCheck.isEmpty()) {
      // 존재하지 않는 루트에 좋아요를 추가하는 경우
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    // 유저가 좋아요를 누른 상태인지 확인
    Integer result = outdoorService.getLikes(route_index, nickname);
    if (result != null) {
      // 좋아요룰 눌렀다면 좋아요 삭제
      result = outdoorService.deleteLikes(result);
      if (result > 0) {
        res.setStatus(StatusCode.OK);
        return StatusCode.OK;
      } else {
        res.setStatus(StatusCode.Accepted);
        return StatusCode.Accepted;
      }
    } else {
      // 좋아요를 누르지 않았다면 좋아요 생성
      result = outdoorService.createLikes(null, route_index, nickname, format1.format(time));
      if (result > 0) {
        res.setStatus(StatusCode.Created);
        return StatusCode.Created;
      } else {
        res.setStatus(StatusCode.Accepted);
        return StatusCode.Accepted;
      }
    }
  }

  @Operation(summary = "루트 완등 체크 API")
  @PostMapping("/clear")
  public int createClear(@RequestBody create_user_clearlist_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    int route_index = reqbody.getRoute_index();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // route exist check -> parent table constraint check
    List<outdoor_route> routeConstraintCheck = outdoorService.routeExistCheck(route_index);
    if (routeConstraintCheck.isEmpty()) {
      // 존재하지 않는 루트에 완등체크를 추가하는 경우
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    // 유저가 완등체크를 수행한 상태인지 확인
    Integer result = outdoorService.getClear(route_index, user_index);
    if (result != null) {
      // 완등 상태면 완등체크 해제
      result = outdoorService.deleteClear(result);
      if (result > 0) {
        res.setStatus(StatusCode.OK);
        return StatusCode.OK;
      } else {
        res.setStatus(StatusCode.Accepted);
        return StatusCode.Accepted;
      }
    } else {
      // 미완등 상태면 완등체크
      result = outdoorService.createClear(null, user_index, route_index, format1.format(time));
      if (result > 0) {
        res.setStatus(StatusCode.Created);
        return StatusCode.Created;
      } else {
        res.setStatus(StatusCode.Accepted);
        return StatusCode.Accepted;
      }
    }
  }

  @Operation(summary = "암장에 대한 평점 작성 API")
  @PostMapping("/score")
  public int createScore(@RequestBody create_outdoor_score_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    int location_index = reqbody.getLocation_index();
    float score = reqbody.getScore();

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // outdoor_locatoin exist check -> parent table constraint check
    List<get_outdoorLocation_dto> routeConstraintCheck = outdoorService.locationExistCheck(location_index);
    if (routeConstraintCheck.isEmpty()) {
      // 존재하지 않는 암장에 평점을 추가하는 경우
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    // data exist check
    Integer dataExistCheck = outdoorService.scoreExistCheck(user_index, location_index);
    if (dataExistCheck != null) {
      // 이미 평점을 작성한 경우
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = outdoorService.createScore(null, user_index, location_index, score);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "암장에 대한 평점 수정 API")
  @PutMapping("/score")
  public int modifyScore(@RequestBody create_outdoor_score_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    int location_index = reqbody.getLocation_index();
    float score = reqbody.getScore();

    // outdoor_locatoin exist check -> parent table constraint check
    List<get_outdoorLocation_dto> routeConstraintCheck = outdoorService.locationExistCheck(location_index);
    if (routeConstraintCheck.isEmpty()) {
      // 존재하지 않는 암장의 평점을 수정하는 경우
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    int result = outdoorService.modifyScore(user_index, location_index, score);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "암장에 대한 평점 삭제 API")
  @DeleteMapping("/score")
  public int deleteScore(@RequestBody delete_outdoor_score_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    int location_index = reqbody.getLocation_index();

    // user_index exist check -> parent table constraint check
    String constraintCheck = userService.idExistCheck(user_index);
    if (constraintCheck == null) {
      // 존재하지 않는 유저인 경우
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    int idx = outdoorService.scoreExistCheck(user_index, location_index);
    if (idx < 0) { // 존재하지 않는 암장의 평점을 삭제하는 경우
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    int result = outdoorService.deleteScore(idx, location_index);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }
}