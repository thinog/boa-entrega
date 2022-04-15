.PHONY: init
init:
	docker-compose up --build --detach


.PHONY: build
build:
	docker-compose build


.PHONY: run
run:
	docker-compose up --detach