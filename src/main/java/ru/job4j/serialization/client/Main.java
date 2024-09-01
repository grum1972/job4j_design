package ru.job4j.serialization.client;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        final Client client = new Client(false, 50, new Address("Belgorod, Orlova st., 50"),
                new String[]{"MAN", "DAF"});
        JAXBContext context = JAXBContext.newInstance(Client.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {

            marshaller.marshal(client, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Client result = (Client) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
