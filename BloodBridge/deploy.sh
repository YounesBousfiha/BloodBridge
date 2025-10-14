#!/bin/bash

APP_NAME="BloodBridge"
TOMCAT_HOME="/opt/tomcat"
DEPLOY_DIR="$TOMCAT_HOME/webapps"
WAR_TARGET="target/${APP_NAME}.war"
STARTUP_SCRIPT="$TOMCAT_HOME/bin/startup.sh"
SHUTDOWN_SCRIPT="$TOMCAT_HOME/bin/shutdown.sh"


echo "Cleaning Previous build ..."
mvn clean package -DskipTests


if [ $? -ne 0 ]; then
	echo "Maven build failed. Aborting"
	exit 1
fi

echo "Stopping Tomcat..."
sudo bash "$SHUTDOWN_SCRIPT"
sleep 5

echo "Removing old deployement..."
sudo rm -rf "$DEPLOY_DIR/${APP_NAME}" "$DEPLOY_DIR/${APP_NAME}.war"

echo "Copying new WAR to Tomcat..."
sudo cp "$WAR_TARGET" "$DEPLOY_DIR/"

echo "Starting Tomcat..."
sudo bash "$STARTUP_SCRIPT"

echo "Deployement completed"
echo "Check logs: $TOMCAT_HOME/logs/catalina.out"
