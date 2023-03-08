create table Patient(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    egn VARCHAR(10) NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    middle_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    doctor_id BIGINT NOT NULL,
    has_insurance BOOLEAN NOT NULL
);
drop table Patient;

create table Diagnosis(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(65) NOT NULL
);
drop table Diagnosis;