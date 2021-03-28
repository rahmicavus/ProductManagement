package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product{

    private LocalDate bestBefore;

    public Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        super(id,name,price,rating);
        this.bestBefore = bestBefore;
    }

    /**
     * Get the value of the date
     * @return the value of the bestBefore
     */
    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public String toString() {
        return super.toString()+","+
                "bestBefore=" + bestBefore +
                '}';
    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Food(getId(),getName(),getPrice(),newRating,getBestBefore());
    }

    @Override
    public BigDecimal getDiscount() {
        return  bestBefore.isEqual(LocalDate.now()) ? super.getDiscount() : BigDecimal.ZERO;
    }
}
