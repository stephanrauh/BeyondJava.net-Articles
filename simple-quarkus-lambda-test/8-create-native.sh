mvn clean install -Pnative -Dnative-image.docker-build=true
. 1b-extract-arn.sh
LAMBDA_ROLE_ARN=$LAMBDA_ROLE_ARN  sh manage.sh native create