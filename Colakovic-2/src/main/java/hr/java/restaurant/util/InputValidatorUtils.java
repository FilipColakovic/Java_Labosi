package hr.java.restaurant.util;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidatorUtils {
    public static BigDecimal validatePositiveBigDecimal(Scanner sc, String message,
                                                        String errorMessage) {
        BigDecimal number = new BigDecimal(0);
        Boolean isValid;

        do {
            isValid = true;

            try {
                System.out.println(message);
                number = sc.nextBigDecimal();

                if(number.compareTo(BigDecimal.ZERO) < 0) {
                    isValid = false;
                    System.out.println(errorMessage);
                }

            } catch (InputMismatchException e) {
                isValid = false;
                System.out.println(errorMessage);
                sc.nextLine();
            }
        } while(!isValid);
        return number;
    }
}
