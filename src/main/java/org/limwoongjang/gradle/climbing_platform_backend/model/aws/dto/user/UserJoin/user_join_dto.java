package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserJoin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "회원가입 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class user_join_dto {
  @Schema(description = "이름")
  private String user_name;

  @Schema(description = "이메일")
  private String email;

  @Schema(description = "전화번호")
  private String phone;

  @Schema(description = "닉네임")
  private String nickname;

  @Schema(description = "푸시알림을 위한 device token")
  private String device_token;

  @Schema(description = "계정(ID)")
  private String user_id;

  @Schema(description = "비밀번호")
  private String password;
}
