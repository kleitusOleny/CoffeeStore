package model.order_system;

import data.ReadFileJson;
import data.dto.MenuDTO;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuDTO> menuDTOList = ReadFileJson.readFileJSONForMenu();

    private String[][] checkDrinkByType(String typeDrink) {
        List<String[]> listResult = new ArrayList<>();
        for (MenuDTO menuDTO : menuDTOList) {
            if (menuDTO.getType().equals(typeDrink)) {
                listResult.add(new String[]{
                        menuDTO.getName(),
                        menuDTO.getPrice(),
                        menuDTO.getSourcePicture()
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