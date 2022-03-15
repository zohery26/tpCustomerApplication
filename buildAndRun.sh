#!/bin/sh
mvn clean package && docker build -t mg.zo/tpCustomerApplication .
docker rm -f tpCustomerApplication || true && docker run -d -p 9080:9080 -p 9443:9443 --name tpCustomerApplication mg.zo/tpCustomerApplication