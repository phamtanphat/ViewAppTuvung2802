package khoapham.ptp.phamtanphat.apphoctienganh2802.API.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tuvung {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("words")
    @Expose
    private List<Word> words = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

}