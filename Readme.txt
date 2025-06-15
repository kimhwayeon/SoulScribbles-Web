[SoulScribbles 프로젝트 실행 안내서 ]


1. 개발 환경
- 운영체제 : Windows 10 이상
- Java Version : Java 17 이상 (현재 프로젝트는 Java 21 사용)
- IDE : IntelliJ IDEA
- 빌드 툴 : Gradle 8.4 이상
- DB : MySQL 8.0 이상
- 프레임워크: Spring Boot 3.5.0


2. 실행 방법
(1) MySQL을 실행하고 아래 정보로 DB를 생성합니다.
- 사용자명: root
- 비밀번호: 1234
- 포트번호: 3307

(2) IntelliJ에서 'build.gradle' 파일을을 선택하여 프로젝트를 엽니다.

(3) 'SoulscribblesBackendApplication.java'에서 실행 버튼을 클립합니다.

(4) http://localhost:8080 에서 서버가 구동됩니다.

(5) 웹 브라우저에서 접속하여 아래의 테스트 계정으로 로그인하거나 회원가입 후 사용합니다.
- E-mail : Test@naver.com
- 비밀번호 : 1234
- 최초 로그인 시 감정 선택 화면으로 이동하며, 감정 선택 후 다른 기능 사용 가능


3. 주요 기능
- 사용자 회원가입 및 로그인
- 감정 선택 및 감정 일기 작성(녹음 기능 포함), 수정, 삭제
- 감정 타로 카드 뽑기 및 기록 조회
- 프로필 조회 및 감정 분석 결과 시각화




