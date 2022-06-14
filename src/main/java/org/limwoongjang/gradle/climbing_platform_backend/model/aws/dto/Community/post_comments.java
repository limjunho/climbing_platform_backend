package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "post_comments table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class post_comments {
  @Schema(description = "post_comments table index")
  private int comment_index;

  @Schema(description = "post_list table index")
  private int post_index;

  @Schema(description = "댓글 작성자 닉네임")
  private String nickname;

  @Schema(description = "댓글 내용")
  private String comment;

  @Schema(description = "댓글 작성 시각")
  private String record_time;

  @Schema(description = "댓글 수정 여부")
  private int is_modified;
}
