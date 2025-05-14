import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj liczbe rodzajow przedmiotow: ");
        int n = scanner.nextInt();

        System.out.print("Podaj ziarno: ");
        long seed = scanner.nextLong();

        System.out.print("Podaj pojemność plecaka: ");
        int capacity = scanner.nextInt();

        int lowerBound = 1;
        int upperBound = 10;

        Problem problem = new Problem(n, seed, lowerBound, upperBound);

        System.out.println(problem.toString());

        Result result = problem.solve(capacity);

        System.out.println("-------------");
        System.out.println(result.toString());

        scanner.close();
    }
}
// Zapisuje dane i tworzy tablice do przechowywanaia itemków
class Problem {
    private int n;
    private long seed;
    private int lowerBound;
    private int upperBound;
    private List<Item> items;

    public Problem(int n, long seed, int lowerBound, int upperBound) {
        this.n = n;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.items = new ArrayList<>();

        generateItems();
    }
    //Losujemy generacje itemków
    private void generateItems() {
        Random random = new Random(seed);

        for (int i = 0; i < n; i++) {
            int value = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int weight = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            items.add(new Item(i, value, weight));
        }
    }
    //Wrzuca itemki do plecaka te z najlepszym ratio
    public Result solve(int capacity) {
        Result result = new Result();

        List<Item> sortedItems = new ArrayList<>(items);
        sortedItems.sort(Comparator.comparingDouble(Item::getRatio).reversed());

        int remainingCapacity = capacity;

        for (Item item : sortedItems) {
            if (item.getWeight() <= 0) continue;

            int count = remainingCapacity / item.getWeight();

            if (count > 0) {
                result.addItem(item, count);

                remainingCapacity -= count * item.getWeight();
            }
        }

        return result;
    }
    //getter
    public List<Item> getItems() {
        return items;
    }
    //getter
    public int getItemCount() {
        return items.size();
    }
    //Nadpisna metoda, aby łatwiej i w sposób bardziej czytelny wypisać informacje
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            sb.append("No: ").append(i).append(" v: ").append(item.getValue())
                    .append(" w: ").append(item.getWeight()).append("\n");
        }

        return sb.toString();
    }
}
//Przechowuje infromacje o id, value oraz weight a później settery.
class Item {
    private int id;
    private int value;
    private int weight;

    public Item(int id, int value, int weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public double getRatio() {
        return (double) value / weight;
    }
    //Nadpisana metoda służaca to lepszego outuptu
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", value=" + value +
                ", weight=" + weight +
                '}';
    }
}
//Klasa wyświetlająca nasze rezulataty
class Result {
    private List<ItemCount> items;
    private int totalWeight;
    private int totalValue;

    public Result() {
        this.items = new ArrayList<>();
        this.totalWeight = 0;
        this.totalValue = 0;
    }

    public void addItem(Item item, int count) {
        items.add(new ItemCount(item, count));
        totalWeight += item.getWeight() * count;
        totalValue += item.getValue() * count;
    }
    //gettery
    public List<ItemCount> getItems() {
        return items;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTotalValue() {
        return totalValue;
    }
    //Znów nadpisana metoda do lepszego wyświetlania
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (ItemCount itemCount : items) {
            Item item = itemCount.getItem();
            int count = itemCount.getCount();

            for (int i = 0; i < count; i++) {
                sb.append("No: ").append(item.getId())
                        .append(" v: ").append(item.getValue())
                        .append(" w: ").append(item.getWeight())
                        .append("\n");
            }
        }

        sb.append("Weight: ").append(totalWeight).append("\n");
        sb.append("Value: ").append(totalValue);

        return sb.toString();
    }

    private static class ItemCount {
        private Item item;
        private int count;

        public ItemCount(Item item, int count) {
            this.item = item;
            this.count = count;
        }

        public Item getItem() {
            return item;
        }

        public int getCount() {
            return count;
        }
    }
}