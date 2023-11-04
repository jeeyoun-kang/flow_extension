# 파일 확장자 차단 프로그램 과제

<br>


## 목차

- [소개](#소개)
- [개발 환경](#개발-환경)
- [사용기술](#사용-기술)
- [How To Install](#How-To-Install)

- [화면 구성](#화면-구성)

- [ERD 구조](#erd-구조)

- [핵심 기능](#핵심-기능)

- [각 스택들을 사용한 이유](#각-스택들을-사용한-이유)

  
<br>


## 소개

어떤 파일들은 첨부시 보안에 문제가 될 수 있습니다.. 특히 exe, sh 등의 실행파일이 존재할 경우 서버에 올려서 실행이 될 수 있는 위험이 있어 파일 확장자 차단을 하게 되었습니다. <br>

<br>


## 개발 환경

- Windows
- IntelliJ
- GitHub
<br>

## 사용 기술

**백엔드**

- Java 11 openjdk
- SpringBoot 2.7.17
- Spring Data JPA
- Lombok

**프론트엔드**

- Html5/css3
- Javascript
- Thymeleaf
- Ajax

**빌드 툴**

- Gradle 8.3

**데이터베이스**

- H2(Version 2.2.224 (2023-09-17)
<br>

## How To Install

- 스프링 부트버전에 맞춰 [H2 데이터베이스 설치](#https://www.h2database.com/html/main.html)

- 맥은chmod 755 h2.sh(윈도)로 H2권한주기
- 실행
  - 맥 : ./h2.sh
  - 윈도우 :  h2.bat 실행
- DB 파일 생성
  - 처음에 접속시, jdbc:h2:~/파일명 로 접속
  - ~/파일명.mv.db 파일 생성 확인 
  - 이후부터는 jdbc:h2:tcp://localhost/~/파일명 로 접속
- sql폴더안에 있는 sql파일로 테이블 생성

<br>


## 화면 구성



<h3>고정확장자</h3>

![extension_1](https://github.com/jeeyoun-kang/codingtest/assets/59076085/276be9a8-37aa-43b6-8a2c-aa24bca7808a)

<br>

<h3>커스텀확장자</h3>

![extension_2](https://github.com/jeeyoun-kang/codingtest/assets/59076085/03ba2d89-168f-48d0-9f8d-f78eb4d1fd75)


## ERD-구조

![extension_erd](https://github.com/HaeBangProject/HAEBANG/assets/59076085/4c998521-b7e4-4a6d-9f12-41ebd3409aea)

<br>


## 핵심 기능

### 1-1. 고정 확장자는 차단을 자주하는 확장자, default는 unCheck가 되어 있어야한다.

- checkbox 태그의 default가 uncheck이여서 checkbox 태그를 이용해서 구현했습니다. 



### 1-2. 고정 확장자를 check or uncheck를 할 경우, db에 저장한다. 또한 새로고침 시 유지되어야 한다.(커스텀 확장자에는 표현x)

- localStorage의 setItem 메서드를 이용해서 확장자에 대한 체크 여부의 값을 로컬스토리지에 저장해서 저장된 데이터가 새로고침해도 유지되게 구현했습니다.



### 2-1. 확장자 최대 입력 길이는 20자리여야 한다.

- 확장자를 입력할때 input의 maxlength를 20으로 설정하였고, DB 모델링에서도 varchar(20)로 설정해서 제한을 두었습니다.



### 2-2. 추가버튼 클릭 시 db에 저장되며, 아래쪽 영역에 표현된다.

- Ajax를 이용해 추가버튼을 클릭시 확장자 데이터를 컨트롤러에 보내고, repository의 findby메서드를 이용해 save시켜 db에 저장되게 구현했습니다.
- jQuery를 이용해 append시켜 아래쪽영역에 추가해 구현했습니다.



### 3-1. 커스텀 확장자는 최대 200개까지 추가가 가능하다.

- 확장자가 추가될때마다 카운트를 세어 최대 200개까지 추가가 가능하게 설정했고, 그 이상은 alert를 통해 해당 조건을 알렸습니다.



### 3-2. 확장자 옆 x를 클릭 시, db에서 삭제가 가능하다.

- Ajax를 이용해 x버튼을 클릭 시 확장자 데이터를 컨트롤러에 보내고, DB에 기존에 데이터가 존재할때 db에서 삭제하게 구현했습니다.

<br>


## 각 스택들을 사용한 이유

Java & Springboot : 최근까지 계속 사용했던 언어와 프레임워크로써 JPA와 함께 간단하게 DB모델링을 구현하기 위해 사용했습니다.

H2 : 테스트환경을 고려해 경량화 DB를 쓰려 하였고, JPA와 사용하기에 간편한 설정으로 쓰기 편해 사용하였습니다.

Ajax : 각각의 변동사항들(DB 저장,삭제 / 확장자개수 카운트)을 페이지를 로드하지않고 서버에 데이터를 전달하고, 동적으로 페이지를 업데이트하기 위해 사용하였습니다.

