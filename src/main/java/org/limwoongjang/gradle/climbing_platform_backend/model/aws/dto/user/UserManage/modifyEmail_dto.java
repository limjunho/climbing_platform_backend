package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserManage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "이메일 변경을 위한 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class modifyEmail_dto {
  @Schema(description = "user_information table index")
  private int user_index;

  @Schema(description = "이메일")
  private String email;
}
