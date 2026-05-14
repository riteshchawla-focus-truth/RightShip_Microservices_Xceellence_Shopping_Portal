package service.registry.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UltraVelocityGeneralUniversalEntity extends UltraVelocityBasicCommonEntity {

    private UltraVelocityStringEntity stringEntity;

    private UltraVelocityDateEntity dateEntity;

    private UltraVelocityDoubleEntity doubleEntity;

    private UltraVelocityLongEntity longEntity;

    private UltraVelocityObjectEntity objectEntity;

    public void initializeStringEntity(String dataString,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        stringEntity = new UltraVelocityStringEntity();
        stringEntity.setStringData(dataString);
        Supplier<Set<String>> factorySet = initSet ? TreeSet::new : () -> null;
        stringEntity.setStringDataSet(factorySet.get());
        Supplier<List<String>> factoryList = initList ? ArrayList::new : () -> null;
        stringEntity.setStringDataList(factoryList.get());
        Supplier<Map<String, String>> factoryMap = initMap ? TreeMap::new : () -> null;
        stringEntity.setStringDataMap(factoryMap.get());
    }

    public void initializeDateEntity(Date dataDate,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        dateEntity = new UltraVelocityDateEntity();
        dateEntity.setDateData(dataDate);
        Supplier<Set<Date>> factorySet = initSet ? TreeSet::new : () -> null;
        dateEntity.setDateDataSet(factorySet.get());
        Supplier<List<Date>> factoryList = initList ? ArrayList::new : () -> null;
        dateEntity.setDateDataList(factoryList.get());
        Supplier<Map<String, Date>> factoryMap = initMap ? TreeMap::new : () -> null;
        dateEntity.setDateDataMap(factoryMap.get());
    }

    public void initializeObjectEntity(Object dataObject,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        objectEntity = new UltraVelocityObjectEntity();
        objectEntity.setObjectData(dataObject);
        Supplier<Set<Object>> factorySet = initSet ? TreeSet::new : () -> null;
        objectEntity.setObjectDataSet(factorySet.get());
        Supplier<List<Object>> factoryList = initList ? ArrayList::new : () -> null;
        objectEntity.setObjectDataList(factoryList.get());
        Supplier<Map<String, Object>> factoryMap = initMap ? TreeMap::new : () -> null;
        objectEntity.setObjectDataMap(factoryMap.get());
    }

    public void initializeDoubleEntity(Double dataDouble,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        doubleEntity = new UltraVelocityDoubleEntity();
        doubleEntity.setDoubleData(dataDouble);
        Supplier<Set<Double>> factorySet = initSet ? TreeSet::new : () -> null;
        doubleEntity.setDoubleDataSet(factorySet.get());
        Supplier<List<Double>> factoryList = initList ? ArrayList::new : () -> null;
        doubleEntity.setDoubleDataList(factoryList.get());
        Supplier<Map<String, Double>> factoryMap = initMap ? TreeMap::new : () -> null;
        doubleEntity.setDoubleDataMap(factoryMap.get());
    }

    public void initializeLongEntity(Long dataLong,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        longEntity = new UltraVelocityLongEntity();
        longEntity.setLongData(dataLong);
        Supplier<Set<Long>> factorySet = initSet ? TreeSet::new : () -> null;
        longEntity.setLongDataSet(factorySet.get());
        Supplier<List<Long>> factoryList = initList ? ArrayList::new : () -> null;
        longEntity.setLongDataList(factoryList.get());
        Supplier<Map<String, Long>> factoryMap = initMap ? TreeMap::new : () -> null;
        longEntity.setLongDataMap(factoryMap.get());
    }
}
