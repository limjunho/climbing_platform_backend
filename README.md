# Back-end README

생성일: 2021년 12월 22일 오후 6:43  
작성 날짜: 2021년 12월 22일  
최종 수정날짜: 2021년 12월 22일  
최종 수정자: 임준호  

# 1. 개요

- 졸업 프로젝트이며 올림픽 종목으로도 선정되는 등 늘어나는 스포츠클라이밍 유저에 비해 부족한 인프라를 보충하고자 아이디어를 냈다. 실내암장에 비해 정보를 얻기 힘든 실외 자연암벽장의 정보들을 열람할 수 있도록 정리하고 커뮤니티 활성화를 유도하여 유저간의 소통을 증대시키기를 목표로 한다.
- 개발기간 : 2021.01 ~ 2022.06.22 (6개월)
- 개발인원 : 2 Front-end, 1 Back-end
- 프로젝트 종료 날짜 : 2022.06.14


# 2. 프로젝트 주요 기능

1. 지도를 통한 자연암벽장 위치 확인 서비스
    * 검색 필터링등의 기능 
2. 해당 암벽장의 루트(등반 문제) 정보 확인 서비스(이미지, 난이도, 완등자 수 등)
    * 난이도 또는 완등하지 않은 루트 등으로 필터링 기능
3. 루트에 대한 유저의 코멘트 기능 및 crux(루트에서 어려운 구간)을 표시하여 클라이머들 간의 정보 공유
4. 완등한 루트들을 날짜 및 시도횟수와 함께 기록하여 실력향상 동기부여
5. 커뮤니티 기능(글 작성, 댓글 작성, 좋아요 등등..)
6. 관리자 기능(유저 관리, 공지사항 관리, 암장 및 루트정보 관리)

서버 자체는 JAVA + Spring boot로 구현되며 AWS에서 서버 구동 예정.

# 3. 버전 정보

- java v17.0.1
- spring boot v2.5.8
- Lombok Annotations Support for VS Code v1.0.1
- mysql 
- mybatis 
- AWS
- Swagger v3.0.0

# 4. 개발 규칙

- 반복적인 코드 하나로 통일 ex) http status code structure
- 변수 명명 규칙 : camelCase
- 주석은 최소화하며 리팩토링은 강력하게 수행
- 최적화보다는 가독성에 집중
- 반드시 테스트 코드 작성하고 테스트 진행 후 배포

# 5. 프로젝트 관련 문서 path
- /documents/.
    - ERD
    - 요구사항정의서
    - workbench mwb
    - 테이블 생성 및 테스트데이터 insert sql

# 7. 프로젝트 관련 자료 정리

[Home - limjunho](https://limjunho.github.io/)

# 프로젝트 결과물