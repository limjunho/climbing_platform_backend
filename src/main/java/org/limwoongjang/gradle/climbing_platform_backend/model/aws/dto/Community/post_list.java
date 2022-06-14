package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "post_list table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class post_list {
  @Schema(description = "post_list table index")
  private int post_index;

  @Schema(description = "게시글 제목")
  private String header;

  @Schema(description = "게시글 내용(본문)")
  private String body;

  @Schema(description = "게시 날짜")
  private String post_date;

  @Schema(description = "글 작성자 닉네임")
  private String nickname;

  @Schema(description = "최종 수정 날짜")
  private String modify_time;

  @Schema(description = "이미지가 있는지에 대한 정보 이미지 경로")
  private int isImage;

  @Schema(description = "댓글 수")
  private int comment_count;

  @Schema(description = "좋아요 수")
  private int like_count;
}
