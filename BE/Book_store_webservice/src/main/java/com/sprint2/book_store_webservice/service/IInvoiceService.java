package com.sprint2.book_store_webservice.service;

import com.sprint2.book_store_webservice.model.Invoice;

import java.util.List;

public interface IInvoiceService {

    void saveAll(List<Invoice> invoices);
}
