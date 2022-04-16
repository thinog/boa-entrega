#!/bin/bash

psql --username "$POSTGRES_USER" --dbname "kong" <<-EOSQL
    -- create database kong; -- kong database already created by docker-compose file
    create database keycloak;
    create database customer;
    create database supplier;
    grant all privileges on database kong to boaentrega;
    grant all privileges on database keycloak to boaentrega;
    grant all privileges on database customer to boaentrega;
    grant all privileges on database supplier to boaentrega;
EOSQL
