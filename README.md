# XWS_Dislinkt


```javascript
TODO: Setup newsfeed for specific user
TODO: Create a bash file that'll automatically clean install your projects
```


## Running the app
Install docker desktop
```bash
docker pull mongo:latest

docker pull mongo-express:latest

docker pull openjdk:11

docker pull nginx:latest
```

Create a parent folder where all microservices will be stored. (e.g. XWSProject)

Pull Repo

Open the all of the projects in IntelliJ and clean and install with maven.
![Picture of the maven build tool in IntelliJ](https://i.imgur.com/MuIDcGk.png)

Open command prompt in parent folder and run
```bash
docker compose up --build 
//This builds all the images again so that any code changes take effect
```
If you run into an issue where the changes you've made to your services are not taking place inside of docker, delete the current container inside of docker desktop and delete all of the microservice images, and then run the command above. 

You should be able to access the endpoints:
```javascript
Post Micoservice running at http://localhost:8750/api/posts
Message Micoservice running at http://localhost:8700/api/messages
User Micoservice running at http://localhost:8761/api/posts

All of the microservices can now be accessed through 
http://localhost:8080
as the gateway has been added. The rest of the url stayed the same /api/endpoint-name

```

MongoDB UI is running at http://localhost:8081/

Note:
After changing any code inside of a service it needs to be rebuilt with maven (see image above).
