package model.order_system;

public class Menu {
    private final String[][] coffeeDrinks = {
            {"Cà phê đen", "25000", "src\\main\\image\\coffee.png"},
            {"Bạc xỉu", "22000", "src\\main\\image\\milkcoffee.png"},
            {"Espresso", "28000", "src\\main\\image\\expresso.png"},
            {"Americano", "28000", "src\\main\\image\\americano.png"},
    };
    
    private final String[][] teaDrinks = {
            {"Trà đào", "30000", "src\\main\\image\\peachtea.png"},
            {"Trà sữa trân châu", "32000", "src\\main\\image\\milktea.png"},
    };
    
    private final String[][] toppings = {
            {"Trân châu mật ong", "5000", "src\\main\\image\\honeyboba.png"},
            {"Trân châu", "5000", "src\\main\\image\\boba.png"},
            {"Kem cheese", "6000", "src\\main\\image\\creamcheese.png"},
            {"Bánh flan", "7000", "src\\main\\image\\flan.png"}
    };
    
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