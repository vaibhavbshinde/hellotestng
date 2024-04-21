package basepackages.helper;

import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.List;

@Slf4j
public class JSONDataModifier {

    public String getRequestParams(String payLoadType, String altValue, JSONObject testData){
        JSONObject requestParams = modifyJsonByKey(payLoadType,altValue,testData);
        log.info("Request param after processing by modifier : {}",requestParams.toString());
        return requestParams.toString();

    }

    private JSONObject modifyJsonByKey(String payLoadType, String altValue,JSONObject requestParam){
        if(payLoadType.contains(",")){
            for (String str : payLoadType.split(",")){
                requestParam = updateJson(str,altValue,requestParam);
            }
        }else {
             requestParam = updateJson(payLoadType,altValue,requestParam);
        }
        return requestParam;
    }

    private JSONObject updateJson(String payLoadType, String altValue, JSONObject requestParam){
        if(payLoadType.startsWith("no_")){
            requestParam.put(payLoadType.replace("no_",""),"");
        } else if (payLoadType.startsWith("del_")){
            requestParam.put(payLoadType.replace("del_",""),"");
        } else if (payLoadType.startsWith("alt_")){
            requestParam.put(payLoadType.replace("alt_",""),"");
        }
        return requestParam;
    }

    public JSONObject convertDataTableToJsonObject(DataTable testData){
        JSONObject requestParams = new JSONObject();
        List<List<String>> data = testData.asLists(String.class);

        for(int i =0 ; i< data.size();i++){
            requestParams.put(data.get(i).get(0),data.get(i).get(1));
        }
        return requestParams;
    }

}
