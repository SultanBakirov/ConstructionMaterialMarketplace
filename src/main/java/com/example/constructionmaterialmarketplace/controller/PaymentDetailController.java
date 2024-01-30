package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.dto.request.PaymentDetailRequest;
import com.example.constructionmaterialmarketplace.dto.request.UpdateStatusRequest;
import com.example.constructionmaterialmarketplace.entity.PaymentDetail;
import com.example.constructionmaterialmarketplace.service.PaymentDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/detail")
public class PaymentDetailController {

    private final PaymentDetailService paymentDetailService;

    @PostMapping("/process-new-payment")
    public ResponseEntity<PaymentDetail> processNewPayment(@RequestBody PaymentDetailRequest newPaymentRequest) {
        PaymentDetail paymentDetail = paymentDetailService.processNewPayment(
                newPaymentRequest.getOrderId(),
                newPaymentRequest.getAmount(),
                newPaymentRequest.getPaymentMethod());
        return new ResponseEntity<>(paymentDetail, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentDetailId}")
    public ResponseEntity<PaymentDetail> getPaymentDetailById(@PathVariable Long paymentDetailId) {
        PaymentDetail paymentDetail = paymentDetailService.getPaymentDetailById(paymentDetailId);
        return ResponseEntity.ok(paymentDetail);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentDetail>> getPaymentDetailsByOrderId(@PathVariable Long orderId) {
        List<PaymentDetail> paymentDetails = paymentDetailService.getPaymentDetailsByOrderId(orderId);
        return ResponseEntity.ok(paymentDetails);
    }

    @PutMapping("/update-status/{paymentDetailId}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long paymentDetailId, @RequestBody UpdateStatusRequest updateStatusRequest) {
        PaymentDetail updatedPaymentDetail = paymentDetailService.updatePaymentStatus(paymentDetailId, updateStatusRequest.getStatus());
        return ResponseEntity.ok(updatedPaymentDetail);
    }

    @PostMapping("/refund/{paymentDetailId}")
    public ResponseEntity<?> refundPayment(@PathVariable Long paymentDetailId) {
        PaymentDetail paymentDetail = paymentDetailService.refundPayment(paymentDetailId);
        return ResponseEntity.ok(paymentDetail);
    }
}
