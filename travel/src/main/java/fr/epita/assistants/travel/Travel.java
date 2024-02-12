package fr.epita.assistants.travel;

public class Travel {
    public static void travelTo(Country source, Country destination) {
        System.out.println("Boarding in " + source.countryName + ", local date and time is: Fri, 12 Oct 2023 16:46:12 +0100");
        System.out.println("Landing in " + destination.countryName + ", local date and time on arrival will be: Sat, 13 Oct 2023 10:39:41 -0600");
    }
}
