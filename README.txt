app-authz-rest-springboot: SpringBoot REST Service Protected Using Keycloak Authorization Services
===================================================

Level: Beginner
Technologies: SpringBoot
Summary: SpringBoot REST Service Protected Using Keycloak Authorization Services
Target Product: Keycloak
Source: <https://github.com/keycloak/Keycloak-quickstarts>


What is it?
-----------

The `app-authz-rest-springboot` quickstart demonstrates how to protect a SpringBoot REST service using Keycloak Authorization Services.

This application tries to focus on the authorization features provided by Keycloak Authorization Services, where resources are
protected by a set of permissions and policies defined in Keycloak itself and access to these resources are enforced by a policy enforcer
that intercepts every single request to the application.

In this application, there are three paths protected by specific permissions in Keycloak:

* **/api/{resource}**, where access to this page is based on the evaluation of permissions associated with a resource **Default Resource** in Keycloak. Basically,
any user with a role *user* is allowed to access this page. Examples of resource that match this path pattern are: "/api/resourcea" and "/api/resourceb".

* **/api/premium**, where access to this page is based on the evaluation of permissions associated with a resource **Premium Resource** in Keycloak. Basically,
only users with a role *user-premium* is allowed to access this page.

You can use two distinct users to access this application:

|Username|Password|Roles|
|---|---|---|
|alice|alice|user|
|jdoe|jdoe|user, user-premium|

System Requirements
-------------------

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later.


Configuration in Keycloak
-----------------------

Prior to running the quickstart you need to create a `realm` in Keycloak with all the necessary configuration to deploy and run the quickstart.

The following steps show how to create the realm required for this quickstart:

* Open the Keycloak admin console
* In the top left corner dropdown menu that is titled `Master`, click `Add Realm`. If you are logged in to the master realm this dropdown menu lists all the realms created.
* For this quickstart we are not going to manually create the realm, but import all configuration from a JSON file. Click on `Select File` and import the [config/realm-import.json](config/realm-import.json).
* Click `Create`

The steps above will result on a new `spring-boot-quickstart` realm.

Build and Run the Quickstart
-------------------------------

Make sure your Keycloak server is running on <http://localhost:8180/>. For that, you can start the server using the command below:

   ````
   cd {KEYCLOAK_HOME}/bin
   ./standalone.sh -Djboss.socket.binding.port-offset=100
   
   ````

If your server is up and running, perform the following steps to start the application:

1. Open a terminal and navigate to the root directory of this quickstart.

2. The following shows the command to deploy the quickstart:

   ````
   mvn spring-boot:run

   ````

Access the Quickstart
---------------------

In order to understand better what is happening behind the scenes, we describe some steps using Curl to exemplify 
how clients can get access to the RESTful resources provided by this application.

First thing, your client needs to obtain an OAuth2 access token from a Keycloak server.

```bash
curl -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
  -H 'Authorization: Basic YXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdDpzZWNyZXQ=' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'username=alice&password=alice&grant_type=password'
```

After executing the command above, you should get a response similar to the following:

