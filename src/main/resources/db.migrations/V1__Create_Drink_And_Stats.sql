CREATE TABLE drink (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       water_amount INT NOT NULL,
                       coffee_amount INT NOT NULL,
                       milk_amount INT NOT NULL
);

CREATE TABLE drink_stats (
                             id BIGSERIAL PRIMARY KEY,
                             drink_id BIGINT REFERENCES drink(id),
                             times_ordered INT NOT NULL
);
