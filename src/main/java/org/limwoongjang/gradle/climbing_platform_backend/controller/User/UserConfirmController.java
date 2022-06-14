package org.limwoongjang.gradle.climbing_platform_backend.controller.User;

import javax.servlet.http.HttpServletResponse;

import org.limwoongjang.gradle.climbing_platform_backend.model.aws.dto.user.UserConfirm.login_dto;
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
@RequestMapping("/confirm")
@RestController
public class UserConfirmController {
  private final UserService userService;

  @Operation(summary = "로그인 API")
  @PostMapping("/login")
  public boolean login(@RequestBody login_dto reqbody, HttpServletResponse res) {
    String user_id = reqbody.getUser_id();
    String password = reqbody.getPassword();

    // null check
    if (user_id == null || password == null) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    // empty string check
    if (user_id.isEmpty() || password.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    boolean result = userService.login(user_id, password);
    if (result) {
      res.setStatus(StatusCode.OK);
      return true;
    } else {
      res.setStatus(StatusCode.OK);
      return false;
    }
  }

  @Operation(summary = "회원정보 수정 시 패스워드를 확인하는 API")
  @GetMapping("/password")
  public boolean passwordExistCheck(@RequestParam int user_index, String password, HttpServletResponse res) {

    // null check
    if (password == null) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    // empty string check
    if (password.isEmpty()) {
      res.setStatus(StatusCode.badRequest);
      return false;
    }

    String result = userService.idExistCheck(user_index);
    if (result == null) { // 패스워드를 확인하고자 하는 유저가 존재하지 않는 경우
      res.setStatus(StatusCode.notFound);
      return false;
    } else { // 패스워드를 확인하고자 하는 유저가 존재하는 경우
      res.setStatus(StatusCode.OK);
      return userService.passwordExistCheck(user_index, password);
    }
  }

  @Operation(summary = "아이디 찾기 API")
  @GetMapping("/id")
  public String idExistCheck(@RequestParam int user_index, HttpServletResponse res) {
    String user_accounts = userService.idExistCheck(user_index);
    if (user_accounts == null) {
      res.setStatus(StatusCode.notFound);
      return null;
    } else {
      res.setStatus(StatusCode.OK);
      return user_accounts;
    }
  }
}