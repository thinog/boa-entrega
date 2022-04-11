#!/bin/bash

psql --username "$POSTGRES_USER" --dbname "customer" <<-EOSQL
    -- create database customer; -- customer database already created by docker-compose file
    create database supplier;
    grant all privileges on database customer to boaentrega;
    grant all privileges on database supplier to boaentrega;
EOSQL
