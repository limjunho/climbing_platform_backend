package org.limwoongjang.gradle.climbing_platform_backend.serviceImpl;

import java.util.List;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao.CommunityMapper;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_comments;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_list;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_reply;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.notice_list;
import org.limwoongjang.gradle.climbing_platform_backend.service.CommunityService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
  private final CommunityMapper CommunityMapper;

  @Override
  public int createPost(Integer post_index, String header, String body, String post_date, String nickname,
      String modify_time, int isImage, int comment_count, int like_count) {
    return CommunityMapper.createPost(post_index, header, body, post_date, nickname, modify_time, isImage,
        comment_count, like_count);
  }

  @Override
  public int checkPostIndex(){
    return CommunityMapper.checkPostIndex();
  }

  @Override
  public int uploadPostImage(Integer image_index, int post_index, String origin_name, Long size, String path) {
    return CommunityMapper.uploadPostImage(image_index, post_index, origin_name, size, path);
  }

  @Override
  public int modifyPost(int post_index, String header, String body, String modify_time) {
    return CommunityMapper.modifyPost(post_index, header, body, modify_time);
  }

  @Override
  public int deletePost(int post_index) {
    return CommunityMapper.deletePost(post_index);
  }

  @Override
  public List<get_post_list_dto> getPostList(String nickname) {
    return CommunityMapper.getPostList(nickname);
  }

  @Override
  public List<get_post_list_dto> getPostList_popular_like(String nickname) {
    return CommunityMapper.getPostList_popular_like(nickname);
  }

  @Override
  public List<get_post_list_dto> getPostList_popular_comments(String nickname) {
    return CommunityMapper.getPostList_popular_comments(nickname);
  }

  @Override
  public List<get_post_list_dto> getPostList_author(String nickname) {
    return CommunityMapper.getPostList_author(nickname);
  }

  @Override
  public List<get_post_list_dto> getPostList_commentByMyself(String nickname) {
    return CommunityMapper.getPostList_commentByMyself(nickname);
  }

  @Override
  public List<get_post_dto> getPost(int post_index, String nickname) {
    return CommunityMapper.getPost(post_index, nickname);
  }

  @Override
  public List<post_list> postExistCheck(int post_index) {
    return CommunityMapper.postExistCheck(post_index);
  }

  @Override
  public List<get_image_dto> getPostImage(int post_index) {
    return CommunityMapper.getPostImage(post_index);
  }

  @Override
  public List<get_post_list_dto> searchPostByContents(String contents) {
    return CommunityMapper.searchPostByContents(contents);
  }

  @Override
  public List<get_post_list_dto> searchPostByNickname(String nickname) {
    return CommunityMapper.searchPostByNickname(nickname);
  }

  @Override
  public List<notice_list> getNoticeList() {
    return CommunityMapper.getNoticeList();
  }

  @Override
  public List<notice_list> getNotice(int notice_index) {
    return CommunityMapper.getNotice(notice_index);
  }

  @Override
  public List<get_image_dto> getNoticeImage(int notice_index) {
    return CommunityMapper.getNoticeImage(notice_index);
  }

  @Override
  public int createComments(Integer comment_index, int post_index, String nickname, String comment,
      String record_time, int is_modified) {
    return CommunityMapper.createComments(comment_index, post_index, nickname, comment, record_time, is_modified);
  }

  @Override
  public int modifyComments(int comment_index, String comment, int is_modified) {
    return CommunityMapper.modifyComments(comment_index, comment, is_modified);
  }

  @Override
  public int deleteComments(int comment_index, int post_index) {
    return CommunityMapper.deleteComments(comment_index, post_index);
  }

  @Override
  public int createReplies(int post_index, Integer idx, int comment_index, String nickname, String reply,
      String record_time, int is_modified) {
    return CommunityMapper.createReplies(post_index, idx, comment_index, nickname, reply, record_time, is_modified);
  }
  
  @Override
  public int deleteReplies(int idx, int post_index) {
    return CommunityMapper.deleteReplies(idx, post_index);
  }

  @Override
  public List<post_comments> getComments(int post_index) {
    return CommunityMapper.getComments(post_index);
  }

  @Override
  public List<post_reply> getReplies(int post_index) {
    return CommunityMapper.getReplies(post_index);
  }

  @Override
  public List<post_comments> commentExistCheck(int comment_index) {
    return CommunityMapper.commentExistCheck(comment_index);
  }

  @Override
  public Integer getLikes(int post_index, String nickname) {
    return CommunityMapper.getLikes(post_index, nickname);
  }

  @Override
  public int createLikes(Integer idx, int post_index, String nickname, String like_time) {
    return CommunityMapper.createLikes(idx, post_index, nickname, like_time);
  }

  @Override
  public int deleteLikes(int idx, int post_index) {
    return CommunityMapper.deleteLikes(idx, post_index);
  }
}