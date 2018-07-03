package starwars.lovy.com.starwarsdemo.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Model class for get characters
 */

public class CharacterResponse{
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("count")
    private int count;
    @SerializedName("results")
    private List<Results> results;
    public void setNext(String next){
        this.next=next;
    }
    public String getNext(){
        return next;
    }
    public void setPrevious(String previous){
        this.previous=previous;
    }
    public String getPrevious(){
        return previous;
    }
    public void setCount(int count){
        this.count=count;
    }
    public int getCount(){
        return count;
    }
    public void setResults(List<Results> results){
        this.results=results;
    }
    public List<Results> getResults(){
        return results;
    }
}
