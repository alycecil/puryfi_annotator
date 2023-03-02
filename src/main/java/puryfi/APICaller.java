package puryfi;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class APICaller {
    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public NSWF_Image makeApiCall(File file, File outputFile) throws IOException {
        HttpResponse returnResponse;
        try {
            returnResponse = innerMakeRequest(outputFile);
        } catch (SocketTimeoutException var43) {
            return null;
        }


        try {
            return this.parser(file, EntityUtils.toString(returnResponse.getEntity()));
        } catch (JSONException var42) {
            System.out.println(EntityUtils.toString(returnResponse.getEntity()));
            LOGGER.error("Error Occured", var42);
            return null;
        }
    }

    private HttpResponse innerMakeRequest(File outputFile) throws IOException {
        String url = "http://pury.fi/detect";
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("file", new FileBody(outputFile));

        return Request.Post(url).body(entity).socketTimeout(30000).execute().returnResponse();
    }

    public NSWF_Image parser(File file, String json) {
        JSONObject obj = new JSONObject(json);
        obj.put("file", file.getAbsolutePath());
        Double score = obj.getJSONObject("output").getDouble("nsfw_score");
        JSONArray arr = obj.getJSONObject("output").getJSONArray("detections");
        NSWF_Image result = new NSWF_Image(file, score, obj);

        for(int i = 0; i < arr.length(); ++i) {
            String name = arr.getJSONObject(i).getString("name");
            Double confidence = arr.getJSONObject(i).getDouble("confidence");
            JSONArray array = arr.getJSONObject(i).optJSONArray("bounding_box");
            int[] nums = new int[array.length()];

            for(int j = 0; j < array.length(); ++j) {
                nums[j] = array.optInt(j);
            }

            result.getResults().add(new NSFW_BoundingBox(name, confidence, nums));
        }

        return result;
    }
}
