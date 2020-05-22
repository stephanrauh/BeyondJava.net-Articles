
echo "Native node.js"
node --version
node ./src/main/javascript/speedtest.js

#!/bin/bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.1.0/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
java -version

mvn clean install

echo "GraalVM 20.1.0, Java 11.0.7:"

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar

echo "AdoptOpenJDK 11.0.5:"

export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home 
export PATH=$JAVA_HOME/bin:$PATH
java -version

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar

echo "AdoptOpenJDK 14.0.5:"

export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
java -version

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar

