package org.limwoongjang.gradle.climbing_platform_backend.service;

import java.util.List;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_comments;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_list;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_reply;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.notice_list;

public interface CommunityService {
  public int createPost(Integer post_index, String header, String body, String post_date, String nickname,
      String modify_time, int isImage, int comment_count, int like_count);

  public int checkPostIndex();
  
  public int uploadPostImage(Integer image_index, int post_index, String origin_name, Long size, String path);

  public int modifyPost(int post_index, String header, String body,
      String modify_time);

  public int deletePost(int post_index);

  public List<get_post_list_dto> getPostList(String nickname);

  public List<get_post_list_dto> getPostList_popular_like(String nickname);

  public List<get_post_list_dto> getPostList_popular_comments(String nickname);

  public List<get_post_list_dto> getPostList_author(String nickname);

  public List<get_post_list_dto> getPostList_commentByMyself(String nickname);

  public List<get_post_dto> getPost(int post_index, String nickname);

  public List<post_list> postExistCheck(int post_index);

  public List<get_image_dto> getPostImage(int post_index);

  public List<get_post_list_dto> searchPostByContents(String contents);

  public List<get_post_list_dto> searchPostByNickname(String nickname);

  public List<notice_list> getNoticeList();

  public List<notice_list> getNotice(int notice_index);

  public List<get_image_dto> getNoticeImage(int notice_index);

  public int createComments(Integer comment_index, int post_index, String nickname, String comment,
      String record_time, int is_modified);

  public int modifyComments(int comment_index, String comment, int is_modified);

  public int deleteComments(int comment_index, int post_index);

  public int createReplies(int post_index, Integer idx, int comment_index, String nickname, String reply,
      String record_time, int is_modified);

  public int deleteReplies(int idx, int post_index);

  public List<post_comments> getComments(int post_index);

  public List<post_reply> getReplies(int post_index);

  public List<post_comments> commentExistCheck(int comment_index);

  public Integer getLikes(int post_index, String nickname);

  public int createLikes(Integer idx, int post_index, String nickname, String like_time);

  public int deleteLikes(int idx, int post_index);
}