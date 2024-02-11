package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Organization extends Common implements EditOrganization {
    private String name;
    private String address;

    public Organization(String number, String name, String address) {
        super(number);
        this.name = name;
        this.address = address;
    }

    public Organization() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void editAddress(String inPut) {
        this.setAddress(inPut);
    }

    @Override
    public void editNumber(String inPut) {
        Pattern pattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*$");
        Matcher matcher = pattern.matcher(inPut);
        if (matcher.matches()) {
            this.setNumber(inPut);
        } else {
            this.setNumber("[no number]");
        }
    }
}
