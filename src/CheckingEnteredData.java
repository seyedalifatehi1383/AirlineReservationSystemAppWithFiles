import java.util.Objects;

//  این کلاس بدین منظور طراحی شده که بیاد و
//  درستی دیتاهایی که در حین اضافه و یا تغییر اطلاعات یک پرواز، درستی وارد شدن یک عدد (یعنی به جای عدد حرف انگلیسی و یا چیزای دیگه وارد نشه) و... رو بررسی کنه
public class CheckingEnteredData {
    //    این متد برای بررسی این است که مقدار ورودی که باید عدد باشد به درستی لحاظ شده یا نه.
    public boolean isEnteredNumberRight (String enteredNumber) {
        if (Objects.equals(enteredNumber, "")) {
            return false;
        }

        char[] enteredNumberCharArray = enteredNumber.toCharArray();
        for (int i = 0; i < enteredNumber.length(); i++) {
            if (!(enteredNumberCharArray[i] >= '0' && enteredNumberCharArray[i] <= '9')) {
                return false;
            }
        }
        return true;
    }


    //    این متد برای این است که مقدار ورودی برای تاریخ پرواز به درستی وارد شده است یا خیر.
    public boolean isEnteredDateRight (String enteredDate) {
        if (enteredDate.length() != 10) {
            return false;
        }

        else {
            char[] enteredDateCharArray = enteredDate.toCharArray();
            for (int i = 0; i < 10; i++) {
                if (i == 4 || i == 7) {
                    continue;
                }

                if (!(enteredDateCharArray[i] >= '0' && enteredDateCharArray[i] <= '9')) {
                    return false;
                }
            }

            if (enteredDateCharArray[4] != enteredDateCharArray[7]) {
                return false;
            }

            else {
                return (enteredDateCharArray[4] == '-' || enteredDateCharArray[4] == '/');
            }
        }
    }


    //   این متد برای بررسی درستی زمان وارد شده می باشد.
    public boolean isEnteredTimeRight (String enteredTime) {
        if (enteredTime.length() != 5) {
            return false;
        }

        else {
            char[] enteredTimeToCharArray = enteredTime.toCharArray();
            for (int i = 0; i < 5; i++) {
                if (i == 2) {
                    continue;
                }
                if (!(enteredTimeToCharArray[i] >= '0' && enteredTimeToCharArray[i] <= '9')) {
                    return false;
                }
            }

            return (enteredTimeToCharArray[2] == ':');
        }
    }
}

