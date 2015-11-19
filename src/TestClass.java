/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sampan IT
 */
//import java.io.IOException;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class TestClass {

    private long id;
    private String name;
    private String notInterstingMember;
    private long anotherMember;

//    @JsonIgnore
    private int forgetThisField;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @JsonIgnore
    public String getNotInterstingMember() {
        return this.notInterstingMember;
    }

    public long getAnotherMember() {
        return anotherMember;
    }

    public void setAnotherMember(long anotherMember) {
        this.anotherMember = anotherMember;
    }

   

    public void setAnotherMember(int anotherMember) {
        this.anotherMember = anotherMember;
    }

//    @JsonProperty
    public int getForgetThisField() {
        return this.forgetThisField;
    }

//    @JsonIgnore
    public void setForgetThisField(int forgetThisField) {
        this.forgetThisField = forgetThisField;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }

//    @Override
//    public String toString() {
//        return "MyTestClass [" + this.id + " , " + this.name + ", " + this.notInterstingMember + ", " + this.anotherMember + ", " + this.forgetThisField + "]";
//    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TestClass obj = new TestClass();
        obj.setId(1447912570);
        obj.setName("faltu project");
//        obj.toString();
        System.out.println(obj.toString());
//        String s = mapper.writeValueAsString(obj);
//        System.out.println(s);
    }
}

//
//public class JSONIgnorePropTest {
//
//    public static void main(String[] args) {
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        MyTestClass mtc = new MyTestClass();
//        mtc.setId(1);
//        mtc.setName("Test program");
//        mtc.setNotInterstingMember("Don't care about this");
//        mtc.setAnotherMember(100);
//        mtc.setForgetThisField(999);
//
//        String s = null;
//        try {
//            s = mapper.writeValueAsString(mtc);
//        } catch (JsonProcessingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        System.out.println(s);
//
//        MyTestClass mtc2 = null;
//        try {
//            mtc2 = mapper.readValue(s, MyTestClass.class);
//        } catch (JsonParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        System.out.println(mtc2.toString());
//
//    }
//}

//}
