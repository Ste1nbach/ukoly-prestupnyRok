import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Vyberte jednu z možností:");
            System.out.println("1. Zjištění přestupného roku podle zadaného roku");
            System.out.println("2. Zjištění přestupného roku podle aktuálního data");
            System.out.println("3. Vlastní formát data a času podle systémového času");
            System.out.println("4. Zbývající čas do určitého data");
            System.out.println("5. Konec programu");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Zadejte rok: ");
                    int year = scanner.nextInt();
                    boolean isLeap = isLeapYear(year);
                    System.out.println("Rok " + year + " je " + (isLeap ? "přestupný." : "ne přestupný."));
                    break;

                case 2:
                    int currentYear = getCurrentYear();
                    System.out.println("Aktuální rok je: " + currentYear);
                    boolean isCurrentYearLeap = isLeapYear(currentYear);
                    System.out.println("Tento rok je " + (isCurrentYearLeap ? "přestupný." : "ne přestupný."));
                    if (!isCurrentYearLeap) {
                        System.out.println("Následující přestupný rok je: " + getNextLeapYear(currentYear));
                    }
                    break;

                case 3:
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    long currentTimeMillis = System.currentTimeMillis();
                    Date currentDateTime = new Date(currentTimeMillis);
                    String formattedDateTime = dateFormat.format(currentDateTime);
                    System.out.println("Aktuální systémový čas: " + formattedDateTime);
                    break;

                case 4:
                    System.out.println("Zadejte den: ");
                    int day = scanner.nextInt();
                    System.out.println("Zadejte měsíc (1-12): ");
                    int month = scanner.nextInt();
                    System.out.println("Zadejte rok: ");
                    int targetYear = scanner.nextInt();

                    Date currentDate = new Date();
                    Date targetDate = new Date(targetYear - 1900, month - 1, day);
                    long timeDiffMillis = targetDate.getTime() - currentDate.getTime();
                    long timeDiffSeconds = timeDiffMillis / 1000;
                    long timeDiffMinutes = timeDiffSeconds / 60;
                    long timeDiffHours = timeDiffMinutes / 60;
                    long timeDiffDays = timeDiffHours / 24;
                    long timeDiffMonths = timeDiffDays / 30;
                    long timeDiffYears = timeDiffDays / 365;

                    System.out.println("Do zadaného data zbývá:");
                    System.out.println(timeDiffYears + " let, " + timeDiffMonths + " měsíců, " + timeDiffDays + " dní, " +
                            timeDiffHours % 24 + " hodin, " + timeDiffMinutes % 60 + " minut, " + timeDiffSeconds % 60 + " sekund.");
                    break;

                case 5:
                    isRunning = false;
                    break;

                default:
                    System.out.println("Neplatná volba. Zvolte znovu.");
                    break;
            }
        }
    }

    // Funkce pro zjištění, zda je rok přestupný
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }

    // Funkce pro získání aktuálního roku
    public static int getCurrentYear() {
        Date currentDate = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        return Integer.parseInt(yearFormat.format(currentDate));
    }

    // Funkce pro získání následujícího přestupného roku
    public static int getNextLeapYear(int currentYear) {
        int nextYear = currentYear + 1;
        while (!isLeapYear(nextYear)) {
            nextYear++;
        }
        return nextYear;
    }
}