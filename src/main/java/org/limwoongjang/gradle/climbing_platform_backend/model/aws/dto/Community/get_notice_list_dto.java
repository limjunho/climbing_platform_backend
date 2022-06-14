package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "공지사항 조회 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class get_notice_list_dto {
  @Schema(description = "공지사항 조회 DTO")
  private int notice_index;

  @Schema(description = "공지사항 제목")
  private String header;

  @Schema(description = "게시 날짜")
  private String post_date;

  @Schema(description = "최종 수정 날짜")
  private String modify_date;

  @Schema(description = "이미지가 있는지에 대한 정보 이미지 경로")
  private int isImage;
}