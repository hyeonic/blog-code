package io.github.hyeonic.transaction.orderdetail;

public class OrderRequest {

    private String productName;
    private Integer productPrice;
    private Long customerId;
    private Long ownerId;

    private OrderRequest() {
    }

    public OrderRequest(final String productName, final Integer productPrice, final Long customerId,
                        final Long ownerId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.customerId = customerId;
        this.ownerId = ownerId;
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
