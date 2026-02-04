
-- Schema: create a sample table; modify as needed
CREATE TABLE IF NOT EXISTS maintenance_task (
    id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
