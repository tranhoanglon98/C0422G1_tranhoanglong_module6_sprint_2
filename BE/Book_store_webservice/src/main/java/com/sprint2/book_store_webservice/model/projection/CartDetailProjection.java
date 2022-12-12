package com.sprint2.book_store_webservice.model.projection;

public interface CartDetailProjection {

    Long getId();

    Integer getQuantity();

    Boolean getStatus();

    Long getAccountId();

    String getTitle();

    String getUrl();

    Double getBookPrice();
}
