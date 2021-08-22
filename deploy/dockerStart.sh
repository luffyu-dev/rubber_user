#!/bin/bash
echo hello
docker build -t rubber-user:v1 .
docker run --name rubber-user-1.0 -p 38001:38001  -d  rubber-user:v1

