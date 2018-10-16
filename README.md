# MessagingServiceWithConsul
the application has been created and tested on a single
machine with consul server running on port 8500
and the two springboot applications running on ports 8801 and 8802.

start the consul server 
on my linux machine the start command
is as follows you'll change the IP address
to match yours : ./consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind={your ip}

in the consul ui add the following key value pairs:
key   uuid-service.prefix  value  {anyvalue}
key   uuid-service.suffix  value  {anyvalue}
key   message-service.edit.time.secs   value {10}
key   message-service.delete.time.mins value {2}

cd into the UuidService folder and run gradle with: gradle run
do the same for MessageService.


The following url are for the message service
to add a new message:
POST   http://localhost:8802/messages/  {"messageText":"mymessage","messageSender":"mackie","messageReciver":"naim"} with no messageId 


to retrive all messages:
GET    http://localhost:8802/messages/

to retrive a single message:
GET    http://localhost:8802/messages/{id} 

to update a message:
PUT    http://localhost:8802/messages/  {"messageId": "{id}","messageText":"newmessage","messageSender":"mackie","messageReciver":"naim"}

to persist to the database:
PUT    http://localhost:8802/messages/persist

and to delete:
DELETE http://localhost:8802/messages/{id} 

the message id's are generated be the uuid-service and the prefix and suffix are configurable but
if the service cannot be connected the the prefix of id will be: "LocalUUID-" and suffix of "-xxx"


