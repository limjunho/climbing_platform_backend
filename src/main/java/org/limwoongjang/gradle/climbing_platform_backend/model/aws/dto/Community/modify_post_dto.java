package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "글 수정 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class modify_post_dto {
  @Schema(description = "post_list table index")
  private int post_index;

  @Schema(description = "게시글 제목")
  private String header;

  @Schema(description = "게시글 내용(본문)")
  private String body;
}
