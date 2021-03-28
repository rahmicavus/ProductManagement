package labs.pm.data;

public class Order {
    private static int id= 1;
    public static void createShipmentMode(String description){
        new ShippingMode(description);
    }

    private static class ShippingMode{
        private String description;

        public ShippingMode(String description){
            this.description = description;

        }

    }
}
