DROP TABLE `Recipes`;

CREATE TABLE Recipes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    type VARCHAR(255),
    flavour VARCHAR(255),
    noIngred INTEGER NOT NULL
);