import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BillCalculator {

    Scanner scanner = new Scanner(System.in);

    public void start() {


        System.out.println("Добро пожаловать в калькулятор счетов!");
        System.out.println("Разделите свой счет с друзьями :");
        System.out.println("Для этого сначала введите количество друзей.");
        System.out.println("Далее добавляйте товары и их стоимость.");
        System.out.println("Для того, чтобы закончить работу программы введите слово 'Завершить'.");
        System.out.println("Введите количество друзей, которые разделят счет:");

        while (true) {
           int friends = friends();
            if (friends < 2) {
                System.out.println("Вам не разделить этот счет на " + friends + " %d друзей");
                if (printMenuExit()) {
                break;
                }
            } else {
               executeCalculate(friends);
            }
        }
    }

    public boolean printMenuExit() {

        scanner.nextLine();

        System.out.println("................................");
        System.out.println("Хотите продолжить или завершить?\nДля завершения программы введите слово 'Завершить' в любом регистре,\nдля продолжения нажмите на любую клавишу.");
        String commandExit = scanner.nextLine();
        commandExit = commandExit.toLowerCase();
        if (commandExit.equals("завершить")) {
            System.out.println("Выход. Спасибо за использование!");
            return true;
        } else {
            return false;
        }
    }

    public double priceGoods() {

        while (true) {
            try {
                return scanner.nextDouble();
            }
            catch (InputMismatchException e) {
                System.out.println("Вы ввели неправильную цену, повторите ввод!");
                scanner.next();
            }
        }
    }

    public int friends() {

        while (true) {
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Вы ввели неправильно количество друзей.");
                if (printMenuExit()) {
                    return 0;
                } else {
                    System.out.println("Введите количество друзей:");
                }
            }
        }
    }

    public String wordCurrency(double price) {

        String[] wordCurrencyList = {
                "рубль", "рубля", "рублей"
        };

        int lastSimbolPrice = (int) price % 10;
        int twoLastSimbolPrice = (int) price % 100;

        if (twoLastSimbolPrice >= 11 && twoLastSimbolPrice<= 19) {
            return wordCurrencyList[2];
        } else {
            return switch (lastSimbolPrice) {
                case 1          -> wordCurrencyList[0];
                case 2, 3, 4    -> wordCurrencyList[1];
                default         -> wordCurrencyList[2];
            };
        }
    }

    private void executeCalculate(int friends) {
        int i = 0;
        ArrayList<Goods> goodsList = new ArrayList<>();

        while (true) {

            i++;
            System.out.println("Введите наименование товара " + i + ":");
            String nameGoods = scanner.next();
            System.out.println("Введите цену товара " + i +":");
            Goods goods = new Goods(nameGoods, priceGoods(), i);
            goodsList.add(goods);
            System.out.println("----\nСЧЕТ\n----");

            double totalPrice = 0;
            for (int x = 0; x < goodsList.size(); x++) {
                System.out.println(String.format("%d. %s - %.2f %s;", goodsList.get(x).numberGoogs, goodsList.get(x).nameGoods, goodsList.get(x).priceGoods, wordCurrency(goodsList.get(x).priceGoods)));
                totalPrice = totalPrice + goodsList.get(x).priceGoods;
            }
            double friendsPrice = totalPrice / friends;

            System.out.println(String.format("Итоговая сумма: %.2f %s.", totalPrice, wordCurrency(totalPrice)));
            System.out.println(String.format("Друзей: %d, каждому необходимо заплатить - %.2f %s!", friends, friendsPrice, wordCurrency(friendsPrice)));

            if (printMenuExit()) {
                break;
            }
        }
    }
}
