package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserConfirm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "login DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class login_dto {
  @Schema(description = "계정(ID)")
  private String user_id;

  @Schema(description = "비밀번호")
  private String password;
}
