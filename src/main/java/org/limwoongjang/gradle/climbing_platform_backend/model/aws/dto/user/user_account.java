package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "user_account table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class user_account {
  @Schema(description = "user_information table index")
  private int user_index;

  @Schema(description = "계정(ID)")
  private String user_id;

  @Schema(description = "비밀번호")
  private String password;
}
