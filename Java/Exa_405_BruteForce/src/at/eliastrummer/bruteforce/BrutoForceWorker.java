package at.eliastrummer.bruteforce;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.xml.bind.DatatypeConverter;

public class BrutoForceWorker implements Callable<ResultHolder>{

    private Person person;

    public BrutoForceWorker(Person person) {
        this.person = person;
    }
    
    @Override
    public ResultHolder call() throws Exception {
        List<Character> characters = new ArrayList<>();
        
        for(int i = (int)'a'; i <= (int)'z'; i++){
            characters.add((char) i);
        }
        
        for(int i = (int)'0'; i <= (int)'9'; i++){
            characters.add((char) i);
        }
        
        for(int i = 0; i < characters.size(); i++){
            for(int i2 = 0; i2 < characters.size(); i2++){
                for(int i3 = 0; i3 < characters.size(); i3++){
                    for(int i4 = 0; i4 < characters.size(); i4++){
                        for(int i5 = 0; i5 < characters.size(); i5++){
                            String word = "" + characters.get(i) + characters.get(i2) + characters.get(i3) + characters.get(i4) + characters.get(i5);
                            String hash = hash(word);
                            
                            if(word.equals("9j0g3")){
                                System.out.println("-> " + hash);
                            }
                            
                            if(hash.equals(person.getHash())){
                                return new ResultHolder(person, word);
                            }
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    private String hash(String param){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(param.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.toString());
        }
        
        return "";
    }
}
