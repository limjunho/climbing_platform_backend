package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "루트정보 조회 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class get_outdoor_route_dto {
  @Schema(description = "outdoor_route table index")
  private int route_index;

  @Schema(description = "outdoor_location")
  private int location_index;

  @Schema(description = "루트 이름")
  private String route_name;

  @Schema(description = "볼트 개수")
  private String bolt_count;

  @Schema(description = "난이도")
  private String difficulty;

  @Schema(description = "이미지가 있는지에 대한 정보 이미지 경로")
  private int isImage;

  @Schema(description = "좋아요 개수")
  private String like_count;

  @Schema(description = "유저의 좋아요 여부")
  private int isLike;

  @Schema(description = "유저의 완등 여부")
  private int isClear;
}