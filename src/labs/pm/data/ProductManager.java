package labs.pm.data;

import org.junit.jupiter.engine.execution.NamespaceAwareStore;

import java.awt.color.ProfileDataException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.PublicKey;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductManager {

    private static final Logger logger =
            Logger.getLogger(ProductManager.class.getName());

    private ResourceFormatter formatter;
    private static Map<String,ResourceFormatter> formatters
            = Map.of("en-GB",new ResourceFormatter(Locale.UK),
                    "en-US",new ResourceFormatter(Locale.US),
                    "fr-FR",new ResourceFormatter(Locale.FRANCE),
                    "ru-RU",new ResourceFormatter(new Locale("ru","RU")),
                    "zh-CN", new ResourceFormatter(Locale.SIMPLIFIED_CHINESE));
    //private Product product;
    private Review[] reviews = new Review[5];
    private Map<Product, List<Review>> products = new HashMap<>();
    private  ResourceBundle config =
            ResourceBundle.getBundle("labs.pm.data.config");
    private MessageFormat reviewFormat =
            new MessageFormat(config.getString("review.data.format"),Locale.UK);
    private MessageFormat productFormat =
            new MessageFormat(config.getString("product.data.format"),Locale.UK);



    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());

    }
    public ProductManager(String languageTag) {

        changeLocale(languageTag);

    }

    public void changeLocale(String languageTag){
        formatter = formatters.getOrDefault(languageTag,formatters.get("en-GB"));
    }
    public static Set<String> getSupportedLocales(){
        return formatters.keySet();
    }



    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        Product product = new Food(id,name,price,rating,bestBefore);
        products.putIfAbsent(product,new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        Product product= new Drink(id,name,price,rating);
        products.putIfAbsent(product,new ArrayList<>());
        return product;
    }

    public Product reviewProduct(int id, Rating rating,String comment){
        try {
            return reviewProduct(findProduct(id),rating,comment);
        }
        catch (ProductManagerException ex){
            logger.log(Level.INFO,ex.getMessage());
            return null;
        }

    }

    public Product reviewProduct(Product product, Rating rating, String comments){

        List<Review> reviews =products.get(product);
        products.remove(product);
        reviews.add(new Review(rating,comments));

//        int sum = 0;
//        for(Review review : reviews){
//            sum+=review.getRating().ordinal();
//        }

        product = product.applyRating(
                Rateable.convert(
                        (int)Math.round(
                                reviews.stream()
                                        .mapToInt(r -> r.getRating().ordinal())
                                        .average()
                                        .orElse(0))));


        //We had removed first and put again with rating
        products.put(product,reviews);
        return product;
    }

    public Product findProduct(int id) throws ProductManagerException {
//        Product result = null;
//
//        for(Product product : products.keySet()){
//            if(id == product.getId()) {
//                result = product;
//                break;
//            }
//        }
//        return result;

        return products.keySet()
                .stream()
                .filter(p -> p.getId()==id)
                .findFirst()
                .orElseThrow(() -> new ProductManagerException("Product with "+id+" not found!"));
    }

    public void printProductReport(int id)
    {

        try {
            printProductReport(findProduct(id));
        } catch (ProductManagerException ex) {
            logger.log(Level.INFO,ex.getMessage());
        }
    }

    public void printProductReport(Product product){

        List<Review> reviews =products.get(product);
        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append("\n");
        Collections.sort(reviews);

        if(reviews.isEmpty()){
            txt.append(formatter.getText("no.reviews") +"\n");
        }
        else {
            txt.append(reviews.stream()
                    .map(r -> formatter.formatReview(r) +"\n")
                    .collect(Collectors.joining()));
        }

//        for( Review review : reviews) {
//                txt.append(formatter.formatReview(review));
//                txt.append("\n");
//
//            }
//
//            if(reviews.isEmpty()) {
//                txt.append(formatter.getText("no.reviews"));
//                txt.append("\n");
//            }

        System.out.println(txt);
        }

        public void printProducts(Predicate<Product> filter,Comparator<Product> sorter){

//            List<Product> productsList = new ArrayList<>(products.keySet());
//            productsList.sort(sorter);
            StringBuilder text = new StringBuilder();
//            for(Product product : productsList){
//                text.append(formatter.formatProduct(product));
//                text.append("\n");
//            }

            products.keySet()
                    .stream()
                    .sorted(sorter)
                    .filter(filter)
                    .forEach(p -> text.append(formatter.formatProduct(p) +"\n"));


            System.out.println(text);
        }

        public void parseReview(String text) throws ProductManagerException {
            try {
                Object[] values = reviewFormat.parse(text);
                reviewProduct(Integer.parseInt((String)values[0]),Rateable.convert(Integer.parseInt((String)values[1])),(String)values[2]);
            } catch (ParseException | NumberFormatException e) {
                logger.log(Level.WARNING,"Error parsing Review "+ text,e);
                //throw new ProductManagerException("Cannot parse the text"+text);
            }
        }

        public void parseProduct(String text) throws ProductManagerException {
            try {
                Object[] values = productFormat.parse(text);

                int id = Integer.parseInt((String)values[1]);
                String name = (String) values[2];
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String)values[3]));
                Rating rating = Rateable.convert(Integer.parseInt((String)values[4]));

                switch ((String) values[0]){
                    case "D":createProduct(id,name,price,rating);break;
                    case "F":
                        LocalDate bestBefore = LocalDate.parse((String) values[5]);
                        createProduct(id,name,price,rating,bestBefore);
                        break;
                }
            } catch (ParseException | NumberFormatException e) {
                logger.log(Level.SEVERE,"Product parse problem"+text,e);
            }

        }

        public Map<String,String> getDiscount(){

        return products.keySet()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                product -> product.getRating().getStars(),
                        Collectors.collectingAndThen(
                                Collectors.summingDouble(
                                        product -> product.getDiscount().doubleValue()),
                                        discount -> formatter.moneyFormat.format(discount))));


                        //price.multiply(BigDecimal.valueOf(1.0).subtract(DISCOUNT_RATE)).setScale(2, RoundingMode.HALF_UP);


        }

        private static class ResourceFormatter{

            private Locale locale;
            private ResourceBundle resources;
            private DateTimeFormatter dateFormat;
            private NumberFormat moneyFormat;

            private ResourceFormatter(Locale locale){
                this.locale = locale;
                resources = ResourceBundle.getBundle("labs.pm.data.resources",locale);
                dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
                moneyFormat = NumberFormat.getCurrencyInstance(locale);

            }

            private String formatProduct(Product product){

                return MessageFormat.format(resources.getString("product"),
                        product.getName(),
                        moneyFormat.format(product.getPrice()),
                        product.getRating().getStars(),
                        dateFormat.format(product.getBestBefore()));

            }

            private String formatReview(Review review){
                return MessageFormat.format(resources.getString("review"),
                        review.getRating().getStars(),
                        review.getComments());
            }

            private String getText(String key){
                return resources.getString(key);
            }

        }


    }
