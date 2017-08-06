package gnomenwald;
/**
 * Created by dorothyquincy on 06/08/2017.
 */

import gnomenwald.RoadsAndVillagesSuper.Village;
import gnomenwald.RoadsAndVillagesSuper.Road;
import java.util.ArrayList;

public class MinSpan {
    public static ArrayList<Road> ConvertRoad(ArrayList<Village> Village){
        ArrayList<Road> Path = new ArrayList<Road>();
        for(Village V: Village)
        {
            //for villages in village list, add their previous roads to new list
            Path.add(V.getRoadPrev());
        }
        return Path;
    }

    public static Village ClosestVillage(ArrayList<Village> EvaluatedVillage){
        // minimum distance in evaluated villages
        Village closest = EvaldVillage.get(0);
        for(Village V: EvaldVillage){
            if(closest.getDistFrom() > V.getDistFrom()){
                closest = V;
            }
        }
        return closest;
    }
    public static ArrayList<Road> MinSpan(Village V){//constrcutor

        ArrayList<Village> EvaldVillages = new ArrayList<Village>();
        ArrayList<Village> ReevaluatedVillages = new ArrayList<Village>();
        for (Road Roads:V.getROutConnections()){
            Roads.getTarget().setDistFrom(Roads.getLength());
            Roads.getTarget().setRoadPrev(Roads);
            EvalddVillages.add(Roads.getTarget());
        }
        while (EvaldVillages.size()!= 0){
            Village closest = ClosestVillage(EvaldVillages);
            EvaldVillages.remove(closest);
            for (Road Roads:closest.getROutConnections()){
                if(!EvaldVillages.contains(Roads.getTarget())){
                    Roads.getTarget().setRoadPrev(Roads);
                    Roads.getTarget().setDistFrom(closest.getDistFrom() + Roads.getLength());
                    EvaldVillages.add(Roads.getTarget());
                    ReevaluatedVillages.add(closest);
                }
                else {
                    if(Roads.getTarget().getDistFrom() > (closest.getDistFrom() + Roads.getLength())){
                        //if length of this path is shorter, change, otherwise, don't
                        Roads.getTarget().setDistFrom(closest.getDistFrom() + Roads.getLength());
                        Roads.getTarget().setRoadPrev(Roads);
                    }
                }
            }

        }
        return ConvertRoad(ReevaluatedVillages);
    }
}
