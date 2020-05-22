## Test setup for Nashorn benchmark

See the article [Nashorn: The New Rhino on the Block](https://ariya.io/2014/03/nashorn-the-new-rhino-on-the-block) for more details.

Test with V8 (Node.js):
```
node speedtest.js
```
Test with Nashorn (Java >= 8):
```
javac -classpath ./rhino.jar speedtest.java
java -classpath ./rhino.jar:. speedtest
```
