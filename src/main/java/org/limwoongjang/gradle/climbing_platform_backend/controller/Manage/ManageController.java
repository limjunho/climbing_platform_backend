package org.limwoongjang.gradle.climbing_platform_backend.controller.Manage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.delete_post_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.delete_post_reply_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.create_notice_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.create_outdoorLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.create_outdoor_route_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.modify_notice_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.modify_outdoorLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.modify_outdoor_route_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor.get_outdoorLocation_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserConfirm.login_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.http.StatusCode;
import org.limwoongjang.gradle.climbing_platform_backend.service.ManageService;
import org.limwoongjang.gradle.climbing_platform_backend.service.OutdoorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/manage")
@RestController
public class ManageController {
  private final ManageService manageService;
  private final OutdoorService outdoorService;

  @Operation(summary = "관리자 로그인 API")
  @PostMapping("/login")
  public boolean login(@RequestBody login_dto reqbody, HttpServletResponse res) {
    String user_id = reqbody.getUser_id();
    String password = reqbody.getPassword();

    // null check
    if (user_id == null || password == null) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    // empty string check
    if (user_id.isEmpty() || password.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    boolean result = manageService.login(user_id, password);
    if (result) {
      res.setStatus(StatusCode.OK);
      return true;
    } else {
      res.setStatus(StatusCode.OK);
      return false;
    }
  }

  @Operation(summary = "공지사항 작성 API")
  @PostMapping("/notice")
  public int createNotice(@RequestBody create_notice_dto reqbody, HttpServletResponse res) {
    String header = reqbody.getHeader();
    String body = reqbody.getBody();
    Integer isImage = reqbody.getIsImage();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    // null check
    if (header == null || body == null || isImage == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (header.isEmpty() || body.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = manageService.createNotice(null, header, body, format1.format(time), null, isImage);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "공지사항 이미지 업로드 API")
  @PostMapping("/noticeImage")
  public int uploadPostImage(@RequestPart List<MultipartFile> files, HttpServletResponse res)
      throws Exception {
    String originName = "";
    String saveName = "";
    long size;
    int notice_index = manageService.checkNoticeIndex();
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/notice/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/notice/";
    UUID uuid = UUID.randomUUID();

    path += notice_index;

    // 디렉터리가 없다면 생성
    File Folder = new File(path);
    if (!Folder.exists()) {
      try {
        Folder.mkdir(); // 디렉터리 생성.
      } catch (Exception e) {
        e.getStackTrace();
      }
    } else {
    }

    for (MultipartFile file : files) {
      originName = file.getOriginalFilename();
      size = file.getSize();
      // path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/notice/" + notice_index + "/" + saveName;
      path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/notice/" + notice_index + "/" + saveName;

      // filename의 중복을 제거하기 위해 UUID 사용
      saveName = uuid.toString() + "_" + originName;

      path = path + "/" + saveName;
      int result = manageService.uploadNoticeImage(null, notice_index, originName, size, path);
      if (result > 0) {
        file.transferTo(new File(path));
      } else {
        res.setStatus(StatusCode.serverErr);
        return StatusCode.serverErr;
      }
    }

    res.setStatus(StatusCode.Created);
    return StatusCode.Created;
  }

  @Operation(summary = "공지사항 수정 API")
  @PutMapping("/notice")
  public int modifyNotice(@RequestBody modify_notice_dto reqbody, HttpServletResponse res) {
    int notice_index = reqbody.getNotice_index();
    String header = reqbody.getHeader();
    String body = reqbody.getBody();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    // null check
    if (header == null || body == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (header.isEmpty() || body.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = manageService.modifyNotice(notice_index, header, body, format1.format(time));
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "공지사항 삭제 API")
  @DeleteMapping("/notice")
  public int deleteNotice(@RequestParam int notice_index, HttpServletResponse res) {
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/notice/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/notice/";
    path += notice_index;

    int result = manageService.deleteNotice(notice_index);
    if (result > 0) {
      // 이미지가 있는 디렉터리 삭제
      File folder = new File(path);
      try {
        while (folder.exists()) {
          File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

          for (int j = 0; j < folder_list.length; j++) {
            folder_list[j].delete(); // 파일 삭제
          }

          if (folder_list.length == 0 && folder.isDirectory()) {
            folder.delete(); // 대상폴더 삭제
          }
        }
      } catch (Exception e) {
        e.getStackTrace();
      }
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "관리자권한 글 삭제 API")
  @DeleteMapping("/post")
  public int deletePost(@RequestParam int post_index, HttpServletResponse res) {
    int result = manageService.deletePost(post_index);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "관리자권한 댓글 삭제 API")
  @DeleteMapping("/comments")
  public int deleteComments(@RequestBody delete_post_comments_dto reqbody, HttpServletResponse res) {
    int comment_index = reqbody.getComment_index();
    int post_index = reqbody.getPost_index();

    int result = manageService.deleteComments(comment_index, post_index);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "관리자권한 답글 삭제 API")
  @DeleteMapping("/reply")
  public int deleteReplies(@RequestBody delete_post_reply_dto reqbody, HttpServletResponse res) {
    int idx = reqbody.getIdx();
    int post_index = reqbody.getPost_index();

    int result = manageService.deleteReplies(idx, post_index);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "신규 암장 추가 API")
  @PostMapping("/approach")
  public int createApproach(@RequestBody create_outdoorLocation_dto reqbody, HttpServletResponse res) {
    String address_province = reqbody.getAddress_province();
    String address_city = reqbody.getAddress_city();
    String address_detail = reqbody.getAddress_detail();
    String latitude = reqbody.getLatitude();
    String longitude = reqbody.getLongitude();
    String name = reqbody.getName();
    String description = reqbody.getDescription();
    Integer isImage = reqbody.getIsImage();

    // null check
    if (address_province == null || address_city == null || address_detail == null || latitude == null
        || longitude == null || name == null || description == null || isImage == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (address_province.isEmpty() || address_city.isEmpty() || address_detail.isEmpty() || latitude.isEmpty()
        || longitude.isEmpty() || name.isEmpty() || description.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = manageService.createApproach(null, address_province, address_city, address_detail, latitude, longitude,
        name, description, isImage, (float) 0.0);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "암장 이미지 업로드 API")
  @PostMapping("/locationImage")
  public int uploadLocationImage(@RequestPart List<MultipartFile> files, HttpServletResponse res)
      throws Exception {
    String originName = "";
    String saveName = "";
    long size;
    int location_index = manageService.checkLocationIndex();
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/location/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/location/";
    UUID uuid = UUID.randomUUID();

    path += location_index;

    // 디렉터리가 없다면 생성
    File Folder = new File(path);
    if (!Folder.exists()) {
      try {
        Folder.mkdir(); // 디렉터리 생성.
      } catch (Exception e) {
        e.getStackTrace();
      }
    } else {
    }

    for (MultipartFile file : files) {
      originName = file.getOriginalFilename();
      size = file.getSize();
      // path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/location/" + location_index + "/" + saveName;
      path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/location/" + location_index + "/" + saveName;

      // filename의 중복을 제거하기 위해 UUID 사용
      saveName = uuid.toString() + "_" + originName;

      path = path + "/" + saveName;
      int result = manageService.uploadLocationImage(null, location_index, originName, size, path);
      if (result > 0) {
        file.transferTo(new File(path));
      } else {
        res.setStatus(StatusCode.serverErr);
        return StatusCode.serverErr;
      }
    }

    res.setStatus(StatusCode.Created);
    return StatusCode.Created;
  }

  @Operation(summary = "암장 정보 수정 API")
  @PutMapping("/approach")
  public int modifyApproach(@RequestBody modify_outdoorLocation_dto reqbody, HttpServletResponse res) {
    int location_index = reqbody.getLocation_index();
    String address_province = reqbody.getAddress_province();
    String address_city = reqbody.getAddress_city();
    String address_detail = reqbody.getAddress_detail();
    String latitude = reqbody.getLatitude();
    String longitude = reqbody.getLongitude();
    String name = reqbody.getName();
    String description = reqbody.getDescription();

    // null check
    if (address_province == null || address_city == null || address_detail == null
        || latitude == null || longitude == null || name == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (address_province.isEmpty() || address_city.isEmpty() || address_detail.isEmpty()
    || latitude.isEmpty() || longitude.isEmpty() || name.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = manageService.modifyApproach(location_index, address_province, address_city, address_detail, latitude, longitude, name, description);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "암장 삭제 API")
  @DeleteMapping("/Approach")
  public int deleteRoute(@RequestParam int location_index, HttpServletResponse res) {
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/location/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/location/";
    path += location_index;
    
    int result = manageService.deleteApproach(location_index);
    if (result > 0) {
      // 이미지가 있는 디렉터리 삭제
      File folder = new File(path);
      try {
        while (folder.exists()) {
          File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

          for (int j = 0; j < folder_list.length; j++) {
            folder_list[j].delete(); // 파일 삭제
          }

          if (folder_list.length == 0 && folder.isDirectory()) {
            folder.delete(); // 대상폴더 삭제
          }
        }
      } catch (Exception e) {
        e.getStackTrace();
      }
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "신규 루트 추가 API")
  @PostMapping("/route")
  public int createRoute(@RequestBody create_outdoor_route_dto reqbody, HttpServletResponse res) {
    int location_index = reqbody.getLocation_index();
    String route_name = reqbody.getRoute_name();
    String bolt_count = reqbody.getBolt_count();
    String difficulty = reqbody.getDifficulty();
    Integer isImage = reqbody.getIsImage();

    // null check
    if (route_name == null || bolt_count == null || difficulty == null
        || isImage == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (route_name.isEmpty() || bolt_count.isEmpty() || difficulty.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // location exist check -> parent table constraint check
    List<get_outdoorLocation_dto> locationConstraintCheck = outdoorService.locationExistCheck(location_index);
    if(locationConstraintCheck.isEmpty()){
      // 존재하지 않는 암장에 루트를 추가하는 경우
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    int result = manageService.createRoute(null, location_index, route_name, bolt_count, difficulty, isImage, 0);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "루트 이미지 업로드 API")
  @PostMapping("/routeImage")
  public int uploadRouteImage(@RequestPart List<MultipartFile> files, HttpServletResponse res)
      throws Exception {
    String originName = "";
    String saveName = "";
    long size;
    int route_index = manageService.checkRouteIndex();
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/route/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/route/";
    UUID uuid = UUID.randomUUID();

    path += route_index;

    // 디렉터리가 없다면 생성
    File Folder = new File(path);
    if (!Folder.exists()) {
      try {
        Folder.mkdir(); // 디렉터리 생성.
      } catch (Exception e) {
        e.getStackTrace();
      }
    } else {
    }

    for (MultipartFile file : files) {
      originName = file.getOriginalFilename();
      size = file.getSize();
      // path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/route/" + route_index + "/" + saveName;
      path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/route/" + route_index + "/" + saveName;

      // filename의 중복을 제거하기 위해 UUID 사용
      saveName = uuid.toString() + "_" + originName;

      path = path + "/" + saveName;
      int result = manageService.uploadRouteImage(null, route_index, originName, size, path);
      if (result > 0) {
        file.transferTo(new File(path));
      } else {
        res.setStatus(StatusCode.serverErr);
        return StatusCode.serverErr;
      }
    }

    res.setStatus(StatusCode.Created);
    return StatusCode.Created;
  }

  @Operation(summary = "루트 정보 수정 API")
  @PutMapping("/route")
  public int modifyRoute(@RequestBody modify_outdoor_route_dto reqbody, HttpServletResponse res) {
    int route_index = reqbody.getRoute_index();
    String route_name = reqbody.getRoute_name();
    String bolt_count = reqbody.getBolt_count();
    String difficulty = reqbody.getDifficulty();

    // null check
    if (route_name == null || bolt_count == null || difficulty == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (route_name.isEmpty() || bolt_count.isEmpty() || difficulty.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = manageService.modifyRoute(route_index, route_name, bolt_count, difficulty);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "루트 삭제 API")
  @DeleteMapping("/route")
  public int deleteApproach(@RequestParam int route_index, HttpServletResponse res) {
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/route/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/route/";
    path += route_index;

    int result = manageService.deleteRoute(route_index);
    if (result > 0) {
      // 이미지가 있는 디렉터리 삭제
      File folder = new File(path);
      try {
        while (folder.exists()) {
          File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

          for (int j = 0; j < folder_list.length; j++) {
            folder_list[j].delete(); // 파일 삭제
          }

          if (folder_list.length == 0 && folder.isDirectory()) {
            folder.delete(); // 대상폴더 삭제
          }
        }
      } catch (Exception e) {
        e.getStackTrace();
      }
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }
}