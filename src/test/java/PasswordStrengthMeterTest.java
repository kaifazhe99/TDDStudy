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

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength (String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr,result);
    }

    // 모든 규칙을 충족하는 경우
    @Test
    void meetsAllCriteria_Then_Strong(){
        assertStrength("ab12!@AB",PasswordStrength.STRONG);
        assertStrength("abc1!Add",PasswordStrength.STRONG);
    }



    // 한가지 조건제외 충족하는 경우들

    // 길이만 8글자 미만이고 나머지 조건은 충족하는 경우
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal(){
        assertStrength("ab12!@A",PasswordStrength.NORMAL);
    }

    // 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        assertStrength("ab!@ABqwer",PasswordStrength.NORMAL);
    }

    // 대문자를 포함하지 않고 나머지 조건을 충족하는 경우
    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("aab12!@df",PasswordStrength.NORMAL);
    }



    //값이 없는 경우

    // null
    @Test
    void nullInput_Then_Invalid(){
        assertStrength(null,PasswordStrength.INVALID);
    }

    // 빈 문자열
    @Test
    void emptyInput_Then_Invalid(){
        assertStrength("",PasswordStrength.INVALID);
    }

}
