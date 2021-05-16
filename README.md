# The Pirates 과제

## 1. 설치 및 환경설정 가이드

### 개발환경
- OS: macOS Big Sur 11.3.1
- IDE: IntelliJ IDEA 2021.1.1 (Ultimate Edition)
- Language & Framework: `JAVA`, `Spring`, `Spring Data JDBC`
- Database: H2(In-Memory)

### 환경설정 방법1

1. `git clone https://github.com/Polynomeer/ThePirates.git`을 합니다.
2. IntelliJ에서 프로젝트를 import합니다.
3. 프로젝트를 실행한 다음, API 문서를 참조하여 실행합니다.
4. `localhost:8080/h2-console`로 브라우저를 통해 DB를 확인할 수 있습니다. (접속 정보는 아래의 그림 참조)

![2021-05-15_13-50-44](https://user-images.githubusercontent.com/62940574/118348379-d643a500-b584-11eb-87da-5cd85ba001a4.png)

\* 개발 시 포스트맨을 사용하였고 public으로 공개되어 있습니다. (Postsman에서 'ThePirates'로 검색하면 찾을 수 있습니다.)

![image](https://user-images.githubusercontent.com/62940574/118348483-a0eb8700-b585-11eb-9a15-ed99e8a61dca.png)

### 환경설정 방법2

1. `git clone https://github.com/Polynomeer/ThePirates.git`을 합니다.
2. Gradle 프로젝트가 있는 디렉터리로 이동하여 다음 명령으로 빌드를 합니다. `./gradlew build`
3. `/ThePirates/thepirates/build/libs/ThePirates-0.0.1-SNAPSHOT.jar`경로에 생성된 jar파일을 확인하고, 해당경로로 이동합니다.
4. `java -jar ThePirates-0.0.1-SNAPSHOT.jar`명령어로 jar파일을 실행합니다.

![image](https://user-images.githubusercontent.com/62940574/118399828-f87b1700-b699-11eb-867a-719db1bc272f.png)

5. 또 다른 터미널을 열어서 curl명령어로 테스트를 합니다. [API Documents](https://github.com/Polynomeer/ThePirates/wiki/API-Documents)를 참조하여 curl명령어로 터미널에서 바로 실행이 가능합니다.

![image](https://user-images.githubusercontent.com/62940574/118399853-1183c800-b69a-11eb-873c-fa2d7639d45f.png)

### 사용방법

**사용예시**
- `PUT localhost:8080/stores`으로 점포를 등록한다. (주어진 JSON을 request body에 넣고 두 개를 등록해봅니다.)
- `PUT localhost:8080/stores/{store_id}`으로 해당 점포의 휴무일을 등록합니다.
- `GET localhost:8080/stores`으로 점포 목록을 조회합니다.
- `GET localhost:8080/stores/{store_id}`으로 점포 하나의 상세정보를 조회합니다.

## 2. 테이블 생성 SQL

```sql
CREATE TABLE IF NOT EXISTS `store`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)  NOT NULL UNIQUE,
    `owner`       VARCHAR(45)  NOT NULL,
    `description` VARCHAR(100) NULL,
    `level`       INT          NOT NULL,
    `address`     VARCHAR(100) NULL,
    `phone`       VARCHAR(20)  NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `business_time`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `day`      TINYINT(1)  NOT NULL,
    `open`     VARCHAR(45) NOT NULL,
    `close`    VARCHAR(45) NOT NULL,
    `store_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_business_time_store`
        FOREIGN KEY (`store_id`)
            REFERENCES `store` (`id`)
);

CREATE TABLE IF NOT EXISTS `holiday`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `holiday`  VARCHAR(20) NULL,
    `store_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_holiday_store`
        FOREIGN KEY (`store_id`)
            REFERENCES `store` (`id`)
)

```

## 3. API 사용 가이드

**Store Create(점포 생성)**

- Description: 점포의 요청 파라미터를 전달 받아 DB 에 저장
- Request

```http
POST /stores
```

- Request Body

```json
{
   "name": "인어수산",
   "owner": "장인어",
   "description": "인천소래포구 종합어시장 갑각류센터 인어수산",
   "level": 2,
   "address": "인천광역시 남동구 논현동 680-1 소래포구 종합어시장 1층 1호",
   "phone": "010-1111-2222",
   "businessTimes": [
      {
         "day": "Monday",
         "open": "13:00",
         "close": "23:00"
      },
      {
         "day": "Tuesday",
         "open": "13:00",
         "close": "23:00"
      },
      {
         "day": "Wednesday",
         "open": "09:00",
         "close": "18:00"
      },
      {
         "day": "Thursday",
         "open": "09:00",
         "close": "23:00"
      },
      {
         "day": "Friday",
         "open": "09:00",
         "close": "23:00"
      }
   ]
}
```

**Stores Read(점포 목록 조회)**

- Description: 점포의 목록을 모두 조회
- Request

```http
GET /stores
```

- Response Body

```json
[
    {
        "name": "인어수산",
        "description": "인천소래포구 종합어시장 갑각류센터 인어수산",
        "level": 2,
        "businessStatus": "HOLIDAY"
    },
    {
        "name": "해적수산",
        "description": "노량진 시장 광어, 참돔 등 싱싱한 고퀄 활어 전문 횟집",
        "level": 1,
        "businessStatus": "OPEN"
    }
]
```

**Stores Read(한 개의 점포 조회)**

- Description: 한 개의 점포 상세정보 조회
- Request

```http
GET /stores/{store_id}
```

- Response Body

```json
{
    "id": 2,
    "name": "해적수산",
    "description": "노량진 시장 광어, 참돔 등 싱싱한 고퀄 활어 전문 횟집",
    "level": 1,
    "address": "서울 동작구 노량진동 13-8 노량진수산시장 활어 001",
    "phone": "010-1234-1234",
    "businessDays": [
        {
            "day": "Saturday",
            "open": "15:00",
            "close": "24:00",
            "status": "CLOSE"
        },
        {
            "day": "Sunday",
            "open": "11:00",
            "close": "24:00",
            "status": "OPEN"
        },
        {
            "day": "Monday",
            "open": "09:00",
            "close": "23:00",
            "status": "OPEN"
        }
    ]
}
```

**Store Delete**

- Description: 해당 점포를 삭제
- Request

```http
DELETE /stores/{store_id}
```

**Holiday Create**

- Description: 점포의 휴무일의 정보를 DB에 저장
- Request

```http
POST /stores/{store_id}
```
- Request Body

```json
{
    "id": 1,
    "holidays": [
        "2021-05-15",
        "2021-05-16"
    ]
}
```
