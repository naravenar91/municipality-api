CREATE TABLE IF NOT EXISTS region (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS municipality (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    region_id BIGINT,
        CONSTRAINT fk_region_commune
                FOREIGN KEY (region_id)
                REFERENCES region(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    user_uuid VARCHAR(36) NOT NULL,
    user_name VARCHAR(12) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    --phone VARCHAR(16) CHECK (phone ~ '^\+[1-9]\d{1,14}$'),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_municipality (
    user_id INTEGER REFERENCES users(id),
    muni_id INTEGER REFERENCES municipality(id),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, muni_id)
);