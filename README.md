

Provided sample JSON input can be uploaded in POST request body on routes:

  * "127.0.0.1:8080/upload/flights" - for flights
  * "127.0.0.1:8080/upload/shipments - for cargo

Functionalities from task can be used with GET request with url params, for ex:

  * "127.0.0.1:8080/weight?flightNo=1458&date=2019-07-07" - task to check weight
  * "127.0.0.1:8080/total?iata=MIT&date=2018-10-31" - task with arrival info
