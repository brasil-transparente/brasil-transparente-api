CREATE TABLE `poder`
(
    `poder_id`            bigint NOT NULL AUTO_INCREMENT,
    `name_poder`          varchar(255) DEFAULT NULL,
    `total_value_spent`   double NOT NULL,
    `percentage_of_total` double NOT NULL,
    PRIMARY KEY (`poder_id`)
);