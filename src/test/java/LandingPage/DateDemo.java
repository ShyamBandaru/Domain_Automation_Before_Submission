package LandingPage;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateDemo {
    public static void main(String[] args) {
        String dateString = "2023-12-25T22:40:11.349+00:00"; // Change this to your desired date string


        // Define the DateTimeFormatter with the appropriate pattern
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        // Parse the input date string into an OffsetDateTime
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);

        // Convert OffsetDateTime to epoch time (milliseconds since January 1, 1970)
        long validthrough = offsetDateTime.toInstant().toEpochMilli();

        // Print the epoch time
        System.out.println("Valid Through time is: " + validthrough);

        String datestring2 = "2023-11-27T22:40:11.349+00:00";
        OffsetDateTime offsetDateTime1= OffsetDateTime.parse(datestring2,formatter);
        long dateposted = offsetDateTime1.toInstant().toEpochMilli();
        System.out.println("Date Posted Time is "+dateposted);
        long result = validthrough-dateposted;
        long expected = (long) 1000 *60*60*24*28;//28 DAYS * 24 HOURS * 60 MINS * 60 SECS * 1000

        System.out.println(result);
        System.out.println(expected);
    }
}
