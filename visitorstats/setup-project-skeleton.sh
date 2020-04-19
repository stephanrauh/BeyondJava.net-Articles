mvn io.quarkus:quarkus-maven-plugin:1.3.2.Final:create \
    -DprojectGroupId=de.beyondjava \
    -DprojectArtifactId=visitorstats \
    -DclassName="de.beyondjava.statistics.visitorResource" \
    -Dpath="/visitors" \
    -Dextensions="resteasy-mutiny, resteasy-jsonb, reactive-mysql-client"