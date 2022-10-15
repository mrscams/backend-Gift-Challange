# Wedoogift Backend challenge
You are interested in joining our team ? try to accomplish this challenge, we will be glad to see
your code and give you feedback.

## Start The Application 
you can start the application by running this command

 > $> mvn spring-boot:run 

## Swagger Description
to have an insight about the API you can check the swagger Doc UI after starting the application
by accessing this rul
> http://localhost:8080/swagger-ui.html

## Enhacement ToDo

use  mapstrut
add spring security and also secure the swagger UI

## Statements

Companies can use Wedoogift services to distribute:
- Gift deposits
- Meal deposits
### Gift deposits
Gift deposits has 365 days lifespan, beyond this period it will no longer be counted in the user balance.

example:
John receives a Gift distribution with the amount of $100 euros from Tesla. he will therefore have $100 in gift cards in his account.
He received it on 06/15/2021. The gift distribution will expire on 06/14/2022. 
### Meal deposits
Meal deposit works like the Gift deposit excepting for the end date. In fact meal deposits expires at the end of February of the year following the distribution date.

example:
Jessica receives a Meal distribution from Apple with the amount of $50 on 01/01/2020, the distribution ends on 02/28/2021.

* Implement one or two functions allowing companies to distribute gift and meal deposits to a user if the company's balance allows it.
* Implement a function to calculate the user balance.


