package io.github.hyeonic.transaction.orderdetail;

public class OrderDetail {

    private Long id;
    private String productName;
    private Integer productPrice;
    private Long customerId;
    private Long ownerId;

    public OrderDetail(final Long id, final OrderDetail orderDetail) {
        this(id, orderDetail.getProductName(), orderDetail.getProductPrice(), orderDetail.getCustomerId(),
                orderDetail.getOwnerId());
    }

    public OrderDetail(final String productName, final Integer productPrice, final Long customerId,
                       final Long ownerId) {
        this(null, productName, productPrice, customerId, ownerId);
    }

    public OrderDetail(final Long id, final String productName, final Integer productPrice, final Long customerId,
                       final Long ownerId) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.customerId = customerId;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }
}
