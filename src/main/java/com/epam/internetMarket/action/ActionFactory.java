package com.epam.internetMarket.action;

import java.util.Map;
import java.util.HashMap;

import static com.epam.internetMarket.util.constants.ActionConstants.*;

public class ActionFactory {

    private static final Map<String, Action> ACTION_MAP = new HashMap<>();
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();

    static {
        ACTION_MAP.put(REGISTER_USER_ACTION, new RegisterUserAction());
        ACTION_MAP.put(CHANGE_LOCALE_ACTION, new ChangeLocaleAction());
        ACTION_MAP.put(LOGIN_ACTION, new LoginAction());
        ACTION_MAP.put(ERROR_ACTION, new ErrorAction());
        ACTION_MAP.put(MAKE_ORDER_ACTION, new MakeOrderAction());
        ACTION_MAP.put(EDIT_PROFILE_ACTION, new EditProfileAction());
        ACTION_MAP.put(EDIT_PASSWORD_ACTION, new EditPasswordAction());
        ACTION_MAP.put(EDIT_USERS_PASSWORD_ACTION, new EditUsersPasswordAction());
        ACTION_MAP.put(LOGOUT_ACTION, new LogoutAction());
        ACTION_MAP.put(ADD_CART_ACTION, new AddCartAction());
        ACTION_MAP.put(GET_PRODUCT_PAGE_ACTION, new GetProductPageAction());
        ACTION_MAP.put(EDIT_CART_PRODUCT_COUNT_ACTION, new EditCartProductCountAction());
        ACTION_MAP.put(DELETE_CART_ACTION, new DeleteCartAction());
        ACTION_MAP.put(GET_ORDER_DETAILS_ACTION, new GetOrderDetailsPageAction());
        ACTION_MAP.put(ADD_PRODUCT_ACTION, new AddProductAction());
        ACTION_MAP.put(EDIT_PRODUCTS_ACTION, new EditProductsAction());
        ACTION_MAP.put(REMOVE_FROM_MARKET_ACTION, new RemoveFromMarketAction());
        ACTION_MAP.put(EDIT_ORDER_STATUS_ACTION, new EditOrderStatusAction());
    }

    public static ActionFactory getInstance() { return ACTION_FACTORY; }

    public Action getAction (String request) {
        Action action = ACTION_MAP.get(ERROR_ACTION);

        for (Map.Entry<String, Action> pair : ACTION_MAP.entrySet()) {
            if (request.equalsIgnoreCase(pair.getKey())) {
                action = ACTION_MAP.get(pair.getKey());
            }
        }
        return action;
    }
}
