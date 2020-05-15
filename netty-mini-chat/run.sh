#!/usr/bin/env bash

echo "Running app.....";

java --module-path target/netty-mini-chat-1.0-SNAPSHOT.jar  --module netty.mini.chat/fr.codeworks.App
