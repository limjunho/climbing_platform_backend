package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserConfirm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "패스워드 확인을 위한 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class passwordExistCheck_dto {
  @Schema(description = "user_information table index")
  private int user_index;

  @Schema(description = "비밀번호")
  private String password;
}
