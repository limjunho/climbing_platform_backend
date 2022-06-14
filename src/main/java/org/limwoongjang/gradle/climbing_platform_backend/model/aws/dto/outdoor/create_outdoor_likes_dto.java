package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "루트 좋아요 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class create_outdoor_likes_dto {
  @Schema(description = "outdoor_route table index")
  private int route_index;

  @Schema(description = "좋아요 누른사람 닉네임")
  private String nickname;
}
