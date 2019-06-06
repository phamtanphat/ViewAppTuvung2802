package khoapham.ptp.phamtanphat.apphoctienganh2802;

import java.util.ArrayList;
import java.util.List;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Word;

public interface ListenData {
    void onSuccess(List<Word> words);
    void onFailt(String error);
}
