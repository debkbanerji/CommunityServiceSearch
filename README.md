# CommunityServiceSearch
Community Service Search Android Application

The name of the application, CS^2 stands for Community Service Search and allows users to actively engage in giving back to their communities. This service finds and organizes community service events, and puts then at the fingertips of the user.  The user also has the ability to create their own community service events, and put them on the radar for other people to attend.

The inspiration behind building this service was to empower individuals to become more involved in their respective communities.  Surveys show that although many people see the value of community service, most people are not as involved as they would like to be.  This is largely due to the fact that many people are not aware of opportunities to help out around them.  As the creators of this service, we wanted to spur users to give back to their communities, and instigate a push for positive social change.

The application was created for the android platform, using Java for a large part of the logic, XML for most of the visuals, and Gradle as a build tool.  The data is stored remotely and backed by a hash map. Since community service events can have similar names, we chose to hash the events according to the date and time that they are scheduled to occur, in order to maintain good performance. This is because the time complexity for manipulating events stored in the database, as well as for pushing events to the database is amortized O(1). The application also has support for extremely easy selection of the date and time the event occurs, while still storing this data in a space efficient manner.

Another aspect of the application is that it has the ability to mark the location of an event on a user’s map. In order to do this, it manipulates the string representing the address of the event, and parses it via a Uri object so that it can launch Google Maps with the location.  The app also trims off trailing spaces entered by the user before pushing the data to the database in order to reduce space used and maintain the integrity of stored data.

Screenshots:

![CS^2](/Screenshots/2016-04-03 00.01.36.png?raw=true)
![CS^2](/Screenshots/2016-04-03 00.09.16.png?raw=true)
![CS^2](/Screenshots/2016-04-03 00.23.12.png?raw=true)
![CS^2](/Screenshots/2016-04-03 00.23.38.png?raw=true)