# Doori_back

## 📌 프로젝트 소개
Doori_back은 독립영화 예매 서비스를 제공하는 Doori 프로젝트의 백엔드 레포지토리입니다. 
Spring Boot를 사용하여 개발되었으며, 사용자 인증, 예매 관리, 리뷰 작성 등의 기능을 제공합니다.

## 📅 개발 기간
- **개발 시작**: 2024.11.13
- **개발 종료**: 2024.11.25

## 🛠 기술 스택
- **프레임워크**: Spring Boot
- **데이터베이스**: MySQL
- **ORM**: JPA (Hibernate)
- **빌드 툴**: Maven
- **보안**: Spring Security, JWT 인증

## 📂 주요 파일
- `src/main/java/com/doori/DooriApplication.java` : 메인 애플리케이션 실행 파일
- `src/main/resources/application.yml` : 데이터베이스 및 환경 변수 설정 파일
- `src/main/java/com/doori/controller/` : API 컨트롤러
- `src/main/java/com/doori/service/` : 비즈니스 로직 구현
- `src/main/java/com/doori/repository/` : 데이터베이스 액세스 레이어
- `src/main/java/com/doori/entity/` : JPA 엔티티 클래스

## 🚀 실행 방법
1. **레포지토리 클론**
```sh
git clone https://github.com/haeunE/Doori_back.git
cd Doori_back
```
2. **환경 변수 설정**
- `src/main/resources/application.yml` 파일을 생성하고 데이터베이스 설정을 추가합니다.

## 📌 주요 기능
- 회원가입 및 로그인 (JWT 인증)
- 독립영화 예매 관리 (자리 선택, 예매 확인, 예매 취소)
- 영화 평점 및 리뷰 작성 (상영 시간이 지난 후에만 평점 작성 가능)
- 예매 내역 조회 및 취소 기능
