package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "댓글에 대한 답글 작성 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class create_post_reply_dto {
  @Schema(description = "post_comments table index")
  private int comment_index;

  @Schema(description = "post_list table index")
  private int post_index;

  @Schema(description = "답글 작성자 닉네임")
  private String nickname;

  @Schema(description = "답글 내용")
  private String reply;
}