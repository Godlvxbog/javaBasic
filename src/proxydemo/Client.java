package proxydemo;

/**
 * Created by bo.wu on 2017/4/26.
 */
public class Client {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        People people1 = new People();
        people1.setMoney(3000);

        proxy.setPeople(people1);
        proxy.buycar();
    }
}
