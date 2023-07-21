# 👶 Corini(코린이)

## 💻 프로젝트 소개
코인 거래소들의 공지사항, 이벤트 게시글 및 현재가(Ticker)를 수집하여 제공

## 🔧 개발기간
* 2019.10 ~ 2019.12

## ⚙️ 개발환경
- Spring Boot
- JAVA 13
- Spring Data Jpa
- QueryDsl
- Mysql
## 📌 주요 기능
- 크롤링
  - 스케쥴러를 이용하여 거래소별 공지사항, 이벤트 관련 게시글 크롤링
- 게시글
  - 각 거래소별 게시글 조회
  - 게시글 제목검색
- 현재가(중단)
  - 각 거래소별 코인 현재가 조회기능

## 🔧 추가 예정기능
- OAuth2 로그인 기능
- 거래소별 게시글 업데이트 알림 기능

### 게시글(공지사항, 이벤트) 지원 목록
- 업비트
- 코인원

### 현재가 지원 목록(중단)
- 바이낸스
- 빗썸
- 코인베이스
- 업비트
- 코인원

## ER 다이어그램
<img src="https://user-images.githubusercontent.com/52519728/241852408-7706e094-0eb0-4afe-b46d-18247006f6bb.svg" alt="ER 다이어그램">

## 시퀀스 다이어그램
<img src="https://user-images.githubusercontent.com/52519728/241861833-a2319b62-ef4a-43ce-bcae-bbd01bd05bd5.svg" alt="스케쥴러 시퀀스 다이어그램">

## 클래스 다이어그램
- 크롤링 클래스 다이어그램
<img src="https://user-images.githubusercontent.com/52519728/241861601-64f6aa8a-2fbc-4cfb-b803-ada24918f22e.svg" alt="공지사항 스케쥴러">
<img src="https://user-images.githubusercontent.com/52519728/241861537-05efd50d-7343-404a-8f87-2d1704515548.svg" alt="현재가 스케쥴러">

- 게시글(API) 클래스 다이어그램
<img src="https://user-images.githubusercontent.com/52519728/241861664-3adca6ab-32ca-498e-a735-78ee9a33fd3b.svg" alt="공지사항 API">
