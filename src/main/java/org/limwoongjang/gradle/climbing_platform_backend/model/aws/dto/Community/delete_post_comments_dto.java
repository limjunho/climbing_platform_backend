package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.Community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "post_comments table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class delete_post_comments_dto {
  @Schema(description = "post_comments table index")
  private int comment_index;

  @Schema(description = "post_list table index")
  private int post_index;
}
