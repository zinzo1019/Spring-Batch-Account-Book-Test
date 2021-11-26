# Spring-Batch-Account-Book-Test

총 세 가지 상황을 가정하여 테스트 진행

1. 특정 사용자의 지출 내역 가져오기 & 총합 구하기
   예제) email이 email...1인 사용자의 지출 내역과 총합
2. 특정 사용자 + 카테고리에 따른 지출 내역과 총합 가져오기
   예제) 이메일이 email...1이고 카테고리가 FOOD인 지출 내역과 총합
3. 특정 사용자 + 친/반친환경에 따른 지출 내역과 총합 가져오기
   예제) 이메일이 email...1이고 친환경인 지출 내역과 총합
  
프로그램 돌리는 법

1. DB 생성 후 application.properties에서 DB 연결
2. 테스트 코드 실행 -> DB에 user, expenditure 테이블 자동 생성
3. test > RepositoryTest > UserTest 파일 실행 -> 테이블에 데이터 자동 생성
4. HelloSpringBatchApplication 실행 (메인 코드 실행) -> 오류 없이 진행되면 성공 -> 1번 테스트
5. main > hellospringbatch > configuration > JobConfiguration 클래스 안에 @Configuration 어노테이션 주석 처리
6. main > hellospringbatch > configuration > JobConfiguration2 클래스 안에 @Configuration 어노테이션 주석 풀고 다시 메인 코드 실행 -> 2번 테스트
7. 다시 어노테이션 주석 처러ㅣ
8. main > hellospringbatch > configuration > JobConfiguration3 클래스 안에 @Configuration 어노테이션 주석 풀고 다시 메인 코드 실행 -> 3번 테스트

