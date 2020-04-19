mvn clean install
. 1b-extract-arn.sh
ECHO $LAMBDA_ROLE_ARN
LAMBDA_ROLE_ARN=$LAMBDA_ROLE_ARN sh manage.sh create