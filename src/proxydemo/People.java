package proxydemo;

/**
 * Created by bo.wu on 2017/4/26.
 * 添加具有购买汽车的人；
 * 人不能直接得到汽车，必须用proxy进行代理购买
 *
 */
public class People implements BuyCar{
    public String username;
    public int money;
    public boolean isvip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isIsvip() {
        return isvip;
    }

    public void setIsvip(boolean isvip) {
        this.isvip = isvip;
    }

    @Override
    public void buycar() {

    }
}
