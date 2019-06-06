package khoapham.ptp.phamtanphat.apphoctienganh2802.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {

@SerializedName("_id")
@Expose
private String id;
@SerializedName("en")
@Expose
private String en;
@SerializedName("vn")
@Expose
private String vn;

@SerializedName("isMemorized")
@Expose
private Boolean isMemorized;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getEn() {
return en;
}

public void setEn(String en) {
this.en = en;
}

public String getVn() {
return vn;
}

public void setVn(String vn) {
this.vn = vn;
}



public Boolean getIsMemorized() {
return isMemorized;
}

public void setIsMemorized(Boolean isMemorized) {
this.isMemorized = isMemorized;
}

}