package de.beyondjava.statistics;

import java.util.stream.StreamSupport;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Visitor {
    public Long id;
    
    public String date;
    
    public long rawvisitors;
    
    public long likelyvisitors;
    
    public String url;
    
    public Visitor() {}
    
    public Visitor(Long id, String date, Long rawvisitors, Long likelyvisitors, String url) {
    	this.id = id;
    	this.date = date;
    	this.rawvisitors = rawvisitors;
    	this.likelyvisitors = likelyvisitors;
    	this.url = url;
    }
}
