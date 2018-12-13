package cl.kyo.minecartapp.utils;

public enum Menu {
    CHECKOUT_MINECART, LISTAR_CHECKOUT_MINECART;

    @Override
    public String toString() {
        String menuItem = "";

        switch (this) {
            case CHECKOUT_MINECART:
                menuItem = "Agregar Minecart";
                break;
            case LISTAR_CHECKOUT_MINECART:
                menuItem = "Listar Minecarts";
                break;

        }

        return menuItem;
    }
}
