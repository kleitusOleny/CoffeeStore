package model.order_system;

import data.ReadFileJson;
import data.dto.FormatMenu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<FormatMenu> formatMenuList = ReadFileJson.readFileJSONForMenu();

    private String[][] checkDrinkByType(String typeDrink) {
        List<String[]> listResult = new ArrayList<>();
        for (FormatMenu formatMenu : formatMenuList) {
            if (formatMenu.getType().equals(typeDrink)) {
                listResult.add(new String[]{
                        formatMenu.getName(),
                        formatMenu.getPrice(),
                        formatMenu.getSourcePicture()
                });
            }
        }
        return listResult.toArray(new String[0][0]);
    }

    private final String[][] coffeeDrinks = checkDrinkByType("coffee");
    
    private final String[][] teaDrinks = checkDrinkByType("tea");
    
    private final String[][] toppings = checkDrinkByType("topping");

    public String[][] getCoffeeDrinks() {
        return coffeeDrinks;
    }
    
    public String[][] getTeaDrinks() {
        return teaDrinks;
    }
    
    public String[][] getToppings() {
        return toppings;
    }
}