package org.limwoongjang.gradle.climbing_platform_backend.controller.User;

import javax.servlet.http.HttpServletResponse;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserConfirm.passwordExistCheck_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserManage.modifyEmailPermit_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserManage.modifyEmail_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserManage.modifyPhoneNumber_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserManage.modifyPushPermit_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserManage.modifySmsPermit_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.http.StatusCode;
import org.limwoongjang.gradle.climbing_platform_backend.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserManageController {
  private final UserService userService;

  @Operation(summary = "회원탈퇴 API")
  @DeleteMapping("signout")
  public int deleteUser(@RequestParam int user_index, HttpServletResponse res) {
    int result = userService.deleteUser(user_index);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "패스워드 변경 API")
  @PutMapping("/password")
  public int modifyPassword(@RequestBody passwordExistCheck_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    String password = reqbody.getPassword();

    // null check
    if (password == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (password.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = userService.modifyPassword(user_index, password);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "전화번호 변경 API")
  @PutMapping("/phone")
  public int modifyPhoneNumber(@RequestBody modifyPhoneNumber_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    String phone = reqbody.getPhone();

    // null check
    if (phone == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (phone.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = userService.modifyPhoneNumber(user_index, phone);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "이메일 변경 API")
  @PutMapping("/email")
  public int modifyEmail(@RequestBody modifyEmail_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    String email = reqbody.getEmail();

    // null check
    if (email == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (email.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    int result = userService.modifyEmail(user_index, email);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "푸시알림 허용 여부 변경 API")
  @PutMapping("/push-permit")
  public int modifyPushPermit(@RequestBody modifyPushPermit_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    int push_permit = reqbody.getPush_permit();

    /*
    // null check
    if (push_permit == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (push_permit.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }
    */

    // 0 or 1의 값만 받도록 필터링
    if (!(push_permit == 0 || push_permit == 1)) {
      return StatusCode.badRequest;
    }

    int result = userService.modifyPushPermit(user_index, push_permit);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "SMS 수신 허용 여부 변경 API")
  @PutMapping("/sms-permit")
  public int modifySmsPermit(@RequestBody modifySmsPermit_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    int sms_permit = reqbody.getSms_permit();

    /*
    // null check
    if (sms_permit == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (sms_permit.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }
    */

    // 0 or 1의 값만 받도록 필터링
    if (!(sms_permit == 0 || sms_permit == 1)) {
      return StatusCode.badRequest;
    }

    int result = userService.modifySmsPermit(user_index, sms_permit);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }

  @Operation(summary = "이메일 수신 허용 여부 변경 API")
  @PutMapping("/email-permit")
  public int modifyEmailPermit(@RequestBody modifyEmailPermit_dto reqbody, HttpServletResponse res) {
    int user_index = reqbody.getUser_index();
    int email_permit = reqbody.getEmail_permit();

    /*
    // null check
    if (email_permit == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (email_permit.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }
    */

    // 0 or 1의 값만 받도록 필터링
    if (!(email_permit == 0 || email_permit == 1)) {
      return StatusCode.badRequest;
    }

    int result = userService.modifyEmailPermit(user_index, email_permit);
    if (result > 0) {
      res.setStatus(StatusCode.OK);
      return StatusCode.OK;
    } else {
      res.setStatus(StatusCode.notFound);
      return StatusCode.notFound;
    }
  }
}