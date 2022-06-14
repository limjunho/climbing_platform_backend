package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "outdoor_comments table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class create_outdoor_comments_dto {
  @Schema(description = "outdoor_route table index")
  private int route_index;

  @Schema(description = "댓글 작성자 닉네임")
  private String nickname;

  @Schema(description = "댓글 내용")
  private String comment;
}