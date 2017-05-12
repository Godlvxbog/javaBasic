package proxydemo;

/**
 * Created by bo.wu on 2017/4/26.
 */
public class Proxy implements BuyCar{
    People people;

    @Override
    public void buycar() {
        if (people.getMoney() > 5000){
            System.out.println("钱够了，可以买车");
        }else {
            System.out.println("钱不够，不可以买车");
        }
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
