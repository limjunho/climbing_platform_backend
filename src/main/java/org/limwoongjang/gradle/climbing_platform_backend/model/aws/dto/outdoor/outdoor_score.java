package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "outdoor_score table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class outdoor_score {
  @Schema(description = "outdoor_score table index")
  private Integer idx;

  @Schema(description = "user_index")
  private int user_index;

  @Schema(description = "location_index")
  private int location_index;

  @Schema(description = "평점")
  private String score;
}