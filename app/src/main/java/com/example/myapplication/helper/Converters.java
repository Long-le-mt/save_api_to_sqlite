package com.example.myapplication.helper;

import androidx.room.TypeConverter;

import com.example.myapplication.model.Unit;

public class Converters {
    @TypeConverter
    public static String fromUnitToString(Unit unit){
        return unit == null ? "" : unit.getMetric() + "," + unit.getImperial();
    }
    @TypeConverter
    public static Unit fromStringToUnit(String unitString){
        if(unitString == null){
            return null;
        }
        String[] splitedByMeasureSystem = unitString.split(",");
        String imperial = splitedByMeasureSystem[0];
        String metric = splitedByMeasureSystem[1];
        return new Unit(imperial, metric);
    }

}
