package org.limwoongjang.gradle.climbing_platform_backend.model.http;

/**
 * @author limjunho
 * @date 2021-12-23
 * @desc HTTP Status Code 인터페이스
 **/

public interface StatusCode {
  int OK = 200; // 정상응답, 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
  int Created = 201; // 요청이 성공적이였으며 그 결과로 새로운 리소스 생성
  int Accepted = 202; // 요청을 수신하였지만 그에 응하여 행동하지 않음
  int NoContent = 204; // 서버가 요청을 성공적으로 처리했지만 요청에 대해서 보내줄 수 있는 콘텐츠가 없다.

  int badRequest = 400; // 요청이 부적절 할 때, 유효성 검증 실패, 필수 값 누락 등.
  int Unauthorized = 401; // 인증 실패, 로그인하지 않은 사용자 또는 권한 없는 사용자 처리
  int Forbidden = 403; // 인증 성공 그러나 자원에 대한 권한 없음. 삭제, 수정시 권한 없음.
  int notFound = 404; // 요청한 URI에 대한 리소스 없을 때 사용.
  int requestTimeout = 408; // 요청 타임아웃 에러
  int Conflict = 409; // 요청 충돌 에러, 리소스 상태에 위반되는 행위 시 사용.

  int serverErr = 500; // 서버 내부 에러
}