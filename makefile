test:
	docker build -t backend -f ./phone-number-listing-backend/BackEndDockerfile ./phone-number-listing-backend && docker run -it --entrypoint ./test-backend-entry-point.sh backend

run:
	docker build -t backend -f ./phone-number-listing-backend/BackEndDockerfile ./phone-number-listing-backend &&\
	docker build -t frontend  -f ./phone-number-listing-frontend/FrontEndDockerfile ./phone-number-listing-frontend &&\
	docker-compose up

stop:
	docker-compose down 