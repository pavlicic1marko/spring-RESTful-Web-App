# spring-RESTful-Web-App
Project for learning Spring


--InitializingDefaultUsers class creates 2 users on Application Ready Event
username:"adminuser@mail.com"  password:"1234" role:"roleAdmin"
username:"regularuser@mail.com" password:"1234" role:"regularUser"


--Login
HTTP POST request: 
Base url + "/users/login"
request body:
{
	"email":"adminuser@mail.com",
	"password":"1234"
}


--Get Requests
HTTP GET requests:
copy (prefix +token) "Bearer + Token" from the response header and add to the the Get request Authorization Header


--Swager urls are
Base url + "/v2/api-docs"
Base url + "/swagger-ui.html"


--H2 Database:
Base URL + "h2-console"


--War name:
spring-RESTful-Web-App

