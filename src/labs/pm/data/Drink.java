package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Drink extends Product {

    @Override
    public Product applyRating(Rating newRating) {
        return new Drink(getId(),getName(),getPrice(),newRating);
    }

    public Drink(int id, String name, BigDecimal price, Rating rating){
        super(id,name,price,rating);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public BigDecimal getDiscount() {
        LocalTime now = LocalTime.now();

        return (now.isAfter(LocalTime.of(13,30)) && now.isBefore(LocalTime.of(16,30)))
                ? super.getDiscount() : BigDecimal.ZERO;
    }
}
