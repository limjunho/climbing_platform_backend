package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "post_reply table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class post_reply {
  @Schema(description = "post_reply table index")
  private int idx;

  @Schema(description = "post_list table index")
  private int post_index;

  @Schema(description = "post_comments table index")
  private String comment_index;

  @Schema(description = "답글 작성자 닉네임")
  private String nickname;

  @Schema(description = "답글 내용")
  private String reply;

  @Schema(description = "답글 작성 시각")
  private String record_time;

  @Schema(description = "답글 수정 여부")
  private String is_modified;
}