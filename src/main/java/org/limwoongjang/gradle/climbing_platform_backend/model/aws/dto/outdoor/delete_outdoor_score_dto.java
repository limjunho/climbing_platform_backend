package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "암장 평점 삭제 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class delete_outdoor_score_dto {
  @Schema(description = "user_index")
  private int user_index;

  @Schema(description = "location_index")
  private int location_index;
}