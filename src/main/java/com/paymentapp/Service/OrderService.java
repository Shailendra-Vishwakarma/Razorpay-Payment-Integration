package com.paymentapp.Service;

import com.paymentapp.Entity.Order;
import com.paymentapp.Repo.OrderRepo;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Value("${razorpay.key.id}")
    private String razorpayId;

    @Value("${razorpay.secret.key}")
    private String getRazorpayKey;

    private RazorpayClient client;

    public Order createOrder(Order order) throws Exception {
        //Creating JSON Object
        JSONObject orderReq=new JSONObject();
        orderReq.put("amount",order.getAmount()*100);
        orderReq.put("currency","INR");
        orderReq.put("receipt",order.getEmail());

        this.client=new RazorpayClient(razorpayId,getRazorpayKey);

        //create order in razorpay
        com.razorpay.Order razorpayOrder=client.orders.create(orderReq);

        order.setRazorpayOrderId(razorpayOrder.get("id"));
        order.setOrderStatus(razorpayOrder.get("status"));

        orderRepo.save(order);
        return order;
    }

    public Order updateOrder(Map<String,String> responsePayLoad){
    String razorpayOrderId=responsePayLoad.get("razorpay_payment_id");
    Order order=orderRepo.findByrazorpayOrderId(razorpayOrderId);
    order.setOrderStatus("Payment Completed !");

    Order updatedOrder=orderRepo.save(order);
    return updatedOrder;
    }
}
