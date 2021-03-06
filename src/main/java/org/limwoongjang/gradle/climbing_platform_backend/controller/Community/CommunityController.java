package org.limwoongjang.gradle.climbing_platform_backend.controller.Community;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.create_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.create_post_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.create_post_likes;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.create_post_reply_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.delete_post_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.delete_post_reply_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.modify_comments_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.modify_post_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_comments;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_list;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_reply;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.notice_list;
import org.limwoongjang.gradle.climbing_platform_backend.model.http.StatusCode;
import org.limwoongjang.gradle.climbing_platform_backend.service.CommunityService;
import org.limwoongjang.gradle.climbing_platform_backend.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/community")
@RestController
public class CommunityController {
  private final CommunityService communityService;
  private final UserService userService;

  @Operation(summary = "????????? ?????? API")
  @PostMapping("/post")
  public int createPost(@RequestBody create_post_dto reqbody, HttpServletResponse res) {
    String header = reqbody.getHeader();
    String body = reqbody.getBody();
    String nickname = reqbody.getNickname();
    int isImage = reqbody.getIsImage();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // null check
    if (header == null || body == null || nickname == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (header.isEmpty() || body.isEmpty() || nickname.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = communityService.createPost(null, header, body, format1.format(time), nickname, null, isImage, 0, 0);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "????????? ????????? ????????? API")
  @PostMapping("/postImage")
  public int uploadPostImage(@RequestPart List<MultipartFile> files, HttpServletResponse res)
      throws Exception {
    String originName = "";
    String saveName = "";
    long size;
    int post_index = communityService.checkPostIndex();
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/post/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/post/";
    UUID uuid = UUID.randomUUID();

    path += post_index;

    // ??????????????? ????????? ??????
    File Folder = new File(path);
    if (!Folder.exists()) {
      try {
        Folder.mkdir(); // ???????????? ??????.
      } catch (Exception e) {
        e.getStackTrace();
      }
    } else {
    }

    for (MultipartFile file : files) {
      originName = file.getOriginalFilename();
      size = file.getSize();
      
      // DB??? ????????? ??????(static resource ????????? ??????)
      path = "/static/images/post/" + post_index;

      // filename??? ????????? ???????????? ?????? UUID ??????
      saveName = uuid.toString() + "_" + originName;

      path = path + "/" + saveName;
      int result = communityService.uploadPostImage(null, post_index, originName, size, path);
      if (result > 0) {
        // ?????? ???????????? ?????? ??????
        // path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/post/" + post_index + "/" + saveName;
        path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/post/" + post_index + "/" + saveName;
        
        file.transferTo(new File(path));
      } else {
        res.setStatus(StatusCode.serverErr);
        return StatusCode.serverErr;
      }
    }

    res.setStatus(StatusCode.Created);
    return StatusCode.Created;
  }

  @Operation(summary = "????????? ?????? API")
  @PutMapping("/post")
  public int modifyPost(@RequestBody modify_post_dto reqbody, HttpServletResponse res) {
    int post_index = reqbody.getPost_index();
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

    int result = communityService.modifyPost(post_index, header, body, format1.format(time));
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "????????? ?????? API")
  @DeleteMapping("/post")
  public int deleteUser(@RequestParam int post_index, HttpServletResponse res) {
    // String path = "D:/Github/climbing_platform_backend/src/main/resources/static/images/post/";
    String path = "/home/ubuntu/climbing_platform_backend/src/main/resources/static/images/post/";
    path += post_index;

    int result = communityService.deletePost(post_index);
    if (result > 0) {
      // ???????????? ?????? ???????????? ??????
      File folder = new File(path);
      try {
        while (folder.exists()) {
          File[] folder_list = folder.listFiles(); // ??????????????? ????????????

          for (int j = 0; j < folder_list.length; j++) {
            folder_list[j].delete(); // ?????? ??????
          }

          if (folder_list.length == 0 && folder.isDirectory()) {
            folder.delete(); // ???????????? ??????
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

  @Operation(summary = "????????? ????????? ?????? API")
  @GetMapping("/post-list")
  public List<get_post_list_dto> getPostList(@RequestParam String nickname, HttpServletResponse res) {
    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    return communityService.getPostList(nickname);
  }

  @Operation(summary = "????????? ????????? ????????? ?????? API")
  @GetMapping("/post-list/populer/like")
  public List<get_post_list_dto> getPostList_popular_like(@RequestParam String nickname, HttpServletResponse res) {
    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    return communityService.getPostList_popular_like(nickname);
  }

  @Operation(summary = "?????? ????????? ????????? ????????? ?????? API")
  @GetMapping("/post-list/populer/comments")
  public List<get_post_list_dto> getPostList_popular_comments(@RequestParam String nickname, HttpServletResponse res) {
    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    return communityService.getPostList_popular_comments(nickname);
  }

  @Operation(summary = "?????? ????????? ????????? ????????? ?????? API")
  @GetMapping("/post-list/author")
  public List<get_post_list_dto> getPostList_author(@RequestParam String nickname, HttpServletResponse res) {

    // null check
    if (nickname == null) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    // empty string check
    if (nickname.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<get_post_list_dto> result = communityService.getPostList_author(nickname);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    }

    return result;
  }

  @Operation(summary = "?????? ????????? ????????? ????????? ?????? API")
  @GetMapping("/post-list/commentByMyself")
  public List<get_post_list_dto> getPostList_comment(@RequestParam String nickname, HttpServletResponse res) {

    // null check
    if (nickname == null) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    // empty string check
    if (nickname.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<get_post_list_dto> result = communityService.getPostList_commentByMyself(nickname);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    }

    return result;
  }

  @Operation(summary = "????????? ?????? API")
  @GetMapping("/post")
  public List<get_post_dto> getPost(@RequestParam int post_index, String nickname, HttpServletResponse res) {

    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return null;
    }

    List<get_post_dto> result = communityService.getPost(post_index, nickname);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "????????? ????????? ?????? API")
  @GetMapping("/postImage")
  public List<get_image_dto> getPostImage(@RequestParam int post_index, HttpServletResponse res) {

    List<get_image_dto> result = communityService.getPostImage(post_index);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "????????? ??????+?????? ?????? API")
  @GetMapping("/searchPostByContents")
  public List<get_post_list_dto> searchPostByContents(@RequestParam String contents, HttpServletResponse res) {
    // null check
    if (contents == null) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    // empty string check
    if (contents.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    List<get_post_list_dto> result = communityService.searchPostByContents(contents);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "????????? ????????? ?????? API")
  @GetMapping("/searchPostByNickname")
  public List<get_post_list_dto> searchPostByNickname(@RequestParam String nickname, HttpServletResponse res) {
    // null check
    if (nickname == null) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    // empty string check
    if (nickname.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return null;
    }

    List<get_post_list_dto> result = communityService.searchPostByNickname(nickname);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "???????????? ????????? ?????? API")
  @GetMapping("/notice-list")
  public List<notice_list> getNoticeList(HttpServletResponse res) {

    return communityService.getNoticeList();
  }

  @Operation(summary = "???????????? ?????? API")
  @GetMapping("/notice")
  public List<notice_list> getNotice(@RequestParam int notice_index, HttpServletResponse res) {

    List<notice_list> result = communityService.getNotice(notice_index);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "???????????? ????????? ?????? API")
  @GetMapping("/noticeImage")
  public List<get_image_dto> getNoticeImage(@RequestParam int notice_index, HttpServletResponse res) {

    List<get_image_dto> result = communityService.getNoticeImage(notice_index);
    if (result.isEmpty()) {
      res.setStatus(StatusCode.NoContent);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return result;
    }
  }

  @Operation(summary = "?????? ?????? API")
  @PostMapping("/comments")
  public int createComments(@RequestBody create_comments_dto reqbody, HttpServletResponse res) {
    Integer post_index = reqbody.getPost_index();
    String nickname = reqbody.getNickname();
    String comment = reqbody.getComment();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

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
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // post exist check -> parent table constraint check
    List<post_list> postConstraintCheck = communityService.postExistCheck(post_index);
    if (postConstraintCheck.isEmpty()) {
      // ???????????? ?????? ???????????? ????????? ???????????? ??????
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    int result = communityService.createComments(null, post_index, nickname, comment, format1.format(time), 0);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "?????? ?????? API")
  @PutMapping("/comments")
  public int modifyComments(@RequestBody modify_comments_dto reqbody, HttpServletResponse res) {
    int comment_index = reqbody.getComment_index();
    String comment = reqbody.getComment();

    // null check
    if (comment == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (comment.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = communityService.modifyComments(comment_index, comment, 1);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "?????? ?????? API")
  @DeleteMapping("/comments")
  public int deleteComments(@RequestBody delete_post_comments_dto reqbody, HttpServletResponse res) {
    int comment_index = reqbody.getComment_index();
    int post_index = reqbody.getPost_index();

    int result = communityService.deleteComments(comment_index, post_index);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "????????? ?????? ?????? ?????? API")
  @PostMapping("/reply")
  public int createReplies(@RequestBody create_post_reply_dto reqbody, HttpServletResponse res) {
    int comment_index = reqbody.getComment_index();
    int post_index = reqbody.getPost_index();
    String nickname = reqbody.getNickname();
    String reply = reqbody.getReply();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    // null check
    if (nickname == null || reply == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (nickname.isEmpty() || reply.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // nickname exist check -> parent table constraint check
    boolean nicknameConstraintCheck = userService.nicknameDupllicateCheck(nickname);
    if (!nicknameConstraintCheck) {
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // comments exist check -> parent table constraint check
    List<post_comments> commentConstraintCheck = communityService.commentExistCheck(comment_index);
    if (commentConstraintCheck.isEmpty()) {
      // ???????????? ?????? ????????? ????????? ???????????? ??????
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    int result = communityService.createReplies(post_index, null, comment_index, nickname, reply, format1.format(time),
        0);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }

  @Operation(summary = "?????? ?????? API")
  @DeleteMapping("/reply")
  public int deleteReplies(@RequestBody delete_post_reply_dto reqbody, HttpServletResponse res) {
    int idx = reqbody.getIdx();
    int post_index = reqbody.getPost_index();

    int result = communityService.deleteReplies(idx, post_index);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "?????? ?????? API")
  @GetMapping("/comments")
  public List<post_comments> getComments(@RequestParam int post_index, HttpServletResponse res) {
    // comments exist check -> parent table constraint check
    List<post_list> commentConstraintCheck = communityService.postExistCheck(post_index);
    if (commentConstraintCheck.isEmpty()) {
      // ???????????? ?????? ???????????? ????????? ???????????? ??????
      res.setStatus(StatusCode.notFound);
      return null;
    }

    List<post_comments> result = communityService.getComments(post_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "?????? ?????? API")
  @GetMapping("/reply")
  public List<post_reply> getReplies(@RequestParam int post_index, HttpServletResponse res) {
    // comments exist check -> parent table constraint check
    List<post_list> commentConstraintCheck = communityService.postExistCheck(post_index);
    if (commentConstraintCheck.isEmpty()) {
      // ???????????? ?????? ???????????? ????????? ???????????? ??????
      res.setStatus(StatusCode.notFound);
      return null;
    }

    List<post_reply> result = communityService.getReplies(post_index);
    if (!result.isEmpty()) {
      res.setStatus(StatusCode.OK);
      return result;
    } else {
      res.setStatus(StatusCode.NoContent);
      return null;
    }
  }

  @Operation(summary = "????????? ????????? API")
  @PostMapping("/like")
  public int createLikes(@RequestBody create_post_likes reqbody, HttpServletResponse res) {
    int post_index = reqbody.getPost_index();
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
      // ???????????? ?????? ????????? ??????
      res.setStatus(StatusCode.Unauthorized);
      return StatusCode.Unauthorized;
    }

    // post exist check -> parent table constraint check
    List<post_list> postConstraintCheck = communityService.postExistCheck(post_index);
    if (postConstraintCheck.isEmpty()) {
      // ???????????? ?????? ???????????? ????????? ???????????? ??????
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }

    // ????????? ???????????? ?????? ???????????? ??????
    Integer result = communityService.getLikes(post_index, nickname);
    if (result != null) {
      // ???????????? ???????????? ????????? ??????
      result = communityService.deleteLikes(result, post_index);
      if (result > 0) {
        res.setStatus(StatusCode.OK);
        return StatusCode.OK;
      } else {
        res.setStatus(StatusCode.Accepted);
        return StatusCode.Accepted;
      }
    } else {
      // ???????????? ????????? ???????????? ????????? ??????
      result = communityService.createLikes(null, post_index, nickname, format1.format(time));
      if (result > 0) {
        res.setStatus(StatusCode.Created);
        return StatusCode.Created;
      } else {
        res.setStatus(StatusCode.Accepted);
        return StatusCode.Accepted;
      }
    }
  }
}