package com.paymentapp.Controller;

import com.paymentapp.Entity.Order;
import com.paymentapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String init(){
        return "index";
    }

    @PostMapping(value="/create-order", produces="application/json")
    @ResponseBody
    public ResponseEntity<Order> createOder(@RequestBody Order order) throws Exception {
     Order orderCreated= orderService.createOrder(order);
     return new ResponseEntity<>(orderCreated, HttpStatus.CREATED);
    }

    @PostMapping("/handle-payment-callback")
    public String handlePaymentCallBack(@RequestParam Map<String,String> resPayLoad){
    orderService.updateOrder(resPayLoad);
    return "success";
    }
}
