
echo "Native node.js"
node --version > results-native-node.js-14.16.0.txt
node ./src/main/javascript/speedtest.js > results-native-node.js-14.16.0.txt

#!/bin/bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ee-java11-21.0.0.2/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
java -version > results-GraalVM-CE-21.0.0.2.txt

mvn clean install

echo "GraalVM CE 21.0.0.2, Java 11.0.10:"

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar > results-GraalVM-CE-21.0.0.2.txt

export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-21.0.0.2/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
java -version > results-GraalVM-EE-21.0.0.2.txt

mvn clean install

echo "GraalVM EE 21.0.0.2, Java 11.0.10:"

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar > results-GraalVM-EE-21.0.0.2.txt


echo "AdoptOpenJDK 11.0.10+9:"

export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home 
export PATH=$JAVA_HOME/bin:$PATH
java -version > results-AdoptOpenJDK-11.0.10+9.txt

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar > results-AdoptOpenJDK-11.0.10+9.txt

echo "AdoptOpenJDK 11.0.10 J9:"

export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-11-openj9.jdk/Contents/Home 
export PATH=$JAVA_HOME/bin:$PATH
java -version > results-AdoptOpenJDK-11.0.10-with-J9-compiler.txt

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar > results-AdoptOpenJDK-11.0.10-with-J9-compiler.txt

echo "AdoptOpenJDK 16:"

export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-16.jdk/Contents/Home 
export PATH=$JAVA_HOME/bin:$PATH
java -version > results-AdoptOpenJDK-16+36.txt

java -jar ./target/rhino-nashorn-graalvm-speedtest-1.0-SNAPSHOT-jar-with-dependencies.jar > results-AdoptOpenJDK-16+36.txt
