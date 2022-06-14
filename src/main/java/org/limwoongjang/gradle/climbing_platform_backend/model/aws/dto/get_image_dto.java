package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "게시글 이미지 조회 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class get_image_dto {
  @Schema(description = "post_image table index")
  private Integer image_index;

  @Schema(description = "이미지 경로(변경 이름 포함)")
  private String path;
}
