package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.CartDetail;
import com.sprint2.book_store_webservice.model.Invoice;
import com.sprint2.book_store_webservice.repository.ICartDetailRepository;
import com.sprint2.book_store_webservice.service.ICartDetailService;
import com.sprint2.book_store_webservice.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartDetailService implements ICartDetailService {

    @Autowired
    private ICartDetailRepository cartDetailRepository;

    @Autowired
    private IInvoiceService iInvoiceService;

    @Override
    public List<CartDetail> getCartDetail(String username) {
        return cartDetailRepository.findByUsername(username);
    }

    @Override
    public CartDetail findByAccountIdAndBookId(Long accountId, Long bookId) {
        return this.cartDetailRepository.findByAccount_IdAndBook_IdAndStatus(accountId,bookId,true);
    }

    @Override
    public void save(CartDetail cartDetail) {
        if (cartDetail.getId() == null){
            CartDetail item = findByAccountIdAndBookId(cartDetail.getAccount().getId(),cartDetail.getBook().getId());
            if (item == null){
                cartDetail.setQuantity(1);
                cartDetail.setStatus(true);
                this.cartDetailRepository.save(cartDetail);
            }else {
                item.setQuantity(item.getQuantity() + 1);
                this.cartDetailRepository.save(item);
            }
        }else {
            this.cartDetailRepository.save(cartDetail);
        }
    }

    @Override
    public void delete(Long id) {
        this.cartDetailRepository.changeStatus(id);
    }

    @Override
    public void update(List<CartDetail> cartDetails) {
        this.cartDetailRepository.saveAll(cartDetails);
    }

    @Override
    public void pay(List<CartDetail> cartDetails) {
      List<CartDetail> paidItems =  this.cartDetailRepository.saveAll(cartDetails);
      List<Invoice> invoices = paidItems.stream().map(item ->
              new Invoice(null,
                      "HD-" + item.getAccount().getId() + "-" + item.getId(),
                      LocalDate.now(),item.getQuantity(), item.getBook(),item.getAccount())).collect(Collectors.toList());
      this.iInvoiceService.saveAll(invoices);
    }
}
