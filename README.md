# transneft test task

<h2> About </h2>

 Spring boot backend Rabbitmq ampq message producer and consumer services example with postgresql db and angular react frontend

1. message producer represents standard web application which can create "orders" with "items" and manage all data with database.  
2. when user create an order from web view message producer generate the message about the order and send it to RabbitMQ server to direct exchange.  
3. message consumer listens direct exchange from RabbitMQ server and when message has been received consumer service send email alert about order details from google account. 

<h2> Docker and running the app</h2>

- you have only one option - to deploy all needed services in docker :)  
- make sure you have installed docker and docker compose
```bash
# should return valid output:
$ docker -v
$ docker compose --help
# or if it older version: $ docker-compose --help

# make sure your docker service is running:
$ sudo systemctl start docker
```
> maybe you want to check this guides to install and configure docker:  
> https://docs.docker.com/engine/install/linux-postinstall/  
> https://docs.docker.com/get-docker/  
> https://docs.docker.com/compose/install/
1. clone project
```bash
$ git clone https://github.com/owpk/trnf-test-task
$ cd trnf-test-task
```
2. exec script to invoke docker compose
```bash
$ ./run.sh

# or if you want to do it manually:
$ docker compose up
```
- first run should take some time about 3 - 5 minutes
- you should see something like this:
<p align="center">
  <img src="https://github.com/owpk/trnf-test-task/blob/master/github-img/dck.gif" title="docker startup"/>
</p>

3. go to url http://localhost/app
- you should see something like this:
<p align="center">
  <img src="https://github.com/owpk/trnf-test-task/blob/master/github-img/web.gif" title="web view" />
</p>

 - add items to cart and press "create order" button to send message to consumer service
 - you can edit ./db-docker/init.sql script and change email for "owpk" user to receive email message from consumer service  
    
 ensure if everything is work - you should see this two logs: 
<p align="center">
  <img src="https://github.com/owpk/trnf-test-task/blob/master/github-img/log.jpg" title="working" />
</p>
 
 and email message:
<p align="center">
  <img src="https://github.com/owpk/trnf-test-task/blob/master/github-img/msg.jpg" title="email" />
</p>

 <h2> Used frameworks / middleware soft / etc : </h2>

> Spring boot 2.5.5  
> Postgresql  
> Tomcat 9.0.53  
> RabbitMQ 3.8  
> docker compose 3.9  
> AngularJs