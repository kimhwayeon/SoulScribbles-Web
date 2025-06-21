[SoulScribbles - 감정 일기 시스템]

일상의 감정을 기록하고, 감정 상태를 시각적으로 분석할 수 있는 감정 기반 일기 시스템입니다.
감정을 시각화하는 아바타, 타로 메시지를 통한 감정 피드백, 텍스트와 음성을 활용한 감정 일기 작성 기능을 제공합니다.

----------------------------------------

1. 개발 환경

- 운영체제: Windows 10 이상
- Java: 17 이상 (현재 21 사용)
- IDE: IntelliJ IDEA
- 빌드 툴: Gradle 8.4 이상
- DB: MySQL 8.0 이상
- 프레임워크: Spring Boot 3.5.0

----------------------------------------

2. 실행 방법

1) GitHub에서 프로젝트 다운로드

- 주소: https://github.com/kimhwayeon/SoulScribbles-Web
- Code 버튼 클릭 → Download ZIP → 압축 해제

또는 git 명령어로 클론:
git clone https://github.com/kimhwayeon/SoulScribbles-Web.git

2) MySQL 실행 후 아래 정보로 데이터베이스 생성

- 사용자명: root
- 비밀번호: qwer1234
- 포트번호: 3307 (기본 포트를 사용하는 경우 application.properties에서 수정 필요)

MySQL 접속 후 아래 SQL 실행:
CREATE DATABASE soulscribbles;

3) application.properties 확인 및 수정 (필요 시)

- 위치: src/main/resources/application.properties
- 예시 설정:

spring.datasource.url=jdbc:mysql://localhost:3307/soulscribbles
spring.datasource.username=root
spring.datasource.password=qwer1234

※ PC의 포트나 비밀번호가 다를 경우 위 내용을 수정해야 정상 실행됩니다.

4) IntelliJ에서 build.gradle 파일이 있는 폴더 열기

5) SoulscribblesBackendApplication.java 파일 열고 ▶ 실행

6) 브라우저에서 아래 주소 접속
http://localhost:8080

7) 회원가입 후 로그인
※ 하루 최초 로그인 시 감정 선택 화면으로 이동하며, 감정 선택 이후 모든 기능 사용 가능

----------------------------------------

3. 주요 기능

- 사용자 회원가입 및 로그인
- 감정 선택 및 감정 일기 작성 (텍스트 및 음성 녹음 포함)
- 감정 타로 카드 뽑기 및 히스토리 조회
- 프로필 조회 및 감정 분석 결과 시각화

