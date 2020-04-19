openssl base64 -out encoded-payload.json -in payload.json
. 1b-extract-arn.sh

FUNCTION_NAME=simple-quarkus-test-function
HANDLER=io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest


# set -x
RESULT=$(aws lambda invoke response.txt \
    --function-name ${FUNCTION_NAME} \
	--payload file://encoded-payload.json \
	--log-type Tail \
	--query 'LogResult' \
	--output text |  base64 -D) 
	
echo $RESULT | grep -E -o '(Duration.+$)'

cat response.txt
