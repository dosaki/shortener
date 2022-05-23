#!/bin/bash

./mvnw package || exit 1

pushd frontend/shortener
npm run build || exit 1
popd

docker build -f frontend/shortener/frontend.dockerfile -t dosaki/shortener-frontend frontend/shortener/ || exit 1
