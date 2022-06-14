package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "outdoor_likes table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class outdoor_route_likes {
  @Schema(description = "outdoor_likes table index")
  private int idx;

  @Schema(description = "outdoor_route table index")
  private int route_index;

  @Schema(description = "좋아요 누른사람 닉네임")
  private String nickname;

  @Schema(description = "좋아요 누른 시각")
  private String like_time;
}