```bash
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJGSjg2R2NGM2pUYk5MT2NvNE52WmtVQ0lVbWZZQ3FvcXRPUWVNZmJoTmxFIn0.eyJqdGkiOiI3OWY4NmFjZS01Zjk4LTQ0MTctYWJmZC0xMjcyOGQ2OGJkNDEiLCJleHAiOjE1MDQxOTE5MzYsIm5iZiI6MCwiaWF0IjoxNTA0MTkxNjM2LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiJlNmE3NzcyYS1kZmZlLTRiNDItYTFiMS0zZDZmOTM0OWE0NmIiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiNzE4MmU0YzEtNzY5ZS00MTNlLWI2MWItM2FlZTFjYWZmY2JmIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6e30sInByZWZlcnJlZF91c2VybmFtZSI6ImFsaWNlIn0.bpKwmY2CqEm1TLYZoC_6jG0V1XcLKC2dStTAnUJgUMQfTBn3kZHsrWZeahKq7IdVocn7bWoBU0mP8i0rf89GcoZS1j-oju32XArTtE2e-tWVeWaRa1vJHNjhsIAuvZ4CmRh6QOTa-0qowbi1oEZxL3aQ6jPL4OSjBOAJgS51tn4",
    "expires_in":300,
    "refresh_expires_in":1800,
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJGSjg2R2NGM2pUYk5MT2NvNE52WmtVQ0lVbWZZQ3FvcXRPUWVNZmJoTmxFIn0.eyJqdGkiOiI2Mzk3MDRhOS1jYTg1LTQxOWYtODA5Yi03MDkzOGQyNzQwNTQiLCJleHAiOjE1MDQxOTM0MzYsIm5iZiI6MCwiaWF0IjoxNTA0MTkxNjM2LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiJlNmE3NzcyYS1kZmZlLTRiNDItYTFiMS0zZDZmOTM0OWE0NmIiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoiYXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjcxODJlNGMxLTc2OWUtNDEzZS1iNjFiLTNhZWUxY2FmZmNiZiIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnt9fQ.CV3tZfYylhXMK7F0ozf-u4AZx_edm0FhdIDYiRDbhM3trRFzmpaRtuwML2KethVqj01PhD0VYYjt2yK0GWgkFswW1tc-Rqq2TMjabeGTcLPwvb8NZ7FcZnwglZUU46mfuV8-m1Idzqgs5DhmpkBALkAXjeaVPedAMNsPFQSPJE4",
    "token_type":"bearer",
    "not-before-policy":0,
    "session_state":"7182e4c1-769e-413e-b61b-3aee1caffcbf"
}
```
Now replace the ``${access_token}`` variable below with the value of the ``access_token`` claim from the response. 

```bash
curl -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
  -H "Authorization: Bearer ${access_token}" \
  --data "grant_type=urn:ietf:params:oauth:grant-type:uma-ticket" \
  --data "audience=app-authz-rest-springboot" \
  --data "permission=Default Resource"
```

As an alternative, you can also obtain permissions for any resource protected by your application. For that, execute the command below:

```bash
curl -X GET \
    http://localhost:8180/auth/realms/spring-boot-quickstart/authz/entitlement/app-authz-rest-springboot \
    -H "Authorization: Bearer ${access_token}"
```

After executing the command above, you should get a response similar to the following:

```bash
{
    "access_token": "${rpt}",
}
```

Where `${rpt}` is an access token with all permissions granted by the server, also known as Requesting Party Token or RPT for short.

Finally, you are able to access the RESTFul resources protected by this application with an RPT. For that, replace the ``${rpt}`` variable below with the value of the ``access_token`` claim from the response.

```bash
curl -X GET \
  http://localhost:8080/api/resourcea \
  -H "Authorization: Bearer ${rpt}"
```

User *alice* should be able to access */api/resourcea* and you should get **Access Granted** as a response. Now, if you try to access */api/premium* as *alice* you should
get a **403** HTTP status code.

You should also get a **403** HTTP status code if you try to obtain permissions from Keycloak for `Premium Resource`, given that
user *alice* is not allowed to access `Premium Resource`.

```bash
curl -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
  -H "Authorization: Bearer ${access_token}" \
  --data "grant_type=urn:ietf:params:oauth:grant-type:uma-ticket" \
  --data "audience=app-authz-rest-springboot" \
  --data "permission=Premium Resource"
```

As a response you should get something similar to the following:

```bash
{"error":"access_denied"}
```

You can follow the same steps to check behavior when accessing the same resources with user *jdoe*.

Integration test of the Quickstart
----------------------------------

1. Make sure you have an Keycloak server running with an admin user in the `master` realm or use the provided docker image
2. Be sure to set the `TestHelper.keycloakBaseUrl` in the `createArchive` method (default URL is localhost:8180/auth).
3. Set accordingly the correct url for the `keycloak.auth-server-url` in the test [application.properties](src/test/resources/application.properties).
4. Run `mvn test -Pspring-boot`


----
GET PAT
curl -X POST \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d 'grant_type=client_credentials&client_id=app-authz-rest-springboot&client_secret=84a547e6-af80-4c1e-8a49-7b46e2c4d302' \
    "http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token"

UPDATE OWNERSHIP (8.4.2. Managing Resources)

