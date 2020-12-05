# football-league-app
This App will show Football League Standings...

Problem Statement

Develop, Test and Deploy a microservice to find standings of a team playing league football match using country name, league name and team name. The
service should be accessible via web browser on internet and end user should be able to view results by changing previously listed parameters. Output of
this service should be presented in web browser using any one of Javascript framework, HTML or JSON. And the service should be ready to be released
to production or live environment. In output, display following:
Country ID & Name: (<ID>) - <name>
League ID & Name: (<ID>) - <name>
Team ID & Name: (<ID>) - <name>
Overall League Position: <position>

URL To Hit The API From Local
http://localhost:8080/api/v1/team_standing?countryName=England&leagueName=Championship&teamName=Watford

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

