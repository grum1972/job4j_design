package ru.job4j.serialization.client;
import jakarta.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {

    @XmlAttribute
    private boolean owner;
    @XmlAttribute
    private int age;
    private Address address;

    @XmlElementWrapper(name = "autos")
    @XmlElement(name = "auto")
    private String[] autos;

    public Client() {
    }

    public Client(boolean owner, int age, Address address, String[] autos) {
        this.owner = owner;
        this.age = age;
        this.address = address;
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "Client{"
                + "owner=" + owner
                + ", age=" + age
                + ", address=" + address
                + ", autos=" + Arrays.toString(autos)
                + '}';
    }

}
