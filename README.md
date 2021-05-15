# The Pirates 과제

## 1. 설치 및 환경설정 가이드

### 개발환경
- OS: macOS Big Sur 11.3.1
- IDE: IntelliJ IDEA 2021.1.1 (Ultimate Edition)
- Language & Framework: `JAVA`, `Spring`, `Spring Data JDBC`
- Database: H2(In-Memory)

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

**Holidy Create**

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

