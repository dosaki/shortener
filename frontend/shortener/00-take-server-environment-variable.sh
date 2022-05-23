#!/bin/bash

cd /usr/share/nginx/html/
echo "{
  \"backendUrl\": \"${BACKEND_URL}\"
}" > config.json