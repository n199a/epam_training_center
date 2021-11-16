package com.epam.esm.util;

/**
 * Url mapping for controllers.
 */
public final class UrlMapping {

    /**
     * General URLs.
     */
    public static final String ID = "/{id}";

    /**
     * URL for Gift certificate entities
     */
    public static final String GIFT_CERTIFICATES = "/gift-certificates";

    /**
     * URL for Tag
     */
    public static final String TAGS = "/tags";
    public static final String MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS = "/most-widely";

    /**
     * URL only for User.
     */
    public static final String USERS = "/users";

    /**
     * URL for User with Order
     */
    public static final String ORDERS_USER = "/{userId}/orders";
    public static final String ORDER_USER = "/{userId}/orders/{orderId}";

    /**
     * URL for Order
     */
    public static final String ORDERS = "/orders";

    private UrlMapping() {
    }
}
