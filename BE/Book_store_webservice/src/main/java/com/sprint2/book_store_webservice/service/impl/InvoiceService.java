package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.Invoice;
import com.sprint2.book_store_webservice.repository.IInvoiceRepository;
import com.sprint2.book_store_webservice.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private IInvoiceRepository iInvoiceRepository;

    @Override
    public void saveAll(List<Invoice> invoices) {
         iInvoiceRepository.saveAll(invoices);
    }
}
