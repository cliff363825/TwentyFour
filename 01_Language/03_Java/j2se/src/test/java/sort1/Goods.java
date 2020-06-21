package sort1;

public class Goods implements Comparable {
    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }

        if (!(o instanceof Goods)) {
            return 1;
        }

        Goods goods = (Goods) o;
        if (this.price > goods.price) {
            return 1;
        } else if (this.price < goods.price) {
            return -1;
        } else {
            return 0;
        }
    }
}
