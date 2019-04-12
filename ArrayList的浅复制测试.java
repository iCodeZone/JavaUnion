import java.util.ArrayList;

class Temp {
    public int x;

    Temp() {
        x = 1;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "x=" + x;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Temp> arrayList = new ArrayList<>();
        arrayList.add(new Temp());
        arrayList.add(new Temp());
        System.out.println(arrayList);
        Object[] objects = arrayList.toArray();
        ((Temp) objects[0]).x = 2;
        System.out.println(objects[0]);
        System.out.println(arrayList);

    }


}