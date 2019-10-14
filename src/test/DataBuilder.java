package test;


import java.io.*;
import java.util.*;


public class DataBuilder /*throws IOException*/{

    public static Set<List<Station>> lineSet = new HashSet<List<Station>>();//所有线集合
    public static List<Line> AllLine = new ArrayList<Line>();//所有线集合
    public static Map<String, List<Station>> map = new HashMap<String, List<Station>>();
    public static List<Station> mapOfStation = new ArrayList<Station>();//存放所有站点
    public static int totalStaion = 0;//总的站点数量

    public static void setStations(List<Station> linemodel, String str) {
        String line=str;
        String[] line1Arr = line.split(" ");
        int flag=0;
        String linename = null;
        Line newline=new Line();
        for(String s : line1Arr){
            if(flag==0){
                linename=s;
                newline.setName(linename);
            }
            else{
                Station station=new Station(s);
                for(List<Station> lineP : DataBuilder.lineSet){
                    if(lineP.contains(station)){
                        DataBuilder.totalStaion -=1;//在其他线路出现过就减1站，不然会重复计算
                        break;
                    }
                }
                mapOfStation.add(station);
                station.setLinename(linename);
                linemodel.add(station);
            }
            flag=1;
        }
        for(int i =0;i<linemodel.size();i++){
            if(i<linemodel.size()-1){
                linemodel.get(i).next = linemodel.get(i+1);
                linemodel.get(i+1).prev = linemodel.get(i);
            }
        }
        newline.setStations(linemodel);
        AllLine.add(newline);
        map.put(linename,linemodel);
    }
    static {
        for(int i=0;i<Subway.lines.size();i++){
            List<Station> line = new ArrayList<Station>();
            setStations(line,Subway.lines.get(i));
            totalStaion += line.size();
            lineSet.add(line);
        }
    }
}

