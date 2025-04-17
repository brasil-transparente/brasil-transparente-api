CREATE TABLE `despesa_simplificada`
(
    `despesa_simplificada_id`                  bigint NOT NULL AUTO_INCREMENT,
    `despesa_simplificada_name`                varchar(255) DEFAULT NULL,
    `despesa_simplificada_total_value`         double NOT NULL,
    `despesa_simplificada_percentage_of_total` double NOT NULL,
    PRIMARY KEY (`despesa_simplificada_id`)
);