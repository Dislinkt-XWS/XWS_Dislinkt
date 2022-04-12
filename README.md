# XWS_Dislinkt


```javascript
TODO: Setup API Gateway with spring???
```


## Running the app
Install docker desktop
```bash
docker pull mongo:latest

docker pull mongo-express:latest

docker pull openjdk:11
```

Create a parent folder where all microservices will be stored. (e.g. XWSProject)

Pull Repo

Open the userservice project in IntelliJ and clean and install with maven.
![Picture of the maven build tool in IntelliJ](https://i.imgur.com/MuIDcGk.png)

Open command prompt in parent folder and run
```bash
docker compose up --build 
//This builds all the images again so that any code changes take effect
```

You should be able to access the userservice project endpoints at http://localhost:8761/api/users

MongoDB UI is running at http://localhost:8081/

Note:
After changing any code inside of a service it needs to be rebuilt with maven (see image above).
