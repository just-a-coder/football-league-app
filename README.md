# football-league-app
This App will show Football League Standings...

-------------------------------------------------------------------------------------
How To Run App

Step 1: Inside Project There is one folder : application_docker_image
Inside that there is one tar file(football-league-app) that is actually docker image of the application.
Copy/Use that Tar file.

Step 2: Loading The Tar File into Local Docker
Start Local Docker and Execute:
docker load -i football-league-app

Step 3: Run The Application
Execute:
docker run -p 8090:8080 -t justacoder7/football-league-app .

Step 4:
Hit URL:
http://localhost:8090/api/v1/team_standing?countryName=England&leagueName=Championship&teamName=Watford

You should Receive Output Like
Response(In Snake Case)
{
    "country_id": 41,
    "country_name": "England",
    "league_id": 149,
    "league_name": "Championship",
    "team_id": 2623,
    "team_name": "Watford",
    "overall_position": 7
}

----------------------------------------------
Otherwise You can Build Image and Run The App
docker build --build-arg JAR_FILE=build/libs/*.jar -t justacoder7/football-league-app .
docker run -p 8090:8080 -t justacoder7/football-league-app .



Problem Statement
-----------------
Develop, Test and Deploy a microservice to find standings of a team playing league football match using country name, league name and team name. The
service should be accessible via web browser on internet and end user should be able to view results by changing previously listed parameters. Output of
this service should be presented in web browser using any one of Javascript framework, HTML or JSON. And the service should be ready to be released
to production or live environment. In output, display following:
Country ID & Name: (<ID>) - <name>
League ID & Name: (<ID>) - <name>
Team ID & Name: (<ID>) - <name>
Overall League Position: <position>


------------------------------------------------------
Technology Used

Coding : Java, SpringBoot 
Unit Test : Spock Framework, Groovy

Jenkins For Pipeline
DockerHub For Storing Docker Image
