package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "암장 별 클리어 루트 개수 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class getClearCountGroupByLocation_dto {
  @Schema(description = "전체 루트 개수")
  private int routeCount;

  @Schema(description = "전체 루트 개수")
  private int clearCount;

  @Schema(description = "암벽장 이름")
  private String name;
}