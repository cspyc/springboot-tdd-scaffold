CREATE TABLE IF NOT EXISTS `todo`
(
    `id`        int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title`     varchar(20),
    `completed` int
)