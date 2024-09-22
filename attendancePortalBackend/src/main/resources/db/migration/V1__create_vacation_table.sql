CREATE TABLE vacation_detail (
                                 id SERIAL PRIMARY KEY,
                                 vacation_type VARCHAR(255) NOT NULL,
                                 vacation_date_from DATE NOT NULL,
                                 vacation_date_to DATE NOT NULL,
                                 vacation_reason VARCHAR(255),
                                 is_deleted VARCHAR(1) DEFAULT '0'
);
