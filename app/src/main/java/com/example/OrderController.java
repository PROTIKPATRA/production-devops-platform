package com.example.orderapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class OrderController {

    @GetMapping("/api/orders/status")
    public Map getSystemStatus() {
        return Map.of(
            "service", "E-commerce Order API",
            "status", "Operational",
            "active_orders_queue", 142,
            "version", "1.0.0"
        );
    }
}