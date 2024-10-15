CREATE TABLE car
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    image        VARCHAR(255)          NULL,
    model        VARCHAR(255)          NULL,
    popularity   INT                   NOT NULL,
    seats        INT                   NOT NULL,
    transmission VARCHAR(255)          NULL,
    fuel         VARCHAR(255)          NULL,
    price        INT                   NOT NULL,
    about        VARCHAR(255)          NULL,
    CONSTRAINT pk_car PRIMARY KEY (id)
);

CREATE TABLE reservation
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    date_from          date                  NULL,
    date_to            date                  NULL,
    car_id             BIGINT                NULL,
    customer_email     VARCHAR(255)          NULL,
    customer_name      VARCHAR(255)          NULL,
    customer_phone     VARCHAR(255)          NULL,
    reservation_number VARCHAR(255)          NULL,
    CONSTRAINT pk_reservation PRIMARY KEY (id)
);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_CAR FOREIGN KEY (car_id) REFERENCES car (id);