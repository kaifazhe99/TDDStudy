import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    // 검사할 규칙은 다음 세 가지이다.
    // 길이가 8글자 이상
    // 0부터 9 사이의 숫자를 포함
    // 대문자 포함
    // 세 규칙을 모두 충족하면 암호는 강함이다.
    // 2개의 규칙을 충족하면 암호는 보통이다.
    // 1개 이하의 규칙을 충족하면 암호는 약함이다.

    // 모든 규칙을 충족하는 경우
    @Test
    void meetsAllCriteria_Then_Strong(){
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG,result);
        PasswordStrength result2 =meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG,result2);
    }


    // 길이만 8글자 미만이고 나머지 조건은 충족하는 경우
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal(){
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL,result);
    }

    // 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab!@ABqwer");
        assertEquals(PasswordStrength.NORMAL,result);
    }

}
