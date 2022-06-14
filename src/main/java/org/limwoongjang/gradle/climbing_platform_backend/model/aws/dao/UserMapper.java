package org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao;

import java.util.List;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.user_account;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserConfirm.login_dto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
  List<login_dto> login(@Param("user_id") String user_id, @Param("password") String password);

  List<user_account> idDupllicateCheck(@Param("user_id") String user_id);

  List<user_account> emailDupllicateCheck(@Param("email") String email);

  List<user_account> nicknameDupllicateCheck(@Param("nickname") String nickname);

  int userjoin(@Param("user_index") Integer user_index, @Param("user_name") String user_name,
      @Param("email") String email,
      @Param("phone") String phone, @Param("nickname") String nickname,
      @Param("device_token") String device_token,
      @Param("join_date") String join_date, @Param("user_id") String user_id, @Param("password") String password);

  int deleteUser(@Param("user_index") int user_index);

  List<user_account> passwordExistCheck(@Param("user_index") int user_index, @Param("password") String password);

  String idExistCheck(@Param("user_index") int user_index);

  int modifyPassword(@Param("user_index") int user_index, @Param("password") String password);

  int modifyPhoneNumber(@Param("user_index") int user_index, @Param("phone") String phone);

  int modifyEmail(@Param("user_index") int user_index, @Param("email") String email);

  int modifyPushPermit(@Param("user_index") int user_index, @Param("push_permit") int push_permit);

  int modifySmsPermit(@Param("user_index") int user_index, @Param("sms_permit") int sms_permit);

  int modifyEmailPermit(@Param("user_index") int user_index, @Param("Email_permit") int Email_permit);
}
