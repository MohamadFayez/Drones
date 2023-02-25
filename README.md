# Drones Delivery System

## Table of contents
* [General info](#general-info)
* [PreRequisites](#prerequisites)
* [Technologies](#technologies)
* [Setup](#setup)
* [Assumptions](#assumptions)

## General info
```
This project is for new technology that is destined to be a disruptive force in the field of transportation 
which is the drone.Just as the mobile phone allowed developing countries to leapfrog older technologies for 
personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.
Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult
access.This one will be used to transport medication.
The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.
```

## PreRequisites
```
Installation of Java Sdk preferbly Java 11 to run the project
Install Maven in your machine to use the Maven Commands to run the application
```
## Technologies
```
This Project is created with:
* Java 11
* Maven
* Tomcat Server
* SpringBoot Framework with Spring Data JPA
* H2 Database(in memory)
* REST API
```
	
## Setup
```
To run this project locally, clone it from GitHub on (https://github.com/MohamadFayez/drones-delivery-system)

To run the Project
$ mvn spring-boot:run

To clean the project
$ mvn clean

To test the project tests scenarios
$ mvn test

To create JarFile /Build the project
$ mvn clean install   or mvn package
```

## Assumptions
```
The following assumptions were made made during development
* The relationship between drone and medication is a one-to-many relationship
* The drones will have unique serial numbers.
* The drones data initialized by data.sql file
* The drone that will be Loaded is one with >= 25% battery Level and also is on IDLE State
* No drone will be loaded with medication that is greater than its maximum weight it can carry.
```


## Swagger-ui
```
 http://localhost:8080/swagger-ui/index.html

```
# TEST
# Register drone using POST request with url: localhost:8080/drone/register
```
# Valid request example:
{
    "serialNumber":"SN-20157B",
    "model":"LIGHTWEIGHT",
    "weightLimit":400,
    "batteryCapacity":88,
    "state":"IDLE"
}
# Valid response example:
{
    "status": "Success",
    "data": {
        "serialNumber": "SN-20157B",
        "model": "LIGHTWEIGHT",
        "weightLimit": 400.0,
        "batteryCapacity": 88,
        "state": "IDLE"
    },
    "message": "Drone created successfully",
    "timestamp": "2022-03-31T01:18:57.0567325"
}
# Invalid request example:
{
    "serialNumber":"",
    "model":"LIGHTWEIGHT",
    "weightLimit":4000,
    "batteryCapacity":111,
    "state":"IDLE"
}
# Invalid response example:
{
    "status": "Fail",
    "timestamp": "31-03-2022 10:03:55",
    "message": "Invalid input values",
    "subErrors": [
        {
            "field": "serialNumber",
            "rejectedValue": "",
            "message": "Serial number size must be between 1 and 100"
        },
        {
            "field": "batteryCapacity",
            "rejectedValue": "858",
            "message": "Battery Capacity size must be between 0 and 100"
        },
        {
            "field": "weightLimit",
            "rejectedValue": "4005.0",
            "message": "Weight size must be less than or equals 500 "
        }
    ]
}
```
## Available drones for loading using GET request with url:localhost:8080/drone/available
```
# Valid response example:
{
    "status": "Success",
    "data": [
        {
            "serialNumber": "SN-20157B",
            "model": "LIGHTWEIGHT",
            "weightLimit": 400.0,
            "batteryCapacity": 88,
            "state": "IDLE"
        }
    ],
    "message": "Available drone loaded successfully",
    "timestamp": "2022-03-31T01:25:23.2682346"
}

```
## Loading a drone with medication items using POST request with url: localhost:8080/drone/load
```
# Valid request example:
{
    "serialNumber": "SN-20157B",
    "code":"M2",
	"source":"Cairo",
    "destination": "Damanhour"
}
# Valid response example:
{
    "status": "Success",
    "data": {
        "serialNumber": "SN-20157B",
        "model": "LIGHTWEIGHT",
        "weightLimit": 400.0,
        "batteryCapacity": 88,
        "state": "LOADING"
    },
    "message": "Drone loaded with medication items successfully",
    "timestamp": "2022-03-31T01:42:55.9005016"
}
# Invalid response example:
{
    "status": "Fail",
    "timestamp": "31-03-2022 01:43:49",
    "message": "Medication does not exist",
    "subErrors": []
}

```
## loaded medication items for a given drone using GET request with url:localhost:8080/drone/items/{serialNumber}
```
# Valid response example:
{
    "status": "Success",
    "data": {
        "code": "MH112",
        "name": "CAIRx",
        "weight": 100.0,
        "image": "sade23Rd"
    },
    "message": "Medication item loaded successfully",
    "timestamp": "2022-03-31T01:47:07.6475705"
}
# Invalid response example:
{
    "status": "Fail",
    "timestamp": "31-03-2022 01:47:50",
    "message": "No Medication found for drone with serial number: {0}",
    "subErrors": []
}

```
## check drone battery level using POST request with url: localhost:8080/drone/battery/{serialNumber}
```
# Valid response example:
{
    "status": "Success",
    "data": {
        "serialNumber": "SN-20157B",
        "model": "LIGHTWEIGHT",
        "weightLimit": 400.0,
        "batteryCapacity": 88,
        "state": "LOADING"
    },
    "message": "Battery level checked successfully",
    "timestamp": "2022-03-31T01:50:36.0347054"
}

```
## deliver of medication item using POST request with url: localhost:8080/drone/deliver/{serialNumber}
```
# Valid response example:
{
    "status": "Success",
    "data": {
        "serialNumber": "SN-20157B",
        "model": "LIGHTWEIGHT",
        "weightLimit": 400.0,
        "batteryCapacity": 88,
        "state": "LOADING"
    },
    "message": "Drone delivered successfully",
    "timestamp": "2022-03-31T01:53:01.3008391"
}

```

```
I hope that you will like this modest work so that I have the honor of working with you and therefore I will do my best to achieve progress for your company.
Certainly, the method of work varies from place to place and from project to project, so the method of my work with you will be according to your directions.
In conclusion, I hope we meet again in a bigger business so that I can show all I have.
Thanks

```