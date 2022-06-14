package org.limwoongjang.gradle.climbing_platform_backend.controller.User;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserJoin.user_join_dto;
import org.limwoongjang.gradle.climbing_platform_backend.model.http.StatusCode;
import org.limwoongjang.gradle.climbing_platform_backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/join")
@RestController
public class UserJoinController {
  private final UserService userService;

  @Operation(summary = "회원가입 시 ID의 중복을 확인하는 API")
  @GetMapping("/id-dup-check")
  public boolean IdDupCheck(@RequestParam String user_id, HttpServletResponse res) {
    // null check
    if (user_id == null) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    // empty string check
    if (user_id.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    res.setStatus(StatusCode.OK);
    return userService.idDupllicateCheck(user_id);
  }

  @Operation(summary = "회원가입 시 email의 중복을 확인하는 API")
  @GetMapping("/email-dup-check")
  public boolean emailDupCheck(@RequestParam String email, HttpServletResponse res) {
    // null check
    if (email == null) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    // empty string check
    if (email.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    res.setStatus(StatusCode.OK);
    return userService.emailDupllicateCheck(email);
  }

  @Operation(summary = "회원가입 시 nickname의 중복을 확인하는 API")
  @GetMapping("/nickname-dup-check")
  public boolean nicknameDupCheck(@RequestParam String nickname, HttpServletResponse res) {
    // null check
    if (nickname == null) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    // empty string check
    if (nickname.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    res.setStatus(StatusCode.OK);
    return userService.nicknameDupllicateCheck(nickname);
  }

  @Operation(summary = "회원가입 API")
  @PostMapping("/")
  public int userjoin(@RequestBody user_join_dto reqbody, HttpServletResponse res) {
    String user_name = reqbody.getUser_name();
    String email = reqbody.getEmail();
    String phone = reqbody.getPhone();
    String nickname = reqbody.getNickname();
    String device_token = reqbody.getDevice_token();
    String user_id = reqbody.getUser_id();
    String password = reqbody.getPassword();

    // null check
    if (user_name == null || email == null || phone == null || nickname == null || device_token == null ||
        user_id == null || password == null) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    // empty string check
    if (user_name.isEmpty() || email.isEmpty() || phone.isEmpty() || nickname.isEmpty() || device_token.isEmpty() ||
        user_id.isEmpty() || password.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return StatusCode.badRequest;
    }

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    int result = userService.userjoin(null, user_name, email, phone, nickname, device_token, format1.format(time),
        user_id,
        password);
    if (result > 0) {
      res.setStatus(StatusCode.Created);
      return StatusCode.Created;
    } else {
      res.setStatus(StatusCode.serverErr);
      return StatusCode.serverErr;
    }
  }
}