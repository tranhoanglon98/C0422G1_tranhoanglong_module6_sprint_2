package com.sprint2.book_store_webservice.model.projection;

public interface BookProjection {

    Long getID();

    String getTitle();

    String getImageUrl();

    Double getPrice();

    Integer getSold();
}
