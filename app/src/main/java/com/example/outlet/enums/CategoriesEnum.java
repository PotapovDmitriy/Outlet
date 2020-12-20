package com.example.outlet.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class CategoriesEnum {
    static Map<String, ArrayList<String>> category;
    public CategoriesEnum() {
        category = new HashMap<>();

        ArrayList<String> jackets = new ArrayList<>(Arrays.asList("Jacket", "Parka & Coat", "Jacket in imitation leather",
                "Пальто", "Куртки", "Biker Jackets"));//Куртки, пальто
        ArrayList<String> underwear = new ArrayList<>(Arrays.asList("Underwear","Боди"));//нижнее белье
        ArrayList<String> shorts = new ArrayList<>(Arrays.asList("Short Sleeve","Shirt","Waistcoat","Polo","Jersey",
                "Long Sleeve","Blouse & Shirt","Top","Футболки","Топы","Рубашки"));//Футболки, блузы
        ArrayList<String> sweeter = new ArrayList<>(Arrays.asList("Pullover","Sweatshirt","Cardigan","Blazer","Кардиганы",
                "Джемперы","Мужские пиджаки, блейзеры и жилетки"));//кофты, пиджаки
        ArrayList<String> pants = new ArrayList<>(Arrays.asList("Chinos","Trousers","Slim Straight","Slim","Sweatpants",
                "Denim","Shorts & Trousers","Jumpsuit","Skinny","High Waist","Брюки","Мужские джоггеры и спортивные штаны",
                "Брючные комбинезоны","Комбинезоны с шортами","Leggings"));//брюки, штаны, джинсы
        ArrayList<String> clothes = new ArrayList<>(Arrays.asList("High Heels","Boot","Sneakers",
                "Сандалии и эспадрильи","Кроссовки","Ботильоны","Кеды","Сапоги И Ботинки","Туфли На Каблуке",
                "Обувь На Плоской Подошве","shoes"));//обувь
        ArrayList<String> others = new ArrayList<>(Arrays.asList("Beachwear","Woven","Sleepwear","Other","Вязаные Изделия"));//другое
        ArrayList<String> accessories = new ArrayList<>(Arrays.asList("Backpack","Belt","Bag","Hat & Cap","Gloves",
                "Scarf","Socks","Jewellery","Small Bag","Аксессуары","Cумки","accessories"));//аксессуары
        ArrayList<String> dress = new ArrayList<>(Arrays.asList("Short Dress","Midi Skirt","Short Skirt","Maxi Dress",
                "Платья","Юбки","Midi Dress"));//платья, юбки
        ArrayList<String> clothing = new ArrayList<>(Arrays.asList("clothing","Без категории","Special"));//товары без категорий
        category.put("Куртки, пальто", jackets);
        category.put("Нижнее белье", underwear);
        category.put("Футболки, рубашки", shorts);
        category.put("Кофты, пиджаки", sweeter);
        category.put("Штаны, брюки, джинсы", pants);
        category.put("Обувь", clothes);
        category.put("Другое", others);
        category.put("Аксессуары", accessories);
        category.put("Платья, юбки", dress);
        category.put("Товары без категории", clothing);
    }
    public static ArrayList<String> getCategories(String key){
        return category.get(key);
    }


}
