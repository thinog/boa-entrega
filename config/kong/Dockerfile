# https://faun.pub/kong-jwt-scope-base-auth-with-keycloak-b9938a96d281
# https://github.com/gbbirkisson/kong-plugin-jwt-keycloak

FROM kong/kong-gateway:2.8-alpine

USER root
ENV KONG_PLUGINS="bundled,jwt-keycloak"

RUN apk update && apk add git unzip luarocks
RUN luarocks install kong-plugin-jwt-keycloak

USER kong