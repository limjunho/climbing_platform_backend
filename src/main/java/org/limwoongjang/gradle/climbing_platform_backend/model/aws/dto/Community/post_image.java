package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "post_image table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class post_image {
  @Schema(description = "post_image table index")
  private Integer image_index;

  @Schema(description = "post_list table index")
  private int post_index;

  @Schema(description = "이미지 원본 이름")
  private String origin_name;

  @Schema(description = "이미지 사이즈")
  private long size;

  @Schema(description = "이미지 경로(변경 이름 포함)")
  private String path;
}
