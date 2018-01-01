# skydigitaltechtest
Sky Channel Selecter Web Application


Development tools:
Open source Eclipse (J2EE version) 
Java version: V1.8
Apache Tomcat version V9

SkyTech.zip is a standard dynamic web project
SkyTechEAR.zip is the corresponding EAR project

Projects can be unziped and loaded into Eclipse and published to Apache Tomcat V9.

Once server is started, the entry page can be hit from the browser using the URL:
http://localhost:8080/SkyTech/SkyProdSelection.html

DOM objeect manipulation is used to populate the "basket" pane.

There is built in server-level validation to check for any illegal combination of products for a given customer and location.

I have simulated the customer ID cookie aspect by embedding the customer ID as a hidden field in SkyProdSelection.html page.

* To simulate a London customer, set the hidden field with name "custID" value in SkyProdSelection.html to say, value="1100002"
* To simulate a Liverpool customer, set the hidden field with name "custID" value in SkyProdSelection.html to say, value="1100003"


I have also added two sample unit tests for the services to demonstrate understanding of TDD and JUnit testing.
