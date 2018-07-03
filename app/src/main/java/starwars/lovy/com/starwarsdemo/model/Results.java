package starwars.lovy.com.starwarsdemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import starwars.lovy.com.starwarsdemo.BR;

/**
 * Model class
 */

public class Results extends BaseObservable{
    @SerializedName("films")
    private List<String> films;
    @SerializedName("homeworld")
    private String homeworld;
    @SerializedName("gender")
    private String gender;
    @SerializedName("skin_color")
    private String skin_color;
    @SerializedName("edited")
    private String edited;
    @SerializedName("created")
    private String created;
    @SerializedName("mass")
    private String mass;
    @SerializedName("vehicles")
    private List<String> vehicles;
    @SerializedName("url")
    private String url;
    @SerializedName("hair_color")
    private String hair_color;
    @SerializedName("birth_year")
    private String birth_year;
    @SerializedName("eye_color")
    private String eye_color;
    @SerializedName("species")
    private List<String> species;
    @SerializedName("starships")
    private List<String> starships;
    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private String height;
    public void setFilms( List<String> films){
        this.films=films;
    }
    public  List<String> getFilms(){
        return films;
    }
    public void setHomeworld(String homeworld){
        this.homeworld=homeworld;
    }
    public String getHomeworld(){
        return homeworld;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return gender;
    }
    public void setSkin_color(String skin_color){
        this.skin_color=skin_color;
    }
    public String getSkin_color(){
        return skin_color;
    }
    public void setEdited(String edited){
        this.edited=edited;
    }
    public String getEdited(){
        return edited;
    }
    public void setCreated(String created){
        this.created=created;
    }
    public String getCreated(){
        return created;
    }
    public void setMass(String mass){
        this.mass=mass;
    }
    public String getMass(){
        return mass;
    }
    public void setVehicles( List<String> vehicles){
        this.vehicles=vehicles;
    }
    public  List<String> getVehicles(){
        return vehicles;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public String getUrl(){
        return url;
    }
    public void setHair_color(String hair_color){
        this.hair_color=hair_color;
    }
    public String getHair_color(){
        return hair_color;
    }
    public void setBirth_year(String birth_year){
        this.birth_year=birth_year;
    }
    public String getBirth_year(){
        return birth_year;
    }
    public void setEye_color(String eye_color){
        this.eye_color=eye_color;
    }
    public String getEye_color(){
        return eye_color;
    }
    public void setSpecies( List<String> species){
        this.species=species;
    }
    public  List<String> getSpecies(){
        return species;
    }
    public void setStarships( List<String> starships){
        this.starships=starships;
    }
    public  List<String> getStarships(){
        return starships;
    }
    public void setName(String name){
        this.name=name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getName(){
        return name;
    }
    public void setHeight(String height){
        this.height=height;
    }
    public String getHeight(){
        return height;
    }

}
