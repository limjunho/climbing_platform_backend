package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.get_image_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.get_post_list_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_comments;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_list;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community.post_reply;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage.notice_list;

@Repository
@Mapper
public interface CommunityMapper {
  int createPost(@Param("post_index") Integer post_index, @Param("header") String header,
      @Param("body") String body,
      @Param("post_date") String post_date, @Param("nickname") String nickname,
      @Param("modify_time") String modify_time, @Param("isImage") int isImage,
      @Param("comment_count") int comment_count, @Param("like_count") int like_count);

  int checkPostIndex();
  
  int uploadPostImage(@Param("image_index") Integer image_index, @Param("post_index") int post_index,
      @Param("origin_name") String origin_name, @Param("size") Long size,
      @Param("path") String path);

  int modifyPost(@Param("post_index") int post_index, @Param("header") String header,
      @Param("body") String body,
      @Param("modify_time") String modify_time);

  int deletePost(@Param("post_index") int post_index);

  List<get_post_list_dto> getPostList(@Param("nickname") String nickname);

  List<get_post_list_dto> getPostList_popular_like(@Param("nickname") String nickname);

  List<get_post_list_dto> getPostList_popular_comments(@Param("nickname") String nickname);

  List<get_post_list_dto> getPostList_author(@Param("nickname") String nickname);

  List<get_post_list_dto> getPostList_commentByMyself(@Param("nickname") String nickname);

  List<get_post_dto> getPost(@Param("post_index") int post_index, @Param("nickname") String nickname);

  List<post_list> postExistCheck(@Param("post_index") int post_index);

  List<get_image_dto> getPostImage(@Param("post_index") int post_index);

  List<get_post_list_dto> searchPostByContents(@Param("contents") String contents);

  List<get_post_list_dto> searchPostByNickname(@Param("nickname") String nickname);

  List<notice_list> getNoticeList();

  List<notice_list> getNotice(@Param("notice_index") int notice_index);

  List<get_image_dto> getNoticeImage(@Param("notice_index") int notice_index);

  int createComments(@Param("comment_index") Integer comment_index, @Param("post_index") int post_index,
      @Param("nickname") String nickname,
      @Param("comment") String comment, @Param("record_time") String record_time,
      @Param("is_modified") int is_modified);

  int modifyComments(@Param("comment_index") int comment_index, @Param("comment") String comment,
      @Param("is_modified") int is_modified);

  int deleteComments(@Param("comment_index") int comment_index, @Param("post_index") int post_index);

  int createReplies(@Param("post_index") int post_index, @Param("idx") Integer idx,
      @Param("comment_index") int comment_index,
      @Param("nickname") String nickname, @Param("reply") String reply, @Param("record_time") String record_time,
      @Param("is_modified") int is_modified);

  int deleteReplies(@Param("idx") int idx, @Param("post_index") int post_index);

  List<post_comments> getComments(@Param("post_index") int post_index);

  List<post_reply> getReplies(@Param("post_index") int post_index);

  List<post_comments> commentExistCheck(@Param("comment_index") int comment_index);

  Integer getLikes(@Param("post_index") int post_index, @Param("nickname") String nickname);

  int createLikes(@Param("idx") Integer idx, @Param("post_index") int post_index,
      @Param("nickname") String nickname, @Param("like_time") String like_time);

  int deleteLikes(@Param("idx") int idx, @Param("post_index") int post_index);
}
