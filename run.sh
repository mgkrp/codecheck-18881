#!/bin/bash

ROOT=$(cd $(dirname $0) && pwd)

### Java ###
java -jar $(ls $ROOT/java/fw/target/exam2a-framework-*.jar) "$@"

### Python ###
# python $ROOT/python/src/__pycache__/exam2a-fw.cpython-36.pyc "$@"

### NodeJS ###
# cd $(dirname $0)/js
# node $ROOT/nodejs/src/exam2a-fw.js "$@"
