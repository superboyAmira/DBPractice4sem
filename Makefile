DC_NAME=docker-compose.yml

UP_AUTOREFRESH_FLAG=--force-recreate
PGDATA_LIB=pgdata/

up:
	docker-compose -f $(DC_NAME) up

rm:
	docker-compose stop \
	&& docker-compose rm
# 	&& sudo rm -rf $(PGDATA_LIB)
