package net.ensode.javaee8book.jaxrs21sse;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("serversentevents")
public class SseResource {

    List<Float> stockTickerValues = null;
    Executor executor = Executors.newSingleThreadExecutor();

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void sendEvents(@Context SseEventSink sseEventSink, @Context Sse sse) {
        initializeStockTickerValues();
        executor.execute(() -> {
            stockTickerValues.forEach(value -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(String.format("Sending the following value: %.2f", value));
                    final OutboundSseEvent outboundSseEvent = sse.newEventBuilder()
                            .name("ENSD stock ticker value")
                            .data(String.class, String.format("%.2f", value))
                            .build();
                    sseEventSink.send(outboundSseEvent);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            });

        });
    }

    private void initializeStockTickerValues() {
        stockTickerValues = Stream.of(50.3f, 55.5f, 62.3f, 70.7f, 10.1f, 5.1f).collect(Collectors.toList());
    }
}
