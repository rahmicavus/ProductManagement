package labs.pm.app;

import com.sun.source.tree.BreakTree;
import labs.pm.data.*;

import javax.swing.plaf.ProgressBarUI;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Shop {
    public static void main(String[] args) throws ProductManagerException {

        ProductManager pm = new ProductManager(Locale.UK);

        //pm.createProduct(101,"Tea",BigDecimal.valueOf(1.99),Rating.NOT_RATED);

//        pm.reviewProduct(101,Rating.FOUR_STAR,"Nice hot a cup of tea!");
//        pm.reviewProduct(101,Rating.FIVE_START,"very nice!");
//        pm.reviewProduct(101,Rating.ONE_STAR,"bad!");
//        pm.reviewProduct(101,Rating.TWO_STAR,"averaga!");
//        pm.reviewProduct(101,Rating.THREE_STAR,"Nothing special");
//        pm.reviewProduct(101,Rating.FOUR_STAR,"Nice hot a cup of tea!");
//

        pm.parseProduct("D,101,Tea,15.05,3,2020-05-05");


        pm.parseReview("101,4,Nice hot tea!");
        pm.parseReview("101,2,Rather week coffee!");
        pm.parseReview("101,4,Fine tea!");
        pm.parseReview("101,2,Good tea!");
        pm.parseReview("101,5,Perfect tea!");
        pm.parseReview("101,3,Just add some lemon!");
        pm.printProductReport(101);

        pm.parseProduct("F,102,Foody,14.25,1,2020-12-10");
        pm.parseReview("102,5,Find dinning");
        pm.printProductReport(102);


        //pm.parseReview("1016,Nice baby shot");
        //pm.printProductReport(101);

//        Product p2 = pm.createProduct(102,"Cheese",BigDecimal.valueOf(12.15),Rating.FOUR_STAR);
//        Product p3 = pm.createProduct(103,"Volkie",BigDecimal.valueOf(1.25),Rating.FIVE_START);
//
//
//
//        pm.printProductReport(41);

        //pm.printProducts((o1,o2) -> o1.getRating().ordinal()-o2.getRating().ordinal());
        //pm.printProducts((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
       // pm.printProducts(p-> p.getPrice().floatValue()<2, (o1,o2)->o2.getRating().ordinal()-o1.getRating().ordinal());
//
//        Comparator<Product> ratingSorter= ((o1, o2) -> o2.getRating().ordinal() - o1.getRating().ordinal());
//        Comparator<Product> priceSorter =((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
//        //pm.printProducts(ratingSorter);
//        //pm.printProducts(ratingSorter.thenComparing(priceSorter));
//
//        //pm.printProducts((a1,a2)->a1.getRating().ordinal() - a2
//
//
//
//        pm.createProduct(106,"Chocalatte",BigDecimal.valueOf(5.99),Rating.NOT_RATED,LocalDate.now().plusDays(3));
//        pm.reviewProduct(106,Rating.FIVE_START,"Besty chocalatte!");
//        pm.reviewProduct(106,Rating.THREE_STAR,"Fine chocalatte!");
//
//        pm.printProductReport(106);
//
//        pm.printProducts(p-> p.getPrice().floatValue()>5, (o1,o2)->o2.getRating().ordinal()-o1.getRating().ordinal());
//
//        //


//        Product p1 = new Drink(101,"Tea", BigDecimal.valueOf(1.99),Rating.FIVE_START);
//        Product p2 = new Drink(102,"Coffee",BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
//        Product p3 = new Food(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_START, LocalDate.now());
//        Product p4 = p3.applyRating(Rating.ONE_STAR);
//
//        Product p6 = new Drink(104,"Chocolate", BigDecimal.valueOf(2.99),Rating.FIVE_START);
//        Product p7 = new Food(104,"Chocolate", BigDecimal.valueOf(2.99),Rating.FIVE_START,LocalDate.now().plusDays(2));
//        Product p8 = p2.applyRating(Rating.THREE_STAR);
//
//        System.out.println(p6.equals(p7));
//
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(p4.getBestBefore());
//        System.out.println(p7.getBestBefore());
//        System.out.println(p3.getDiscount());

//        pm.getDiscount().forEach(
//                (rating,discount)-> System.out.println(rating+"\t"+discount));

    }
}
