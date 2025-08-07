microprofile.rest.client.disable.default.mapper=false

sendOrder
sendOrder2
sendOrder3


    protected Object invokeSync(Object[] args) {
        ClientInvocation request = createRequest(args);
        //Revienta acá, no se llega ni al extractor
        ClientResponse response = (ClientResponse) request.invoke();
        ClientContext context = new ClientContext(request, response, entityExtractorFactory);
        return extractor.extractEntity(context);
    }


microprofile.rest.client.disable.default.mapper=true

sendOrder
sendOrder2
sendOrder3


    protected Object invokeSync(Object[] args) {
        ClientInvocation request = createRequest(args);
        ClientResponse response = (ClientResponse) request.invoke();
        ClientContext context = new ClientContext(request, response, entityExtractorFactory);
        // logra llegar el entity extractor
        return extractor.extractEntity(context);
    }


    org.jboss.resteasy.client.jaxrs.internal.proxy.extractors.BodyEntityExtractor

        org.jboss.resteasy.client.jaxrs.internal.ClientInvocation.extractResult
            acá revienta.




----------------------------------------------------------
kogito-serverless-workflow-openapi-parser

productized, yes: kogito-serverless-workflow-rest-parser/9.103.0.redhat-00004

----------------------------------------------------------

POST and PUT methods:
    * always sends the content always as json
    * always expects the result as json

In general:
    * OpenAPI document is used mostly to build the paths, but the types are not used.
    * Doesn't set the Content-Type header
    * path parameters are supported
    * query parameters are not supported

For an invocation like this:

        functionRef:
          refName: sendOrder
          arguments:
            supplier-id: .supplierId
            content: .orderId

The REST Service receives:

    With a signature like this:

    @POST
    @Path("{supplier-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendOrder(@PathParam("supplier-id") String supplierId, String orderNumber) {

    kogito-serverless-workflow-openapi-parser

        {"content":"order1"}

        And produces this:

        {statusCode=202, description='This is a test response for sendOrder: {"content":"order1"}', errors=[]}

        Response processing fails, since not a json.


----------------------------------------------------------

{
    "statusCode" : "el verdadero que ha vendio en el http response, y no lo q puso al tia en su modelo"

    "statusMessage" : "idealmente el staus message q viene el el reponse y que parece que al Vertx se la pela no tenemos acceso parede"

     Y de alguna forma la mierda de entidad que pueda haber retornado, si es que la hay.

    Aqui tenemos 2 alternativas:

    1) directamente inyectamos los campos, en plan

       supongamos que el entity q retorno la tia es
       {
          "campo1" : "valor1",
          "campo2" : "valor2"
       }

       pues metemos

       "campo1" : "valor1",
       "campo2" : "valor2"


    2) segunda alternativa, metemos un campo "entity"

    "entity" : {
    	 "campo1" : "valor1",
         "campo2" : "valor2"
    }

}

----------------------------------------------------------










----------------------------------------------------------
HTTP/1.1 Standard response fields.

Status line:
    HTTP Version (e.g., HTTP/1.1)
    Status Code (e.g., 200, 404, 500)
    Reason Phrase (e.g., OK, Not Found, Internal Server Error)

e.g: HTTP/1.1 200 OK

Response Headers:
Message Body:

Complete example:

HTTP/1.1 200 OK
Date: Tue, 29 Jul 2025 12:00:00 GMT
Server: Apache/2.4.41 (Ubuntu)
Content-Type: application/json
Content-Length: 48
Connection: keep-alive

{
  "message": "Hello, world!",
  "status": "ok"
}


HTTP/2 Standard response fields
Binary Frames based:

Every HTTP/2 message is broken into frames, and each frame has:

    A type (e.g., HEADERS, DATA)

    A length (number of bytes in that frame)

    A flags field (e.g., END_HEADERS, END_STREAM)

Conceptual visualization:

[HEADERS frame: :status, content-type, content-length]
[DATA frame: part of body]
[DATA frame: last part of body with END_STREAM flag]



If you're using the Jakarta REST Client API (previously JAX-RS Client API), you can get the status code,
but not the reason phrase directly, because Jakarta's Response object does not expose the reason phrase
(per the HTTP/2+ spec, it's deprecated and discouraged).
----------------------------------------------------------