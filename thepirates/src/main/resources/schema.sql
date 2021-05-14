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
