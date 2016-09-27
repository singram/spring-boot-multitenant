CREATE DATABASE IF NOT EXISTS tenant_a;
CREATE DATABASE IF NOT EXISTS tenant_b;

CREATE TABLE IF NOT EXISTS tenant_a.people (
  id         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(60) not null,
  last_name  varchar(60) not null,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS tenant_b.people (
  id         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(60) not null,
  last_name  varchar(60) not null,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
);
