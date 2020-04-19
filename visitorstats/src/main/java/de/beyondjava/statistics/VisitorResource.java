package de.beyondjava.statistics;

import javax.inject.Inject;
import javax.ws.rs.GET;

import java.util.stream.StreamSupport;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/visitors")
public class VisitorResource {

	@Inject
	public MySQLPool connection;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<Visitor> findAll() {
		return connection
				.query("SELECT id, rawvisits, likelyvisits, url, date FROM visits ORDER BY date, likelyvisits DESC")
				// Create a Multi from the set of rows:
				.onItem()
				.produceMulti(set -> Multi.createFrom().items(() -> StreamSupport.stream(set.spliterator(), false)))
				// For each row create a fruit instance
				.onItem().apply((row) -> new Visitor(row.getLong("id"), row.getString("date"), row.getLong("rawvisits"),
						row.getLong("likelyvisits"), row.getString("url")));
	}

	@GET
	@Path("{date}")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@SseElementType(MediaType.APPLICATION_JSON)
	public Multi<Visitor> findByDate(@PathParam String date) {
		return connection.preparedQuery(
				"SELECT id, rawvisits, likelyvisits, url, date FROM visits WHERE date = ? ORDER BY likelyvisits DESC",
				Tuple.of(date)).onItem()
				.produceMulti(set -> Multi.createFrom().items(() -> StreamSupport.stream(set.spliterator(), false)))
				.onItem().apply((row) -> new Visitor(row.getLong("id"), row.getString("date"), row.getLong("rawvisits"),
						row.getLong("likelyvisits"), row.getString("url")));

	}

	@GET
	@Path("top10")
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<Top10Article> findTop10() {
		return connection.preparedQuery(
				"SELECT url, sum(likelyvisits) as visits FROM visits GROUP BY url ORDER BY visits DESC LIMIT 1,10")
				.onItem()
				.produceMulti(set -> Multi.createFrom().items(() -> StreamSupport.stream(set.spliterator(), false)))
				.map(row -> new Top10Article(row.getLong("visits"), row.getString("url")));

	}
	
	@GET
	@Path("daily")
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<Top10Article> sumPerDay() {
		return connection.preparedQuery(
				"SELECT date, sum(likelyvisits) as visits FROM visits GROUP BY date ORDER BY date DESC LIMIT 1,10")
				.onItem()
				.produceMulti(set -> Multi.createFrom().items(() -> StreamSupport.stream(set.spliterator(), false)))
				.map(row -> new Top10Article(row.getLong("visits"), row.getString("date")));

	}
	
	@GET
	@Path("monthly")
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<Top10Article> sumPerMonth() {
		return connection.preparedQuery(
				"SELECT LEFT(date, 7) as month, sum(likelyvisits) as visits FROM visits GROUP BY date ORDER BY LEFT(date, 7) DESC LIMIT 1,10")
				.onItem()
				.produceMulti(set -> Multi.createFrom().items(() -> StreamSupport.stream(set.spliterator(), false)))
				.map(row -> new Top10Article(row.getLong("visits"), row.getString("month")));

	}
}