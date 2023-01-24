USE equipmentManager;

CREATE TABLE lockers(
    id INT NOT NULL AUTO_INCREMENT,
    location VARCHAR(45),
    address VARCHAR(45),
    primary key (id)
);

CREATE TABLE equipmentTable(
    id INT NOT NULL AUTO_INCREMENT,
    type VARCHAR(45) NOT NULL ,
    requiresMaintenance BOOLEAN,
    location INT NULL,
    primary key(id),
    CONSTRAINT fk_location
        FOREIGN KEY (location)
        REFERENCES lockers(id)
);


