USE equipmentManager;

CREATE TABLE lockers(
    id BIGINT NOT NULL AUTO_INCREMENT,
    location VARCHAR(45),
    address VARCHAR(45),
    primary key (id)
);

CREATE TABLE equipmentTable(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL ,
    type VARCHAR(45) ,
    requiresMaintenance BOOLEAN,
    location BIGINT NULL,
    primary key(id),
    CONSTRAINT fk_location
        FOREIGN KEY (location)
        REFERENCES lockers(id)
);


