package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkApp {
    private boolean isWorking;

    static private Scanner scanner = new Scanner(System.in);
    static public List<Common> array = new ArrayList<>();

    public WorkApp() {
        this.setWorking(true);
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public void start() {
        while (isWorking()) {
            printMainMessage();
            checkInPut(getInPut());

        }
    }

    private void printMainMessage() {
        System.out.println("[menu] Enter action (add, list, search, count, exit):");
    }

    private String getInPut() {
        String inPut = scanner.nextLine().trim();

        return inPut;
    }

    private void checkInPut(String inPut) {
        switch (inPut.trim()) {
            case "add":
                printAddMessage();
                checkWhoToAdd(getInPut());
                break;
            case "search":
                searchItem();
                break;
            case "count":
                count();
                break;
            case "list":
                getInfo();
                break;
            case "exit":
                setWorking(false);
                System.exit(0);
                break;
            default:

        }
    }

    private void searchItem() {
        boolean isWorking = true;
        while (isWorking) {
            System.out.println("Enter search query:");
            String inPut = getInPut().trim();

            StringBuilder sb = new StringBuilder();
            int j = 0;
            int i = 1;
            for (Common ithem : array) {

                if (ithem instanceof Person) {
                    if (((Person) ithem).getName().toLowerCase().contains(inPut) || ((Person) ithem).getSurname().toLowerCase().contains(inPut) ||  ithem.getNumber().contains(inPut)) {
                        sb.append(i).append(". ").append(((Person) ithem).getName()).append(" ").append(((Person) ithem).getSurname()).append("\n");
                        j++;
                    }
                } else if (ithem instanceof Organization) {
                    if (((Organization) ithem).getName().toLowerCase().contains(inPut) || ithem.getNumber().contains(inPut)) {
                        sb.append(i).append(". ").append(((Organization) ithem).getName()).append("\n");
                        j++;
                    }
                }
                i++;
            }
            System.out.printf("Found %d results:", j);
            String outPut = sb.toString();
            System.out.println(outPut);
            System.out.println("[search] Enter action ([number], back, again):");
            inPut = getInPut().trim();
            switch (inPut) {
                case "back":
                    isWorking = false;
                    break;
                case "again":
                    continue;
                default:
                    try {
                        int index = Integer.parseInt(inPut);
                        showIthem(index - 1);
                        record(index - 1);
                        return;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong inPut");
                    }

            }

        }
    }

    private void showIthem(int index) {
        Common showObject = array.get(index);
        if (showObject instanceof Person person) {
            System.out.printf("Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n\n",
                    person.getName(), person.getSurname(), person.getDateOfBirth(), person.getGender(), person.getNumber(), person.getTimeCreate(), person.getTimeLastUpdate());
        } else if (showObject instanceof Organization organization) {
            System.out.printf("Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n\n",
                    organization.getName(), organization.getAddress(), organization.getNumber(), organization.getTimeCreate(), organization.getTimeLastUpdate());
        }
    }

    private void editItem(int index) {
        if (!array.isEmpty()) {
            index -= 1;
            if (array.get(index) instanceof Person) {
                System.out.println("Select a field (name, surname, birth, gender, number):");
                String inPut = getInPut();
                editPerson(index, inPut);
            } else if (array.get(index) instanceof Organization) {
                System.out.println("Select a field (address, number):");
                String inPut = getInPut();
                editOrganization(index, inPut);
            }
        } else {
            System.out.println("No records to edit");
        }
    }


    private void count() {
        System.out.printf("The Phone Book has %s records.\n\n", array.size());
    }

    private void printAddMessage() {
        System.out.println("Enter the type (person, organization):");
    }

    private void checkWhoToAdd(String inPut) {
        switch (inPut) {
            case "person":
                addPerson();
                break;
            case "organization":
                addOrganization();
                break;
        }
    }

    private void addPerson() {
        Person person = new Person();
        System.out.println("Enter the name:");
        String name = getInPut().trim();
        if (name.isEmpty()) {
            person.setName("[no name]");
        } else {
            person.setName(name);
        }

        System.out.println("Enter the surname:");
        String surname = getInPut().trim();
        if (name.isEmpty()) {
            person.setSurname("[no surname]");
        } else {
            person.setSurname(surname);
        }

        System.out.println("Enter the birth date:");
        String birthDate = getInPut().trim();
        if (!birthDate.isEmpty()) {
            person.setDateOfBirth(birthDate);
        } else {
            person.setDateOfBirth("[no data]");
            System.out.println("Bad birth date!");
        }

        System.out.println("Enter the gender (M, F):");
        String gender = getInPut().trim();
        if (gender.matches("F") || gender.matches("M")) {
            person.setGender(gender);
        } else {
            person.setGender("[no data]");
            System.out.println("Bad gender!");
        }

        System.out.println("Enter the number:");
        String phoneNumber = getInPut().trim();
        Pattern pattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            person.setNumber(phoneNumber);
        } else {
            person.setNumber("[no number]");
        }
        array.add(person);
        System.out.println("The record added.\n");
    }

    private void addOrganization() {
        Organization organization = new Organization();
        System.out.println("Enter the organization name:");
        String name = getInPut().trim();
        if (name.isEmpty()) {
            organization.setName("[no data]");
        } else {
            organization.setName(name);
        }

        System.out.println("Enter the address:");
        String surname = getInPut().trim();
        if (name.isEmpty()) {
            organization.setAddress("[no data]");
        } else {
            organization.setAddress(surname);
        }

        System.out.println("Enter the number:");
        String phoneNumber = getInPut().trim();
        Pattern pattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            organization.setNumber(phoneNumber);
        } else {
            organization.setNumber("[no number]");
        }
        array.add(organization);
        System.out.println("The record added.\n");
    }

    private void remove(int index) {
        if (!array.isEmpty()) {
            array.remove(index - 1);
        } else {
            System.out.println("No records to remove!");
        }
    }

    private void editPerson(int index, String inPut) {
        Common common = new Common();
        Person person = (Person) array.get(index);

        switch (inPut) {
            case "name":
                System.out.println("Enter the name:");
                person.editName(getInPut());
                break;
            case "surname":
                System.out.println("Enter the surname:");
                person.editSurname(getInPut());
                break;
            case "birth":
                System.out.println("Enter the birth date:");
                person.editBirth(getInPut());
                break;
            case "gender":
                System.out.println("Enter the gender (M, F):");
                person.editGender(getInPut());
                break;
            case "number":
                System.out.println("Enter number:");
                person.editNumber(getInPut());
                break;
        }
        person.setTimeLastUpdate(common.getTimeLastUpdate());

        System.out.println("The record updated!\n");
        common = null;
    }

    private void editOrganization(int index, String inPut) {
        Common common = new Common();
        Organization organization = (Organization) array.get(index);

        switch (inPut) {
            case "address":
                System.out.println("Enter the address:");
                organization.editAddress(getInPut());
                break;
            case "number":
                System.out.println("Enter the number:");
                organization.editNumber(getInPut());
                break;
        }
        organization.setTimeLastUpdate(common.getTimeLastUpdate());


        System.out.println("The record updated!\n\n");
        common = null;
    }

    private void infoForRemoveAndInfo() {
        for (int i = 0; i < array.size(); i++) {
            Common object = array.get(i);
            if (object instanceof Person person) {
                System.out.printf(i + 1 + ". %s %s\n", person.getName(), person.getSurname());
            } else if (object instanceof Organization organization) {
                System.out.printf(i + 1 + ". %s\n", organization.getName());
            }
        }
        System.out.println();
    }

    private void getInfo() {


        if (!array.isEmpty()) {
            infoForRemoveAndInfo();
            System.out.println("[list] Enter action ([number], back):");
            String inPut = getInPut().trim();
            if (inPut.equals("back")) {
                return;
            }
            int index = Integer.parseInt(inPut);
            Common showObject = array.get(index - 1);
            if (showObject instanceof Person person) {
                System.out.printf("Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n\n",
                        person.getName(), person.getSurname(), person.getDateOfBirth(), person.getGender(), person.getNumber(), person.getTimeCreate(), person.getTimeLastUpdate());
            } else if (showObject instanceof Organization organization) {
                System.out.printf("Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n\n",
                        organization.getName(), organization.getAddress(), organization.getNumber(), organization.getTimeCreate(), organization.getTimeLastUpdate());
            }

            record(index);


        } else {
            System.out.println("Nothing to show.");
        }
    }

    private void record(int index) {
        boolean isWorking = true;
        while (isWorking) {
            System.out.println("[record] Enter action (edit, delete, menu):");
            String inPut = getInPut().trim();
            switch (inPut) {
                case "menu":
                    isWorking = false;
                    continue;

                case "delete":
                    remove(index);
                    break;
                case "edit":
                    editItem(index);

            }
        }
    }

}

