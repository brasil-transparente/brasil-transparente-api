CREATE TABLE `orgao`
(
    `orgao_id`            bigint NOT NULL AUTO_INCREMENT,
    `name_orgao`          varchar(255) DEFAULT NULL,
    `ministerio_id`       bigint       DEFAULT NULL,
    `total_value_spent`   double NOT NULL,
    `percentage_of_total` double NOT NULL,
    PRIMARY KEY (`orgao_id`),
    KEY `ministerio_id` (`ministerio_id`),
    CONSTRAINT `orgao_ibfk_1` FOREIGN KEY (`ministerio_id`) REFERENCES `ministerio` (`ministerio_id`)
);