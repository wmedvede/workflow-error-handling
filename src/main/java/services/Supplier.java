package services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/supplier")
public class Supplier {

	@POST
	@Path("{supplier-id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response sendOrder(@PathParam("supplier-id") String supplierId, String orderNumber) {
		System.out.println(String.format("Supplier %s received the order %s", supplierId ,orderNumber));

		if ("order1".equals(orderNumber)) {
			// order1, Makes first invocation fail with an error code 401 managed in the wokflow.
			return Response.status(401, "You don't have permission to sendOrder: " + orderNumber).build();
		}

		if ("order10".equals(orderNumber)) {
			// order10, Makes first invocation fail with an error code 410 NOT managed in the workflow.
			return Response.status(410, "Unable to sendOrder: " + orderNumber).build();
		}

		// other order is never works good.
		ActionResponse.Builder builder =
				ActionResponse.Builder.newBuilder(
						Response.Status.ACCEPTED.getStatusCode()).
				setDescription("This is a test response for sendOrder: " + orderNumber);

		System.out.println(" ** Supplier sendOrder Endpoint START ** ");
		System.out.println(builder.build());
		System.out.println(" ** Supplier sendOrder Endpoint END ** ");
		return Response.status(Response.Status.ACCEPTED)
				.entity(builder.build())
				.build();
	}

	@DELETE
	@Path("{supplier-id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response cancelOrder(@PathParam("supplier-id") String supplierId, String orderNumber) {

		if ("order2".equals(orderNumber)) {
			// order2, Makes the second invocation fail with an error code 401 managed in the workflow.

			ActionResponse.Builder builder =
					ActionResponse.Builder.newBuilder(
									Response.Status.UNAUTHORIZED.getStatusCode()).
							setDescription("This is a test response cancelOrder: " + orderNumber).
							addError("Unauthenticated",
									"User is not authenticated",
									Response.Status.UNAUTHORIZED.getStatusCode());

			System.out.println(" ** Supplier cancelOrder Endpoint START ** ");
			System.out.println(builder.build());
			System.out.println(" ** Supplier cancelOrder Endpoint END ** ");

			//NOTE independently of the fact that you provide an entity for the response, at the workflow level
			// In the presence of an error / unhappy http error code, at the WF level we don't have to that
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity(builder.build())
					.build();
		}

		if ("order12".equals(orderNumber)) {
			// order12, Makes the second invocation fail with an error code 412 NOT managed in the workflow.
			return Response.status(410, "Unable to cancelOrder: " + orderNumber).build();
		}

		System.out.println(String.format("Supplier %s acknowledges the order cancellation %s", supplierId, orderNumber));

		ActionResponse.Builder builder =
				ActionResponse.Builder.newBuilder(Response.Status.OK.getStatusCode())
						.setDescription("This is a test response cancelOrder: " + orderNumber);

		System.out.println(" ** Supplier cancelOrder Endpoint START ** ");
		System.out.println(builder.build());
		System.out.println(" ** Supplier cancelOrder Endpoint END ** ");
		return Response.status(Response.Status.OK)
				.entity(builder.build())
				.build();
	}
}
