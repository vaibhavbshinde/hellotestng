package basepackages.helper;

import com.google.common.io.CharStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.util.Charsets;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

@Slf4j
public class JSONDataReader {

    private JSONObject jsonObject;
    private InputStream is = null;

    public  JSONDataReader searchFile(String fileName){
        String actualFileName = "./src/test/resources/testpayload/" +fileName+".json";
        is =null;
        try {
            is = new FileInputStream(actualFileName);
            log.info("Test payload file is available : {}",actualFileName);
        } catch (FileNotFoundException e) {
            //log.info("File not found!!");
            System.out.print("File not found !!!");
            e.printStackTrace();
        }
       return this;
    }

    public JSONDataReader fileToJson(){
        String jsonTxt = null;
        try{
            jsonTxt = CharStreams.toString(new InputStreamReader(is,Charsets.UTF_8));
        } catch (IOException e){
            System.out.println("Exception occurred while reading file !!");
            e.printStackTrace();
        }
        stringToJson(jsonTxt);
        return this;
    }

    public JSONObject getJson(){
        return jsonObject;
    }

    public JSONDataReader stringToJson(String str){
        jsonObject = new JSONObject(str){

            @Override
            public JSONObject put(String key,Object value) throws JSONException{
                try{
                    Field map = JSONObject.class.getDeclaredField("map");
                    map.setAccessible(true);
                    Object mapValue = map.get(this);

                    if(!(mapValue instanceof LinkedHashMap)){
                        map.set(this, new LinkedHashMap<>());
                    }

                }catch(NoSuchFieldException | IllegalAccessException e){
                    throw new RuntimeException(e);
                }
                return super.put(key,value);

            }
        };
        return this;
    }
}
