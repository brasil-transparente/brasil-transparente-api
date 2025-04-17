CREATE TABLE `elemento_despesa`
(
    `elemento_despesa_id`   bigint NOT NULL AUTO_INCREMENT,
    `name_elemento_despesa` varchar(255) DEFAULT NULL,
    `unidade_gestora_id`    bigint       DEFAULT NULL,
    `total_value_spent`     double NOT NULL,
    `percentage_of_total`   double NOT NULL,
    PRIMARY KEY (`elemento_despesa_id`),
    KEY `unidade_gestora_id` (`unidade_gestora_id`),
    CONSTRAINT `elemento_despesa_ibfk_1` FOREIGN KEY (`unidade_gestora_id`) REFERENCES `unidade_gestora` (`unidade_gestora_id`)
);