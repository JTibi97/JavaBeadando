package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.datamodel.Device;


public class DevicesController {

    @FXML
    private TextField brandNameField;

    @FXML
    private TextField phoneTypeField;

    @FXML
    private TextField phonePriceField;

    @FXML
    private TextField phoneColorField;

    public Device getNewDevice() {
        String firstName = brandNameField.getText();
        String lastName = phoneTypeField.getText();
        String phoneNumber = phonePriceField.getText();
        String notes = phoneColorField.getText();

        Device newDevice = new Device(firstName, lastName, phoneNumber, notes);
        return newDevice;
    }

    public void editDevice(Device device) {
        brandNameField.setText(device.getBrandName());
        phoneTypeField.setText(device.getPhoneType());
        phonePriceField.setText(device.getPhonePrice());
        phoneColorField.setText(device.getPhoneColor());
    }

    public void updateDevice(Device device) {
        device.setBrandName(brandNameField.getText());
        device.setPhoneType(phoneTypeField.getText());
        device.setPhonePrice(phonePriceField.getText());
        device.setPhoneColor(phoneColorField.getText());
    }

}















