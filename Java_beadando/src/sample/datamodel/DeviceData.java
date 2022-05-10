package sample.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;


public class DeviceData {

    private static final String DEVICES_FILE = "devices.xml";

    private static final String DEVICE = "device";
    private static final String BRAND_NAME = "brand_name";
    private static final String PHONE_TYPE = "phone_type";
    private static final String PHONE_PRICE = "phone_price";
    private static final String PHONE_COLOR = "color";

    private ObservableList<Device> devices;

    public DeviceData() {
        devices = FXCollections.observableArrayList();
    }

    public ObservableList<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device item) {
        devices.add(item);
    }

    public void deleteDevice(Device item) {
        devices.remove(item);
    }

    public void loadDevices() {
        try {

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();

            InputStream in = new FileInputStream(DEVICES_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            Device device = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals(DEVICE)) {
                        device = new Device();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(BRAND_NAME)) {
                            event = eventReader.nextEvent();
                            device.setBrandName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PHONE_TYPE)) {
                        event = eventReader.nextEvent();
                        device.setPhoneType(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PHONE_PRICE)) {
                        event = eventReader.nextEvent();
                        device.setPhonePrice(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PHONE_COLOR)) {
                        event = eventReader.nextEvent();
                        device.setPhoneColor(event.asCharacters().getData());
                        continue;
                    }
                }


                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(DEVICE)) {
                        devices.add(device);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {

        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveDevices() {

        try {

            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(DEVICES_FILE));

            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");

            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement devicesStartElement = eventFactory.createStartElement("",
                    "", "devices");
            eventWriter.add(devicesStartElement);
            eventWriter.add(end);

            for (Device device : devices) {
                saveDevice(eventWriter, eventFactory, device);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "devices"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Devices file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing device: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveDevice(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Device device)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");


        StartElement configStartElement = eventFactory.createStartElement("",
                "", DEVICE);
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        createNode(eventWriter, BRAND_NAME, device.getBrandName());
        createNode(eventWriter, PHONE_TYPE, device.getPhoneType());
        createNode(eventWriter, PHONE_PRICE, device.getPhonePrice());
        createNode(eventWriter, PHONE_COLOR, device.getPhoneColor());

        eventWriter.add(eventFactory.createEndElement("", "", DEVICE));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);

        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);

        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

}