curl -v -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/authz/protection/resource_set \
  -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXUkQ3dnEwY2ZXTTJ0UFV1UTc5bjE2dF9fc0hUUF8zdWhQeF9PZFpuN2FVIn0.eyJqdGkiOiI3YmM5NjM2Yi0wZjVjLTQ4YzctOTQzNS0zNDBkMmFiOTE3YmUiLCJleHAiOjE1MzE4NTEzNzQsIm5iZiI6MCwiaWF0IjoxNTMxODUxMDc0LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiJhMzE5YzA1Zi04MDgxLTRmNDgtOTY5My00MzJiOTUwYzYxMWQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiODk0NDdkYzgtMzFkNC00MDAxLTk0ZWUtNTQyMDk3OTgxNWE4IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290Ijp7InJvbGVzIjpbInVtYV9wcm90ZWN0aW9uIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiYXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdCIsImNsaWVudEhvc3QiOiIxMjcuMC4wLjEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtYXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdCIsImNsaWVudEFkZHJlc3MiOiIxMjcuMC4wLjEiLCJlbWFpbCI6InNlcnZpY2UtYWNjb3VudC1hcHAtYXV0aHotcmVzdC1zcHJpbmdib290QHBsYWNlaG9sZGVyLm9yZyJ9.OPuQMP7N1UV3HSuneszeKYTvchjSMFddMWC5dJZ9loWwj-rkVx192HeLCuydUA2Sf_kEXosccVquRj0K1AZs1zmh6R9YcKGr3PDixXa1hDh1rRwqxhQrUP66MQGEoX-QXBybtWmWACGJOf24aZ0IKy9YFG7o12HPuHoq6usQqojC3A5dietDYdmSaKpbPa7ijYOdwtJAYh9YnPinor85AQaGtn3sZwMdO7n5auwi5ptCcPCWCcze85mNk766N3hPt-mcPVMcuNIu5v0IapSTJJdc2ujneJ2scKnCfioM4wYGkVCJvh_w3LMtCi63DqfkFjrt0k9VCjPLKtvdtNJxhw' \
  -H 'Content-Type: application/json' \
  -d '{
     "name":"JDoes Resource",
     "owner": "jdoe",
     "ownerManagedAccess": true
  }'

curl -v -X PUT \
  http://localhost:8180/auth/realms/spring-boot-quickstart/authz/protection/resource_set/a0c3b731-ec2b-427b-ab1d-29e1e78b78a3 \
  -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXUkQ3dnEwY2ZXTTJ0UFV1UTc5bjE2dF9fc0hUUF8zdWhQeF9PZFpuN2FVIn0.eyJqdGkiOiI2MzY3MzY0ZS0zMGIyLTQxMTUtYTlkNy04ZmRlN2RjMzhiNmUiLCJleHAiOjE1MzE4NTA5NzcsIm5iZiI6MCwiaWF0IjoxNTMxODUwNjc3LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiJhMzE5YzA1Zi04MDgxLTRmNDgtOTY5My00MzJiOTUwYzYxMWQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiYWY3OTc1YTYtYmZjOC00YmE5LTg4YTgtZjM2YThhMmI4MDMwIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290Ijp7InJvbGVzIjpbInVtYV9wcm90ZWN0aW9uIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiYXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdCIsImNsaWVudEhvc3QiOiIxMjcuMC4wLjEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtYXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdCIsImNsaWVudEFkZHJlc3MiOiIxMjcuMC4wLjEiLCJlbWFpbCI6InNlcnZpY2UtYWNjb3VudC1hcHAtYXV0aHotcmVzdC1zcHJpbmdib290QHBsYWNlaG9sZGVyLm9yZyJ9.AEXMsX34CKIfGThJ2IyJ3G3O0uuw7ebKcREhRxDVohsnVp9r2OynVeVLOHcpfWQlN6_F4vpeyCqRN8y3mRSEpkGK9XEmSRnzeHRxvUigRmPDCkX9snQb-EB7s7Ydx-ewwQKTjwNd-kElz5EqNjZQFDvwip10jjjpR_k-7LFtBZ7R0GEvV-UyMr-fR30PTcTjhUGdjqSCvQRHrDfTlSM0or-uwOSjmj9_e-M0ZruRcNbFKNyYQv-2JGko3lBA61073FTYYz3TFjozwWLREU6SIkKbAb3BpzgF0TYhd-oB03LcmZ5tEGXPZdM8KRk8Eojb5i-Mm-Op3k_apQtFYG6chQ' \
  -H 'Content-Type: application/json' \
  -d '{
     "name":"Joes stuff",
     "owner": {
         "name": "jdoe"
     },
     "ownerManagedAccess": true
  }'  

  try jdoes stuff MANAGEMENT

  curl -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
  -H 'Authorization: Basic YXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdDpzZWNyZXQ=' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'username=jdoe&password=jdoe&grant_type=password'

  curl -v http://localhost:8080/api/belongtojdoe -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJGSjg2R2NGM2pUYk5MT2NvNE52WmtVQ0lVbWZZQ3FvcXRPUWVNZmJoTmxFIn0.eyJqdGkiOiI2NjM5NDE2OC05NGY0LTRjOWItOTk2NC1mNzlmYTY4ZWZkOTEiLCJleHAiOjE1MzE4MDg0OTYsIm5iZiI6MCwiaWF0IjoxNTMxODA4MTk2LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiIxN2U1OTkyNi03NTM1LTRkNjItYTEyOS05NTA1NWQxYTlkNDMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiOWJmYmFjNGUtNTllYy00YTU5LWFkODYtYzk0MGFiNGIxYThlIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyLXByZW1pdW0iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJqZG9lIiwiZW1haWwiOiJqZG9lQGVtYWlsLmNvbSJ9.jtnNGrAZm7_b8iChy78uvNTysqag2A0nsNfNzLf66wswg9eUWLpeO_jYY50hpSPyacUaIhlbeqWHmCNTjGroebXb6VqmrYJyxEDVvSvj2quPqWET2Pu60xCvo8XZqZAoqPY8Rig7pnjs0gswijOx01UDduig4hZ_-DbSIIPVQ_Q'


