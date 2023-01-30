package com.biletx.request;

public class RouteRequest {
    private String fromWhere;

    private String whereTo;

    public RouteRequest() {
        super();
    }

    public RouteRequest(String fromWhere, String whereTo) {
        this.fromWhere = fromWhere;
        this.whereTo = whereTo;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getWhereTo() {
        return whereTo;
    }

    public void setWhereTo(String whereTo) {
        this.whereTo = whereTo;
    }
}
