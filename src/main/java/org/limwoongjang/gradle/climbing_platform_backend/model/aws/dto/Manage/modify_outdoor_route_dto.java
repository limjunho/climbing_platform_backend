package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "루트 생성 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class modify_outdoor_route_dto {
  @Schema(description = "route_index")
  private int route_index;

  @Schema(description = "루트 이름")
  private String route_name;

  @Schema(description = "볼트 개수")
  private String bolt_count;

  @Schema(description = "난이도")
  private String difficulty;
}