curl -v -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
  -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJGSjg2R2NGM2pUYk5MT2NvNE52WmtVQ0lVbWZZQ3FvcXRPUWVNZmJoTmxFIn0.eyJqdGkiOiI5ZDJiZjEwMi1lMzU2LTRlZWItYjgwMC1kNWM3YWQ4YzcyZjYiLCJleHAiOjE1MzE4MDgzNDksIm5iZiI6MCwiaWF0IjoxNTMxODA4MDQ5LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiIxN2U1OTkyNi03NTM1LTRkNjItYTEyOS05NTA1NWQxYTlkNDMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiMWU4MGQ2YTctYmFhNS00Mjg0LWJlMmItZWI1ZmU4ZmRjMjFiIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyLXByZW1pdW0iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJqZG9lIiwiZW1haWwiOiJqZG9lQGVtYWlsLmNvbSJ9.IwiKLr81TEhIOXTfpYCeU1tinLAT9VKg422mT0rWdHjh4p8GQCVliqHsaftrKSLxSg1QaT9i0mvIWMo_GTcezdrfGUskAj3EdS12eUxsD9Oapz4QwRAMppyH-hMbxXNbrE-mRGf6_aF6xmaY0yHZeJBC5y3m22FzluLo1-k6JhY" \
  --data "grant_type=urn:ietf:params:oauth:grant-type:uma-ticket" \
  --data "audience=app-authz-rest-springboot" \
  --data "permission=Joes stuff"

REQUEST Access

curl -X POST \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d 'grant_type=client_credentials&client_id=app-authz-rest-springboot&client_secret=84a547e6-af80-4c1e-8a49-7b46e2c4d302' \
    "http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token"

