#!/bin/sh
gradle build
rm test/plugins/TBT-Lobby.jar
mv build/libs/TBT-Lobby.jar test/plugins/
echo "Done!"
