CREATE TABLE vacation_detail (
                                 vacation_detail_id SERIAL PRIMARY KEY,
                                 vacation_type VARCHAR(255) NOT NULL,
                                 vacation_date_from DATE NOT NULL,
                                 vacation_date_to DATE NOT NULL,
                                 vacation_reason VARCHAR(255)
);