curl -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/authz/protection/permission \
  -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXUkQ3dnEwY2ZXTTJ0UFV1UTc5bjE2dF9fc0hUUF8zdWhQeF9PZFpuN2FVIn0.eyJqdGkiOiI0MmI3NWZjNC01MWVhLTQyZmUtOWVjNC01YTk4MGQ4YmZkZWYiLCJleHAiOjE1MzE4NzI2NDEsIm5iZiI6MCwiaWF0IjoxNTMxODcyMzQxLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiJhMzE5YzA1Zi04MDgxLTRmNDgtOTY5My00MzJiOTUwYzYxMWQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiMWNkNWI5OTAtNWRmNi00OWQ1LWI0NTMtZjczNGI4NmRlNjQzIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290Ijp7InJvbGVzIjpbInVtYV9wcm90ZWN0aW9uIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiYXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdCIsImNsaWVudEhvc3QiOiIxMjcuMC4wLjEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtYXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdCIsImNsaWVudEFkZHJlc3MiOiIxMjcuMC4wLjEiLCJlbWFpbCI6InNlcnZpY2UtYWNjb3VudC1hcHAtYXV0aHotcmVzdC1zcHJpbmdib290QHBsYWNlaG9sZGVyLm9yZyJ9.EIgL5r571ZJFQAIcMe650rOymhpcnTxho3ThSjHS_Ihj60Uhi-YBqCgAnWs9OCClD4w5IfyG81nkF_bb_IaPQhE_ExWLfqLl7fstT0EEfQZK1L9xmHmml-o_N6SifMEirZ527IqLBEzhQBUZ0GVjj4p7IlaIj6KibFNnaeTgsCi2G39po1KcoNvI8aBK93jVjgqOPiFucUR6bKZKHpVUCWJtLaKAUncwR0c8-f44beT2NZqKdJmZuOPCwwwilbOU9Utm3AGGdTD7xOXB5Kdn54C0ITbRqxZLZ5DhS97yew8oMj2VaVSAvb6bTAlbYWY39YsMvI6SahIpZu2YaJPLWA' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "resource_id": "5b81893b-2d9f-4940-b787-ea01b213f6bd"
  }
]'

curl -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
  -H 'Authorization: Basic YXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdDo4NGE1NDdlNi1hZjgwLTRjMWUtOGE0OS03YjQ2ZTJjNGQzMDI=' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'username=jdoe&password=jdoe&grant_type=password'

curl -v http://localhost:8080/api/belongtojdoe -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXUkQ3dnEwY2ZXTTJ0UFV1UTc5bjE2dF9fc0hUUF8zdWhQeF9PZFpuN2FVIn0.eyJqdGkiOiI0ZDFlOTZiZC0xOWRjLTRmZTctYmRlMy1mM2YzNTA1YTQ0N2UiLCJleHAiOjE1MzE4NzI3NDAsIm5iZiI6MCwiaWF0IjoxNTMxODcyNDQwLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiI1MGNmOWRlMy1kNjFjLTQzNmEtYWE0NC0wZTIxZDAzOWZjODkiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiNjc1ZDc0YWMtNWQzMy00NzQ4LThiNjktZDk3MDBjMzdhYzMzIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhbGljZSIsImVtYWlsIjoiYWxpY2VAZW1haWwuY29tIn0.EDH05x1P5qcvjm-yQZ1SjEnZl9PRieWvs39-0h0Hjx7QsBnZzld4uPN7RC8ZVyxbM-dMP_UkVkYd4D2VZ87c9j066Np7FS50-01yug3CzNnDi0XuRpEfUUHp94w8oAkpXPoOr9IE5bMcWIJy6lu6eGAD7kjXfETaiOSMwkPC7Pn9yhNSrdctLmbc3EjbcB4uVFpGmPK7Fd92JkiEfhtDVkTaroPfyR82c_zliwGmnBc3LARpcApz95DBWlDp6KdWjRmGzRrO-ndvkPfvWphm5p0j_887xZ0f450qOcOiobb3Z6e-9oG_RwArIy7cwW55dQZANj712ig-VPGVz4no6w'

