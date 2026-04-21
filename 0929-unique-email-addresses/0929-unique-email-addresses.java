import java.util.*;

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        
        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];
            
            StringBuilder sb = new StringBuilder();
            
            for (char c : local.toCharArray()) {
                if (c == '+') break;
                if (c != '.') sb.append(c);
            }
            
            set.add(sb.toString() + "@" + domain);
        }
        
        return set.size();
    }
}