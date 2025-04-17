CREATE TABLE `ministerio`
(
    `ministerio_id`       bigint NOT NULL AUTO_INCREMENT,
    `name_ministerio`     varchar(255) DEFAULT NULL,
    `poder_id`            bigint       DEFAULT NULL,
    `total_value_spent`   double NOT NULL,
    `percentage_of_total` double NOT NULL,
    PRIMARY KEY (`ministerio_id`),
    KEY `poder_id` (`poder_id`),
    CONSTRAINT `ministerio_ibfk_1` FOREIGN KEY (`poder_id`) REFERENCES `poder` (`poder_id`)
);
