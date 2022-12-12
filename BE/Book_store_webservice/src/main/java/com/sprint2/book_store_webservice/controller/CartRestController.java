package com.sprint2.book_store_webservice.controller;

import com.sprint2.book_store_webservice.model.CartDetail;
import com.sprint2.book_store_webservice.model.projection.CartDetailProjection;
import com.sprint2.book_store_webservice.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/cart")
@CrossOrigin("*")
public class CartRestController {

    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping("")
    public ResponseEntity<List<CartDetail>> getCartDetail(@RequestParam String username){
        List<CartDetail> cartDetailList = this.cartDetailService.getCartDetail(username);
        return new ResponseEntity<>(cartDetailList, HttpStatus.OK);
    }

   @PostMapping("/save")
   public ResponseEntity<Void> saveCartDetail(@RequestBody CartDetail cartDetail){
        this.cartDetailService.save(cartDetail);
        return new ResponseEntity<>(HttpStatus.OK);
   }

   @PostMapping("delete")
    public ResponseEntity<Void> delete(@RequestBody CartDetail cartDetail){
        this.cartDetailService.delete(cartDetail.getId());
        return new ResponseEntity<>(HttpStatus.OK);
   }

   @PostMapping("update-all")
    public ResponseEntity<Void> updateAll(@RequestBody List<CartDetail> cartDetails){
        this.cartDetailService.update(cartDetails);
        return new ResponseEntity<>(HttpStatus.OK);
   }

}
