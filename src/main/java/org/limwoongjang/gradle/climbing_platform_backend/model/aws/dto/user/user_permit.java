package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "user_permit table schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class user_permit {
  @Schema(description = "user_information table index")
  private int user_index;

  @Schema(description = "이메일 수신 동의 여부")
  private String email_permit;

  @Schema(description = "문자 수신 동의 여부")
  private String sms_permit;

  @Schema(description = "푸시알림 수신 동의 여부")
  private String push_permit;
}