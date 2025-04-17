CREATE TABLE `unidade_gestora`
(
    `unidade_gestora_id`   bigint NOT NULL AUTO_INCREMENT,
    `name_unidade_gestora` varchar(255) DEFAULT NULL,
    `orgao_id`             bigint       DEFAULT NULL,
    `total_value_spent`    double NOT NULL,
    `percentage_of_total`  double NOT NULL,
    PRIMARY KEY (`unidade_gestora_id`),
    KEY `orgao_id` (`orgao_id`),
    CONSTRAINT `unidade_gestora_ibfk_1` FOREIGN KEY (`orgao_id`) REFERENCES `orgao` (`orgao_id`)
);