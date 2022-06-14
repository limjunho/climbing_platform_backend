package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "좋아요 생성 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class create_post_likes {
  @Schema(description = "post_list table index")
  private int post_index;

  @Schema(description = "좋아요 누른사람 닉네임")
  private String nickname;
}
