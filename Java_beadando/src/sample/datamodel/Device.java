package sample.datamodel;

import javafx.beans.property.SimpleStringProperty;


public class Device {
    private SimpleStringProperty brandName = new SimpleStringProperty("");
    private SimpleStringProperty phoneType = new SimpleStringProperty("");
    private SimpleStringProperty phonePrice = new SimpleStringProperty("");
    private SimpleStringProperty phoneColor = new SimpleStringProperty("");

    public Device() {
    }

    public Device(String brandName, String phoneType, String phonePrice, String phoneColor) {
        this.brandName.set(brandName);
        this.phoneType.set(phoneType);
        this.phonePrice.set(phonePrice);
        this.phoneColor.set(phoneColor);
    }

    public String getBrandName() {
        return brandName.get();
    }

    public SimpleStringProperty brandNameProperty() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName.set(brandName);
    }

    public String getPhoneType() {
        return phoneType.get();
    }

    public SimpleStringProperty phoneTypeProperty() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType.set(phoneType);
    }

    public String getPhonePrice() {
        return phonePrice.get();
    }

    public SimpleStringProperty phonePriceProperty() {
        return phonePrice;
    }

    public void setPhonePrice(String phonePrice) {
        this.phonePrice.set(phonePrice);
    }

    public String getPhoneColor() {
        return phoneColor.get();
    }

    public SimpleStringProperty phoneColorProperty() {
        return phoneColor;
    }

    public void setPhoneColor(String phoneColor) {
        this.phoneColor.set(phoneColor);
    }

    @Override
    public String toString() {
        return "Devices{" +
                "brandName=" + brandName +
                ", phoneType=" + phoneType +
                ", phonePrice=" + phonePrice +
                ", phoneColor=" + phoneColor +
                '}';
    }
}
