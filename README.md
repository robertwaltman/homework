# homework

This application is built using SpringBoot to provide a stand alone service.  For it to properly execute a local mongodb instance should be running.  

The specification for the application is as follows:

Stand-up a small microservice for us using the following spec:

Microservice spec: 

A simple message subscription service that exposes the following functionality: 
 Create a subscription (Would have at least one parameter, which would be a list of message Types the subscription wants to keep track of) 
 Read the subscription 
 Update the subscription 
 Post a message 
The message would have at least a ‘messageType’ property. 
 
In the response to the 'read subscription’ we would like also see how many times a particular event type has been received by a subscription. There may be more than one subscription listening for the same event type(s). 

Please implement this as a runnable service written with a Java 8 technology (can be based on the Spring Framework or any other of your preference). Also provide an explanation as to instruct how a client would begin using your service, such as whether or not this would be via REST API, JMX, etc. 

We would like clean, maintainable code following good programming practices to be returned.


Assumptions:
In the spec the term "event type" is referenced without being defined.  I assumed that "event type" meant the messageType property


++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

The service provides 4 REST apis to manage messages and subscriptions.  Access the apis as follows:

Add a message:
Post to http://localhost:8080/messages

The payload is a JSON object that defines the message.  For example:

{
	"messageType" : "foo",
	"message" : "Message 100"
}

The messageType attribute is required.  This api will return the saved message including its uniqueId and 
a timestamp of when it was created.  For Example:

{
  "id": "591b1a26014a99272c41be39",
  "messageType": "foo",
  "message": "Message 100",
  "timeStamp": 1494948390061
}

Add a subscription:
Post to http://localhost:8080/subscriptions

This api creates a new subscription.  The payload is a JSON object representing the subscription.  Subscriptions contain 
an array of messageTypes.   For example:
{
	"messageTypes" : [
		"foo", "bar"
	]
}

A subscription is returned including the uniqueId.  For example:
{
  "id": "591b1b05014a99272c41be3a",
  "messageTypes": [
    "foo",
    "bar"
  ]
}
The id field is used to update the subscription and retrieve messages that match the messageTypes associated with the 
subscription.

Update a Subscription:
Put to http://localhost:8080/subscriptions/(id)
This api updates the subscription list of messageTypes.  The payload is a JSON object representing the subscription
(the id is required).  For example:
{
  "id": "591a3adb014a9909406afe2e",
  "messageTypes": [
    "foo",
    "bar",
    "fubar"
  ]
}
The response is the updated subscription.  If the id is missing or the url parameter and the subscription.id do not match or are null then an error is thrown.

Read Messages:
Get from http://localhost:8080/subscriptions/(id)

This api will return the messages associated with the messageTypes contained in the subscription.  In addition a count of each
message type read is provided.  The api response is a QueryResponse object containing an array of messages and a map of messageType, integer pairs. for Example:

{
  "messages": [
    {
      "id": "591a0b02014a9913c4260dad",
      "messageType": "foo",
      "message": "Foo message 1",
      "timeStamp": 1494878978727
    },
    {
      "id": "591a0b02014a9913c4260dae",
      "messageType": "foo",
      "message": "Foo message 2",
      "timeStamp": 1494878978727
    },
    {
      "id": "591a0b02014a9913c4260daf",
      "messageType": "bar",
      "message": "bar message 1",
      "timeStamp": 1494878978728
    },
    {
      "id": "591a0b02014a9913c4260db0",
      "messageType": "bar",
      "message": "bar message 2",
      "timeStamp": 1494878978728
    },
    {
      "id": "591a372c014a990cbc49e641",
      "messageType": "foo",
      "message": "Message 1",
      "timeStamp": 100
    },
    {
      "id": "591a374f014a990cbc49e642",
      "messageType": "foo",
      "message": "Message2",
      "timeStamp": 100
    },
    {
      "id": "591a3753014a990cbc49e643",
      "messageType": "foo",
      "message": "Message 3",
      "timeStamp": 100
    },
    {
      "id": "591a3758014a990cbc49e644",
      "messageType": "bar",
      "message": "Message 3",
      "timeStamp": 100
    },
    {
      "id": "591a3761014a990cbc49e645",
      "messageType": "bar",
      "message": "Message 2",
      "timeStamp": 100
    },
    {
      "id": "591a376a014a990cbc49e646",
      "messageType": "bar",
      "message": "Message 1",
      "timeStamp": 300
    },
    {
      "id": "591a50f8014a993af09b925e",
      "messageType": "bar",
      "message": "Message 10",
      "timeStamp": 300
    },
    {
      "id": "591a5f01014a9914f004ef75",
      "messageType": "fubar",
      "message": "Message me some chocolate",
      "timeStamp": 100
    },
    {
      "id": "591a68ef014a992e44d00bb1",
      "messageType": "fubar",
      "message": "Message 2 for fubar.",
      "timeStamp": 1494903023182
    },
    {
      "id": "591b1a26014a99272c41be39",
      "messageType": "foo",
      "message": "Message 100",
      "timeStamp": 1494948390061
    }
  ],
  "messageTypeCounts": {
    "fubar": 2,
    "bar": 6,
    "foo": 6
  }
}
