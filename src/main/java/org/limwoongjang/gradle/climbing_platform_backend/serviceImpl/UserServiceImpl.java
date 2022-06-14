package org.limwoongjang.gradle.climbing_platform_backend.serviceImpl;


import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dao.UserMapper;
import org.limwoongjang.gradle.climbing_platform_backend.service.UserService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;
  
  @Override
  public boolean login(String user_id, String password) {
    if (userMapper.login(user_id, password).isEmpty())
      return false;
    else
      return true;
  }

  @Override
  public boolean idDupllicateCheck(String user_id) {
    if (userMapper.idDupllicateCheck(user_id).isEmpty())
      return false;
    else
      return true;
  }

  @Override
  public boolean emailDupllicateCheck(String email) {
    if (userMapper.emailDupllicateCheck(email).isEmpty())
      return false;
    else
      return true;
  }

  @Override
  public boolean nicknameDupllicateCheck(String nickname) {
    if (userMapper.nicknameDupllicateCheck(nickname).isEmpty())
      return false;
    else
      return true;
  }

  @Override
  public int userjoin(Integer user_index, String user_name, String email, String phone, String nickname,
      String device_token, String join_date, String user_id, String password) {
    return userMapper.userjoin(user_index, user_name, email, phone, nickname, device_token, join_date, user_id,
        password);
  }

  @Override
  public int deleteUser(int user_index) {
    return userMapper.deleteUser(user_index);
  }

  @Override
  public boolean passwordExistCheck(int user_index, String password) {
    if (userMapper.passwordExistCheck(user_index, password).isEmpty())
      return false;
    else
      return true;
  }

  @Override
  public String idExistCheck(int user_index) {
    return userMapper.idExistCheck(user_index);
  }

  @Override
  public int modifyPassword(int user_index, String password) {
    return userMapper.modifyPassword(user_index, password);
  }

  @Override
  public int modifyPhoneNumber(int user_index, String phone) {
    return userMapper.modifyPhoneNumber(user_index, phone);
  }

  @Override
  public int modifyEmail(int user_index, String email) {
    return userMapper.modifyEmail(user_index, email);
  }

  @Override
  public int modifyPushPermit(int user_index, int push_permit) {
    return userMapper.modifyPushPermit(user_index, push_permit);
  }

  @Override
  public int modifySmsPermit(int user_index, int sms_permit) {
    return userMapper.modifySmsPermit(user_index, sms_permit);
  }

  @Override
  public int modifyEmailPermit(int user_index, int email_permit) {
    return userMapper.modifyEmailPermit(user_index, email_permit);
  }
}