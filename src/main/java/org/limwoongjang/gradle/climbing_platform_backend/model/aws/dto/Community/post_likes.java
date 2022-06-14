package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "post_likes table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class post_likes {
  @Schema(description = "post_likes table index")
  private int idx;

  @Schema(description = "post_list table index")
  private int post_index;
  
  @Schema(description = "좋아요 누른사람 닉네임")
  private String nickname;

  @Schema(description = "좋아요 누른 시각")
  private String like_time;
}
