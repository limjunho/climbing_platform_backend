package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Manage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "공지사항 생성 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class create_notice_dto {
  @Schema(description = "공지사항 제목")
  private String header;

  @Schema(description = "공지사항 내용(본문)")
  private String body;

  @Schema(description = "업로드된 이미지 경로")
  private int isImage;
}
