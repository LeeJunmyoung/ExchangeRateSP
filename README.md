# 환율 변환기

Spring boot + Redis + HTML/CSS + Javascript

* 환율조회시
1. 무료 API 이기도하고 화면 조회시 같은 내용 조회 불필요.
2. 1달 최대 1000건의 요청만 가능하다는 제한사항.
3. 최초 프로세스 동작시 조회 후 1시간 단위로 조회후 redis에 저장.
4. 사용자가 접속시엔 redis에서 정보를 조회함.

* USD 환율 계산시.
1. Server 쪽에서 변환하려는 화폐종류와 USD값을 전달받아 계산후 리턴.

* ETC.
1. Spring boot version 2.1.1
2. test code는 만들어보긴 할텐데 맞는지는 잘 모르겠습니다.
3. 간단한 페이지 이기에 프론트프레임워크를 굳이 적용 하기에 적합하지 않다고 생각해 배제함.




