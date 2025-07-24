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
