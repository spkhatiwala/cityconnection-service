# cityconnection-service
Mastercard City Connection Restful WebService
This is the project using Spring Boot Restful MVC service. 
It determines if two cities are connected.
Two cities are considered connected if there's a series of roads that can be traveled from one city to another.

------------------------------------------------------
The logic is implemented as an undirected graph to see if there is a path between two vertices.
------------------------------------------------------

------------------------------------------------------
The file city.txt will reside in resource folder.
application.properties will have the name of the file 
specified to be used to initate the city connections.
------------------------------------------------------

The application will be deployed as a Spring Boot App and expose one endpoint.
http://localhost:8080/connected?origin=city1&destination=city2


Program will respond ‘yes’ if city1 is connected to city2,
’no’ if city1 is not connected to city2.
Any unexpected input will result in a ’no’ response.


For Example:
city.txt content is:
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany
http://localhost:8080/connected?origin=Boston&destination=Newark
Will return yes
http://localhost:8080/connected?origin=Boston&destination=Philadelphia
Will return yes
http://localhost:8080/connected?origin=Philadelphia&destination=Albany
Will return no
