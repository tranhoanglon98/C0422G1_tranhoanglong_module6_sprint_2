package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.CartDetail;
import com.sprint2.book_store_webservice.repository.ICartDetailRepository;
import com.sprint2.book_store_webservice.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService implements ICartDetailService {

    @Autowired
    private ICartDetailRepository cartDetailRepository;

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

}
