FROM kong/kong-gateway:2.8-alpine

USER root
ENV KONG_PLUGINS="bundled,jwt-keycloak"

RUN apk update && apk add git unzip luarocks

# https://github.com/gbbirkisson/kong-plugin-jwt-keycloak
RUN luarocks install kong-plugin-jwt-keycloak

USER kong