package org.example.eager_algos.backpack;

/*
Первая строка содержит количество предметов 1≤n≤10n3 и вместимость рюкзака 0≤W≤2⋅10n6.
Каждая из следующих n строк задаёт стоимость 0≤ci≤2⋅10n6 и объём 0<wi≤2⋅10n6 предмета (n, W, ci, wi — целые числа).
Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть, стоимость и объём
при этом пропорционально уменьшатся), помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.

Sample Input:
3 50
60 20
100 50
120 30

Sample Output:
180.000
 */

import java.util.*;


public class ContinuousBackpack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amountItems = scanner.nextInt();
        int capacityBag = scanner.nextInt();
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < amountItems ; i++) {
            items.add(new Item(scanner.nextInt(), scanner.nextInt()));
        }
        //System.out.println(items);
        items.sort(Comparator.comparingDouble(a -> a.costPerKG));
        double costInBag = 0;
        int load = 0;
        for(int i = items.size()-1; i >= 0; i--) {
            if (items.get(i).volume <= capacityBag - load) {
                costInBag += items.get(i).cost;
                load += items.get(i).volume;
            } else if (items.get(i).volume > capacityBag - load) {
                costInBag += items.get(i).costPerKG*(capacityBag - load);
                break;
            }
        }
        System.out.printf ("%.3f",costInBag);
    }
}

class Item {
    int cost;
    int volume;
    double costPerKG;

    public Item (int cost, int volume) {
        this.cost = cost;
        this.volume = volume;
        costPerKG = (double)cost/volume;
    }

    @Override
    public String toString() {
        return "Item{" +
                "cost=" + cost +
                ", volume=" + volume +
                ", costPerKG=" + costPerKG +
                '}';
    }
}
