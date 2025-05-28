package data.dto;

public class MenuDTO {
    private String type, name, price, sourcePicture;

    public MenuDTO(String type, String name, String price, String sourcePicture) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.sourcePicture = sourcePicture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSourcePicture() {
        return sourcePicture;
    }

    public void setSourcePicture(String sourcePicture) {
        this.sourcePicture = sourcePicture;
    }
}
