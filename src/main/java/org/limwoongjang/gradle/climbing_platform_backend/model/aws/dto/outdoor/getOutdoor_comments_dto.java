package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.outdoor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "루트 댓글 조회 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class getOutdoor_comments_dto {
  @Schema(description = "outdoor_comments table index")
  private int idx;

  @Schema(description = "댓글 작성자 닉네임")
  private String nickname;

  @Schema(description = "댓글 내용")
  private String comment;

  @Schema(description = "댓글 작성 시각")
  private String record_time;
}
