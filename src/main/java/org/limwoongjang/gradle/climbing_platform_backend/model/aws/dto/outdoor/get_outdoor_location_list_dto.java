package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "지도에 표시할 암장 리스트 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class get_outdoor_location_list_dto {
  @Schema(description = "outdoor_location table index")
  private int location_index;

  @Schema(description = "위도")
  private String latitude;

  @Schema(description = "경도")
  private String longitude;

  @Schema(description = "암장 이름")
  private String name;
}
