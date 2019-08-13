#!/usr/bin/env bash

cd frontend
ng build --prod
rm ../src/main/resources/static/*
cp dist/frontend/* ../src/main/resources/static/.
cd ..