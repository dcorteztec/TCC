#!/bin/sh

cd $(dirname $0)

cd ../auth-center

mvn clean install

exit
