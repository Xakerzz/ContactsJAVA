package contacts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person extends Common implements EditPerson {
    private String name;
    private String surname;
    private String dateOfBirth;
    private String gender;

    public Person(String number, String name, String surname, String dateOfBirth, String gender) {
        super(number);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Person() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void editName(String inPut) {
        this.setName(inPut);
    }

    @Override
    public void editSurname(String inPut) {
        this.setSurname(inPut);
    }

    @Override
    public void editBirth(String inPut) {
        this.setDateOfBirth(inPut);
    }

    @Override
    public void editGender(String inPut) {
        this.setGender(inPut);
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
