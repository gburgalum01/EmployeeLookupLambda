# AWS Lambda Function Demo - Employee Lookup

## Overview

This is a sample project that provides the functionality for an AWS Lambda function that looks up an employee in a JSON-formatted file.


## Requirements
* JDK 11
* Gradle 7
* An AWS account in which you can create a Lambda function.


## Building the Artifact
Run the following command to build the JAR file named EmployeeLookupLambda.jar that needs to be uploaded to the Lambda function.

```
gradle build
```

## Lambda Configuration
In your AWS account, create a new Lambda with the following parameters.

| Parameter | Value      |
|-----------|------------|
| Runtime   | Java 11    |
| Timeout   | 15 seconds |
| Handler   | com.brm.lambda.EmployeeLookupRequestHandler::handleRequest |

Upload the EmployeeLookupLambda.jar file that you built to the Lambda function.


## Running the Lambda

On the `Test` tab of your Lambda function, enter the following JSON into the `Event JSON` field.

```JSON
{
  "company": "BlueLines",
  "id": 5293
}
```
Click the `Test` button to run the test.  You should see the following line in the execution log.

```
"The name of the employee is Terry Rablonski."
```