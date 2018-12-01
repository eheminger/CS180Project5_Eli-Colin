/**
 * <h1>Shipping Address</h1> Represents a shipping address
 */
public class ShippingAddress {
	private String name;
	private String address;
	private String city;
	private String state;
	private int zipCode;

    /**
     * Default constructor
     */
    public ShippingAddress() {
        new ShippingAddress("", "", "", "", 0);
    }

    public ShippingAddress(String name, String address, String city, String state, int zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /*
     * Getter methods
     */

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    /*
     * Setters
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
