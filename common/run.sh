#!/bin/sh

cd $(dirname $0)

cd ../auth-center

mvn clean install

cd ../edge-service

mvn clean install

cd ../eureka-service

mvn clean install

cd ../modulo-controle-coleta-definicao-cargas

mvn clean install

cd ../modulo-controle-frete

mvn clean install

exit
