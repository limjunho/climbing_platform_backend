package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "user_clearlist table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class user_clearlist {
  @Schema(description = "user_clearlist table index")
  private int idx;

  @Schema(description = "user_information table index")
  private int user_index;

  @Schema(description = "outdoor_route table index")
  private int route_index;

  @Schema(description = "clear_date")
  private String clear_date;
}
