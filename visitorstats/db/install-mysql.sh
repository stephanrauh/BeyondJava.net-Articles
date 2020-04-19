docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=topsecret -d mysql:latest 
docker exec mysql /bin/sh -c 'mysql -u root -ptopsecret < ./create-database.sql'
docker exec mysql /bin/sh -c 'mysql -u root -ptopsecret < ./populate-table.sql'