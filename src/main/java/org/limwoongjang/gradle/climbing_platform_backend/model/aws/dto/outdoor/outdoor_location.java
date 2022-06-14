package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "outdoor_location table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class outdoor_location {
  @Schema(description = "outdoor_location table index")
  private int location_index;

  @Schema(description = "주소(도)")
  private String address_province;

  @Schema(description = "주소(시)")
  private String address_city;

  @Schema(description = "주소(상세)")
  private String address_detail;

  @Schema(description = "위도")
  private String latitude;

  @Schema(description = "경도")
  private String longitude;

  @Schema(description = "암장 이름")
  private String name;

  @Schema(description = "암장 설명")
  private String description;

  @Schema(description = "이미지가 있는지에 대한 정보 이미지 경로")
  private int isImage;

  @Schema(description = "평점")
  private String score;
}
