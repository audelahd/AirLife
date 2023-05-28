DROP TABLE RESERVATIONS;
DROP TABLE REVIEWS;
DROP TABLE SERVICE;
DROP TABLE ROOMS;
DROP TABLE USERS;

DROP SEQUENCE SEQ_UNUM;
DROP SEQUENCE SEQ_RNUM;

--여기서부터 실행, 실행시 테이블 5개가 생기면 성공-- 

CREATE SEQUENCE SEQ_UNUM NOCACHE START WITH 2; --시퀀스, (USER_NUM)에 사용 
--EX) INSERT INTO USERS (USER_NUM) VALUES (SEQ_UNUM.NEXTVAL);
CREATE SEQUENCE SEQ_RNUM NOCACHE; --시퀀스, (REVIEW_ID)에 사용


CREATE TABLE users ( --회원 정보 테이블 
 user_num VARCHAR(20),
 user_id VARCHAR(50) UNIQUE,
 username VARCHAR(50),    
 password VARCHAR(50),
 age VARCHAR(15),
 --여기까지 사용자가 입력함 
 registration_date DATE DEFAULT sysdate,   --회원가입 날짜
 grade VARCHAR(10) DEFAULT 'NORMAL', --회원 등급 (블랙리스트 유무)
 mlg INT DEFAULT 0, --마일리지
 PRIMARY KEY (user_num)
);         

CREATE TABLE rooms ( --방 정보 테이블
  room_num INT UNIQUE,      --방 번호
  room_type VARCHAR(50) NOT NULL,  --방 종류
  price INT NOT NULL,  --방 가격
  checkio varchar(4) DEFAULT 'O'
);

CREATE TABLE reservations ( --예약 테이블
  check_in_date DATE,    --체크인날짜
  check_out_date DATE,  --체크아웃날짜
  user_num varchar(10) references users (user_num),
  room_num int references rooms (room_num)  
);

CREATE TABLE reviews ( --리뷰 테이블
  review_id INT PRIMARY KEY,  
  rating float NOT NULL,       --리뷰 평점 0.5 단위로 ( ex 3.5 )
  review_comment varchar(150),         --리뷰 내용
  review_date DATE default SYSDATE,     --리뷰 작성 날짜
  user_num varchar(10) references users (user_num),
  room_num int references rooms (room_num)
);

CREATE TABLE service ( --서비스 목록 테이블 (관리자가 추가하고 삭제할 수 있음)
s_name varchar(20) NOT null, --서비스 이름
s_content varchar(150), --서비스 설명 (필수X)
serox int DEFAULT 4
);

INSERT into rooms(room_num,room_type,price) VALUES(101,'그랜드디럭스',200000);
INSERT into rooms(room_num,room_type,price) VALUES(102,'그랜드디럭스',200000);
INSERT into rooms(room_num,room_type,price) VALUES(103,'그랜드디럭스',200000);
INSERT into rooms(room_num,room_type,price) VALUES(201,'프리미어',150000);
INSERT into rooms(room_num,room_type,price) VALUES(202,'프리미어',150000);
INSERT into rooms(room_num,room_type,price) VALUES(203,'프리미어',150000);
INSERT into rooms(room_num,room_type,price) VALUES(301,'디럭스스위트',250000);
INSERT into rooms(room_num,room_type,price) VALUES(302,'디럭스스위트',250000);
INSERT into rooms(room_num,room_type,price) VALUES(303,'디럭스스위트',250000);
INSERT into rooms(room_num,room_type,price) VALUES(401,'프리미어 스위트',180000);
INSERT into rooms(room_num,room_type,price) VALUES(402,'프리미어 스위트',180000);
INSERT into rooms(room_num,room_type,price) VALUES(403,'프리미어 스위트',180000);
INSERT into rooms(room_num,room_type,price) VALUES(501,'프레지덴셜스위트',300000);
INSERT into rooms(room_num,room_type,price) VALUES(502,'프레지덴셜스위트',300000);
INSERT into rooms(room_num,room_type,price) VALUES(503,'프레지덴셜스위트',300000);

INSERT into service values('룸서비스','남산과 도심 전경이 그림처럼 펼쳐지는 공간에서 하루 3번 제공되는 룸 서비스',1);
INSERT into service values('레스토랑예약','객실에서 여유롭게 예약할 수 있는 레스토랑 예약 서비스',1);
INSERT into service values('수영장','남산의 전경을 만끽하며 즐기는 수영과 휴식 길이',2);
INSERT into service values('Bar','화려한 도심 야경을 바라보며 즐기는 다채로운 라운지 바',2);
INSERT into service values('스파','유럽의 감성과 한국 고유의 여유로운 찜질방 문화가 접목된 스파',2);
INSERT into service values('헬스클럽','100평 규모의 다양한 운동기구가 갖춰져있는 최신식 헬스클럽',3);
INSERT into service values('바베큐','Roast방식의 바베큐를 채택하여 오븐에서 갓 구운 요리 제공',4);
INSERT into service values('호텔소개','한국을 대표하는 최초의 호화 랜드마크 호텔로 국내최대 호텔그룹에서 운영중입니다.',10);
insert into users(user_num,user_id,password) VALUES('1','admin','12345');

commit;