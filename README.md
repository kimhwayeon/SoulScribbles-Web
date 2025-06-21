# 📝 SoulScribbles  
> **일상의 감정을 기록하고, 감정 상태를 시각적으로 분석할 수 있는 감정 기반 일기 시스템**  
> 감정 아바타 | 타로 카드 메시지 | 텍스트 & 음성 기반 감정 일기

---

## 🛠️ 1. 개발 환경

| 구성 요소   | 버전                  |
|------------|-----------------------|
| 운영체제   | Windows 10 이상        |
| Java       | 17 이상 (현재 21 사용) |
| IDE        | IntelliJ IDEA         |
| 빌드 툴    | Gradle 8.4 이상        |
| DB         | MySQL 8.0 이상        |
| 프레임워크 | Spring Boot 3.5.0     |

---

## 🚀 2. 실행 방법

### ✅ 1. 프로젝트 다운로드

#### 👉 방법 1: Git으로 클론

```bash
git clone https://github.com/kimhwayeon/SoulScribbles-Web.git
cd SoulScribbles-Web
```

#### 👉 방법 2: GitHub 웹사이트에서 직접 다운로드

1. 초록색 **`<> Code`** 버튼 클릭
2. **Download ZIP** 선택  

---

### ✅ 2. IntelliJ로 프로젝트 열기

1. IntelliJ 실행
2. `build.gradle` 파일로 프로젝트 열기**

---

### ✅ 3. MySQL 설정 및 DB 생성

MySQL을 실행한 후, 아래 SQL 명령어로 데이터베이스를 생성합니다:

```sql
CREATE DATABASE soulscribbles;
```

> 기본 접속 정보 (환경에 따라 수정 필요):

| 항목       | 기본값 예시         |
|------------|---------------------|
| 사용자명   | `root`              |
| 비밀번호   | `qwer1234`          |
| 포트번호   | `3307`              |

📁 `src/main/resources/application.properties` 파일에서 아래와 같이 수정 가능합니다:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/soulscribbles
spring.datasource.username=root
spring.datasource.password=qwer1234
```

> MySQL이 기본 포트(3306)를 사용하는 경우 `3307 → 3306`으로 변경 필요  
> 비밀번호도 실제 pc 환경에 맞게 수정 필요

---

### ✅ 4. 프로젝트 실행

1. `SoulscribblesBackendApplication.java` 열기
2. ▶ 버튼 클릭하여 실행
3. 브라우저에서 [http://localhost:8080](http://localhost:8080) 접속

---

### ✅ 5. 사용 흐름

1. 회원가입
2. 로그인
3. 하루 최초 로그인 시 감정 선택 화면 → 이후 모든 기능 사용 가능

---

## 🌟 3. 주요 기능

- 사용자 회원가입 및 로그인  
- 감정 선택 및 감정 일기 작성 (텍스트 + 음성 녹음 포함)  
- 감정 타로 카드 뽑기 및 히스토리 조회  
- 프로필 조회 및 감정 분석 결과 시각화

---

📌 **TIP:**  
MySQL 포트번호 또는 비밀번호가 기본값과 다를 경우,  
`application.properties` 파일의 DB 연결 정보를 실제 환경에 맞게 수정해야 정상적으로 실행됩니다.

