# naekaracubae-user-service
네, 카라쿠배 서비스의 구독자를 관리하는 서비스입니다.

## 사용 기술
- Spring Boot(2.4.1)
- Java(1.8)
- Junit4
- travis ci
- AWS S3
- AWS codedeploy
- AWS EC2
- AWS RDS

## 배포 파이프라인
![image](https://user-images.githubusercontent.com/81010357/136643901-c4747072-d68d-4b94-a83a-cbf660353d30.png)
- a. 로컬 push & master 브랜치로 merge
- b. travis ci는 master 브랜치 merge 감지 & 테스트 통과 시 빌드 
- c. 압축 후 s3에 저장
- d. codedeploy는 s3에 저장된 파일을 EC2로 이동
- e. EC2 내부에서 어플리케이션 실행
