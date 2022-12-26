package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice,Long> {
}
