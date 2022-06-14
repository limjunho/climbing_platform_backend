package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "암장 평점 추가 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class create_outdoor_score_dto {
  @Schema(description = "user_index")
  private int user_index;

  @Schema(description = "outdoor_location table index")
  private int location_index;

  @Schema(description = "평점")
  private float score;
}
