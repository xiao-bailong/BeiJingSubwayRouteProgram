package test;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<Station> stations;
/*    public Line (String name){
        this.name = name;
    }*/
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    public List<String> loadAllLineName(){//罗列所有线路名字
        List<String> list=new ArrayList<>();
        for(int i=0;i<DataBuilder.AllLine.size();i++){
            list.add(DataBuilder.AllLine.get(i).getName());
        }
        return list;
    }
}
