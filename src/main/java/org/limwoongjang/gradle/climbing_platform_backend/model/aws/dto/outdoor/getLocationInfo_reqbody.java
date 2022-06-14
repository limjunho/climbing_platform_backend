package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "getLocationInfo_reqbody")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class getLocationInfo_reqbody {
  @Schema(description = "outdoor_location table index")
  private int location_index;

  @Schema(description = "user_index")
  private int user_index;
}
