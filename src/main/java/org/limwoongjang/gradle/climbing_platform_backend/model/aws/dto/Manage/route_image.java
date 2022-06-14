package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "route_image table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class route_image {
  @Schema(description = "route_image table index")
  private Integer image_index;

  @Schema(description = "route_list table index")
  private int route_index;

  @Schema(description = "이미지 원본 이름")
  private String origin_name;

  @Schema(description = "이미지 사이즈")
  private long size;

  @Schema(description = "이미지 경로(변경 이름 포함)")
  private String path;
}
