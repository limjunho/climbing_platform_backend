package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "게시글 작성 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class create_post_dto {
  @Schema(description = "게시글 제목")
  private String header;

  @Schema(description = "게시글 내용(본문)")
  private String body;

  @Schema(description = "글 작성자 닉네임")
  private String nickname;

  @Schema(description = "이미지가 있는지에 대한 정보")
  private int isImage;
}
