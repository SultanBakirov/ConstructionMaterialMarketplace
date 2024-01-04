package com.example.constructionmaterialmarketplace.entity;

import com.example.constructionmaterialmarketplace.entity.enums.PaymentMethod;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_details")
public class PaymentDetail {

    // payment method constants
    public static final String METHOD_CREDIT_CARD = "CREDIT_CARD";
    public static final String METHOD_PAYPAL = "PAYPAL";

    // status constants
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_FAILED = "FAILED";

    @Id
    @SequenceGenerator(name = "payment_detail_gen", sequenceName = "payment_detail_seq", allocationSize = 1, initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_detail_gen")
    private Long id;

    @ManyToOne // ?????????????????????????
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    private PaymentStatus status;

}
