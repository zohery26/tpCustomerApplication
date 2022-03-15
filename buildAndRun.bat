@echo off
call mvn clean package
call docker build -t mg.zo/tpCustomerApplication .
call docker rm -f tpCustomerApplication
call docker run -d -p 9080:9080 -p 9443:9443 --name tpCustomerApplication mg.zo/tpCustomerApplication