import javax.swing.*;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s){

        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        boolean lenghthEnough =s.length() >= 8;
        boolean containsNum = meetsContainingNumberCriteria(s);
        boolean containsUpp = meetsContainingUppercaseCriteria(s);

        if(lenghthEnough && !containsNum && !containsUpp) return PasswordStrength.WEAK;
        if(!lenghthEnough) return PasswordStrength.NORMAL;
        if(!containsNum) return PasswordStrength.NORMAL;
        if(!containsUpp) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }
    private boolean meetsContainingNumberCriteria(String s){
        for (char ch : s.toCharArray()){
            if(ch >= '0' && ch <='9'){
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s){
        for(char ch : s.toCharArray()){
            if(Character.isUpperCase(ch)){
                return true;
            }
        }
        return false;
    }


}