curl -X POST \
  http://localhost:8180/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
  -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXUkQ3dnEwY2ZXTTJ0UFV1UTc5bjE2dF9fc0hUUF8zdWhQeF9PZFpuN2FVIn0.eyJqdGkiOiJmZmM2NmQ3Yi00ZGNlLTQzZDUtOWRlZS1lOGQ2ZmRkNjU2YTQiLCJleHAiOjE1MzE4NzI2ODIsIm5iZiI6MCwiaWF0IjoxNTMxODcyMzgyLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiI1MGNmOWRlMy1kNjFjLTQzNmEtYWE0NC0wZTIxZDAzOWZjODkiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiNWI0MDE0NmUtYmU0OC00YjE5LTlkYmMtN2Y5ZjQ0YTIxMjE1IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhbGljZSIsImVtYWlsIjoiYWxpY2VAZW1haWwuY29tIn0.TicDUoTjqmpVjg795bEDnA3E5SM2FlG0YTGsaqespZOGqwxCcAhzlpyFokOl14ArcmD2VgynkzpWmQihnr6hF1NpJOwmbUdQVbS4qwgMbcVMhTVKMrpm9ph_z46JT8vOjecL_IRJyhQLFNCS3eR8yy_9opTIV5FfcohTFbaPD-XTSqYxFHAcGQBduuT-Cj__ytn72fl3Z7SkETltkZkYA2dGP16g6taoLZi7E5uvxtKXYdjDXb3PcIntJZX-0ZoA9BmI-UjjixSIUTIBJO5D7KWcaZ3lzlgdpRXooaACLf9FLpkhsHsjPzGg4CdnbLqr0wEQYtY5fJSM0mWV4-QmuA" \
  --data "grant_type=urn:ietf:params:oauth:grant-type:uma-ticket" \
  --data "ticket=eyJhbGciOiJSUzI1NiIsImtpZCIgOiAiV1JEN3ZxMGNmV00ydFBVdVE3OW4xNnRfX3NIVFBfM3VoUHhfT2RabjdhVSJ9.eyJyZXNvdXJjZXMiOlt7ImlkIjoiNWI4MTg5M2ItMmQ5Zi00OTQwLWI3ODctZWEwMWIyMTNmNmJkIiwic2NvcGVzIjpbXX1dLCJqdGkiOiIxZDU3ZmMxZC01YjA4LTRlOTctODM0Yy0zNWE5NGRhY2JhNjUtMTUzMTg3MjM2MTc2NyIsImV4cCI6MTUzMTg3MjY0MSwibmJmIjowLCJpYXQiOjE1MzE4NzIzNDEsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiJhMzE5YzA1Zi04MDgxLTRmNDgtOTY5My00MzJiOTUwYzYxMWQiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290In0.flxI_4BSekI38HTR44NdSZYOAa5RXhceFoJldIZcpmtO2LisLcu658poqcPuPdokAzXeFM4nzTHF_h0GERZTlKbDzKnapvCItlxCm0U12DfRgyK9qJWnt1krHXirXY-YPglk63p-DhbvcNfqnRuO_Ulbx88QXzd9DNtreKbHQnkQnqR87LXUyLrcfkmYcmRYjrncmaeKV1kqwaIIFVWdXhAZt4PqW44JBdfc6JdYOVHLMxD6JQ0jnAKqEAl756DDKZDmmIE0eWDhLdo1NoZ78ka_wh9tjURx9XFWfsuZ0mETjWq3-BmxaiEy7W5ZP1jjdomJwaSkjrEv38FndQ7Nvg" \
  --data "submit_request=true"

curl -v http://localhost:8080/api/premium -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXUkQ3dnEwY2ZXTTJ0UFV1UTc5bjE2dF9fc0hUUF8zdWhQeF9PZFpuN2FVIn0.eyJqdGkiOiJiZTBkYWM5My1mYTQ1LTRhZDQtOWI4Ny0xNGJlY2I2OWY2MzAiLCJleHAiOjE1MzE4NzQ0NDgsIm5iZiI6MCwiaWF0IjoxNTMxODc0MTQ4LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWJvb3QtcXVpY2tzdGFydCIsImF1ZCI6ImFwcC1hdXRoei1yZXN0LXNwcmluZ2Jvb3QiLCJzdWIiOiI3YjIxYzBjMS1hYjlmLTRlMzUtODM3Mi1iNzY0NjZlM2Q2NDEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHAtYXV0aHotcmVzdC1zcHJpbmdib290IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZThkNDIxYmItYTQyMy00M2VlLWE1MzMtMzI1NGVhYzQ0OGU2IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyLXByZW1pdW0iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJqZG9lIiwiZW1haWwiOiJqZG9lQGVtYWlsLmNvbSJ9.bxpetGlrJ3SeBpVApAi_D6dSHqvzaJlLP5lob8AHMk0CWiDx-inhulYZ3BM3FOSTZbuvUnB12HSxiWBJTlGidFOOcIJiUEY1t2pcAsnUx8zZ4PCmSBecG3cGwUV2TskOOg5LyuGGJBslJLawejRoO9QgxKsv_Y-9K-iPHJJ95I7MPgGbx07DEYKZhfCycys_QjrBbFjX6VC1EXu-7ih8KrvJmhkrZIzon8k0Kof8o2jAVI1MZ0B61UPEIvoLHd1ORPn560FYneejRHuRtOv9QGCCYJIMs7AYaqvCnXntddjN6G-HbT9eSB76dvI3UavXAN-fzPwdhVSHWZQXGo0t4g'