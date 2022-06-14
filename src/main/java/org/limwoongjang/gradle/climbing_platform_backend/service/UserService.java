package org.limwoongjang.gradle.climbing_platform_backend.service;

public interface UserService {
  public boolean login(String user_id, String password);

  public boolean idDupllicateCheck(String user_id);

  public boolean emailDupllicateCheck(String email);

  public boolean nicknameDupllicateCheck(String nickname);

  public int userjoin(Integer user_index, String user_name, String email, String phone, String nickname,
      String device_token, String join_date, String user_id, String password);

  public int deleteUser(int user_index);

  public boolean passwordExistCheck(int user_index, String password);

  public String idExistCheck(int user_index);

  public int modifyPassword(int user_index, String password);

  public int modifyPhoneNumber(int user_index, String phone);

  public int modifyEmail(int user_index, String email);

  public int modifyPushPermit(int user_index, int push_permit);

  public int modifySmsPermit(int user_index, int sms_permit);

  public int modifyEmailPermit(int user_index, int email_permit);
}