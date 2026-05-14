package user.microservice.dtos;

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
public class UltraVelocityGeneralUniversalDTO extends UltraVelocityBasicCommonDTO {

    private UltraVelocityStringDTO stringDTO;

    private UltraVelocityDateDTO dateDTO;

    private UltraVelocityDoubleDTO doubleDTO;

    private UltraVelocityLongDTO longDTO;

    private UltraVelocityObjectDTO objectDTO;

    public void initializeStringDTO(String dataString,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        stringDTO = new UltraVelocityStringDTO();
        stringDTO.setStringData(dataString);
        Supplier<Set<String>> factorySet = initSet ? TreeSet::new : () -> null;
        stringDTO.setStringDataSet(factorySet.get());
        Supplier<List<String>> factoryList = initList ? ArrayList::new : () -> null;
        stringDTO.setStringDataList(factoryList.get());
        Supplier<Map<String, String>> factoryMap = initMap ? TreeMap::new : () -> null;
        stringDTO.setStringDataMap(factoryMap.get());
    }

    public void initializeDateDTO(Date dataDate,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        dateDTO = new UltraVelocityDateDTO();
        dateDTO.setDateData(dataDate);
        Supplier<Set<Date>> factorySet = initSet ? TreeSet::new : () -> null;
        dateDTO.setDateDataSet(factorySet.get());
        Supplier<List<Date>> factoryList = initList ? ArrayList::new : () -> null;
        dateDTO.setDateDataList(factoryList.get());
        Supplier<Map<String, Date>> factoryMap = initMap ? TreeMap::new : () -> null;
        dateDTO.setDateDataMap(factoryMap.get());
    }

    public void initializeObjectDTO(Object dataObject,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        objectDTO = new UltraVelocityObjectDTO();
        objectDTO.setObjectData(dataObject);
        Supplier<Set<Object>> factorySet = initSet ? TreeSet::new : () -> null;
        objectDTO.setObjectDataSet(factorySet.get());
        Supplier<List<Object>> factoryList = initList ? ArrayList::new : () -> null;
        objectDTO.setObjectDataList(factoryList.get());
        Supplier<Map<String, Object>> factoryMap = initMap ? TreeMap::new : () -> null;
        objectDTO.setObjectDataMap(factoryMap.get());
    }

    public void initializeDoubleDTO(Double dataDouble,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        doubleDTO = new UltraVelocityDoubleDTO();
        doubleDTO.setDoubleData(dataDouble);
        Supplier<Set<Double>> factorySet = initSet ? TreeSet::new : () -> null;
        doubleDTO.setDoubleDataSet(factorySet.get());
        Supplier<List<Double>> factoryList = initList ? ArrayList::new : () -> null;
        doubleDTO.setDoubleDataList(factoryList.get());
        Supplier<Map<String, Double>> factoryMap = initMap ? TreeMap::new : () -> null;
        doubleDTO.setDoubleDataMap(factoryMap.get());
    }

    public void initializeLongDTO(Long dataLong,
                                    boolean initSet,
                                    boolean initList,
                                    boolean initMap) {

        longDTO = new UltraVelocityLongDTO();
        longDTO.setLongData(dataLong);
        Supplier<Set<Long>> factorySet = initSet ? TreeSet::new : () -> null;
        longDTO.setLongDataSet(factorySet.get());
        Supplier<List<Long>> factoryList = initList ? ArrayList::new : () -> null;
        longDTO.setLongDataList(factoryList.get());
        Supplier<Map<String, Long>> factoryMap = initMap ? TreeMap::new : () -> null;
        longDTO.setLongDataMap(factoryMap.get());
    }
}
