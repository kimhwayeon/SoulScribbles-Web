📝 SoulScribbles  
일상의 감정을 기록하고, 감정 상태를 시각적으로 분석할 수 있는 감정 기반 일기 시스템  
감정 아바타 | 타로 카드 메시지 | 텍스트 & 음성 기반 감정 일기

──────────────────────────────

🛠️ 1. 개발 환경

구성 요소        버전
──────────────  ──────────────
운영체제         Windows 10 이상
Java            17 이상 (현재 21 사용)
IDE             IntelliJ IDEA
빌드 툴         Gradle 8.4 이상
DB              MySQL 8.0 이상
프레임워크       Spring Boot 3.5.0

──────────────────────────────

🚀 2. 실행 방법

✅ 1. 프로젝트 다운로드

👉 방법 1: Git으로 클론
   git clone https://github.com/kimhwayeon/SoulScribbles-Web.git
   cd SoulScribbles-Web

👉 방법 2: GitHub 웹사이트에서 직접 다운로드
   - 초록색 <> Code 버튼 클릭
   - Download ZIP 선택 → 압축 해제

✅ 2. IntelliJ로 프로젝트 열기

- IntelliJ 실행 후 `File → Open`  
- `build.gradle` 파일이 있는 폴더 열기  
- IntelliJ 우측 Gradle 탭에서 **Refresh** 버튼 클릭  
  (의존성이 자동으로 설정되지 않을 경우 "Build → Rebuild Project" 실행)

✅ 3. MySQL 설정 및 DB 생성

- **MySQL 8.0 이상이 설치되어 있어야 합니다.**
- MySQL을 실행한 후, 아래 SQL 명령어로 데이터베이스를 생성:

  CREATE DATABASE soulscribbles;

- 기본 접속 정보 (본인 PC 환경에 따라 수정 필요):

  항목       기본값
  ────────  ──────────────
  사용자명   root
  비밀번호   qwer1234
  포트번호   3307

📁 `src/main/resources/application.properties` 파일 수정 예시:

spring.datasource.url=jdbc:mysql://localhost:3307/soulscribbles  
spring.datasource.username=root  
spring.datasource.password=qwer1234  

※ MySQL이 기본 포트(3306)를 사용하는 경우 3307 → 3306으로 변경  
※ 비밀번호도 본인 MySQL 설정에 맞게 수정해야 정상적으로 작동합니다.

✅ 4. 프로젝트 실행

- `SoulscribblesBackendApplication.java` 파일 열기
- ▶ 버튼 클릭하여 실행
- 브라우저에서 http://localhost:8080 접속

✅ 5. 사용 흐름

- 하루 최초 로그인 시 감정 선택 화면으로 이동
- 감정 선택 완료 후 모든 기능 사용 가능

──────────────────────────────

🌟 3. 주요 기능

- 사용자 회원가입 및 로그인
- 감정 선택 및 감정 일기 작성 (텍스트 및 음성 녹음 포함)
- 감정 타로 카드 뽑기 및 히스토리 조회
- 프로필 조회 및 감정 분석 결과 시각화


