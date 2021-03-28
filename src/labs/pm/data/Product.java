package labs.pm.data;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import static labs.pm.data.Rating.NOT_RATED;

public abstract class  Product implements Rateable<Product> {
    //public abstract Product applyRating(Rating newRating);
    private int id;
    private String name;
    private BigDecimal price;

    @Override
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    private Rating rating;

    public static final BigDecimal DISCOUNT_RATE=BigDecimal.valueOf(0.1);

    public Product(){
        this(0,"no name",BigDecimal.ZERO);
    }

    public Product(int id, String name, BigDecimal price, Rating rating){
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
    }

    public Product(int id, String name, BigDecimal price) {
        this(id,name,price,NOT_RATED);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount(){
        return price.multiply(BigDecimal.valueOf(1.0).subtract(DISCOUNT_RATE)).setScale(2, RoundingMode.HALF_UP);
    }

    public LocalDate getBestBefore(){
        return LocalDate.now();
    }

//    public Product applyRating(Rating newRating){
//
//        return new Product(this.id,this.name,this.price, newRating);
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null || getClass() == o.getClass()) {

            Product product = (Product) o;

            return this.id == ((Product) o).id && Objects.equals(this.name, ((Product) o).name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
