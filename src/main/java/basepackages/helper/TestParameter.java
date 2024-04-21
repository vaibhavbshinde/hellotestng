package basepackages.helper;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class TestParameter {

public List<Map<String,String>> convertDataTableToParameter(List<Map<String,String>> testParam,
                                                            String payLoadType, String altValue){
List<Map<String,String>> allParam = new ArrayList<>();
for (Map<String,String> items:testParam){
    allParam.add(new HashMap<>(){{
        put("name",items.get("name"));
        put("value",items.get("value"));
     }});
}
return modifyParameter(allParam,payLoadType,altValue);
}

public List<Map<String, String>> modifyParameter(List<Map<String,String>> jsonArray,
                                                 String payLoadType,String altValue){

    if(payLoadType.contains(",")){
        for(String str: payLoadType.split(",")){
            jsonArray = updateParam(jsonArray,str,altValue);
        }
    }else{
        jsonArray=updateParam(jsonArray,payLoadType,altValue);
    }
return jsonArray;
}

public List<Map<String, String>> updateParam(List<Map<String,String>> jsonArray,
                                                     String payLoadType,String altValue) {
    if (payLoadType.startsWith("no_")) {
        for (Map<String, String> item : jsonArray) {
            if (item.get("name").equals(payLoadType.replace("no_", ""))) {
                item.put("value", "");
            }
        }
    } else if (payLoadType.startsWith("alt_")) {
        for (Map<String, String> item : jsonArray) {
            if (item.get("name").equals(payLoadType.replace("alt_", ""))) {
                item.put("value", altValue);
            }
        }
    }
    return jsonArray;
   }

public String getParamByKey(List<Map<String,String>> testParam,String key){
    String str="";
    for (Map<String, String> item : testParam) {
        if (item.get("name").equals(key)){
            str=item.get("value");
        }
    }
   return str;
}


    private  Map<String, String> convertMapToJSONObject(List<Map<String,String>> inputParam,
                                                   String payLoadType,String altValue) {

    Map<String,String> allParam = new HashMap<>();
    for (Map<String, String> item : inputParam) {
         allParam.put(item.get("key"),item.get("value"));
            }


        if(payLoadType.startsWith("no_")){
            for (String key: allParam.keySet()) {
                if(key.equals(payLoadType.replace("no_",""))){
                    log.info("payload type {}",payLoadType);
                    allParam.put(key,"");
                }
            }

        } else if (payLoadType.startsWith("alt_")){
            for (String key: allParam.keySet()) {
                if(key.equals(payLoadType.replace("alt_",""))){
                    log.info("payload type {}",payLoadType);
                    allParam.put(key,altValue);
                }
            }
        }
        return allParam;
    }


}



