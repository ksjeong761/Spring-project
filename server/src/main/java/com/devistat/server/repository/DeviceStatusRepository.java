package com.devistat.server.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatus;

@Repository
public class DeviceStatusRepository extends AbstractRepository<DeviceStatus>{
    private final Logger logger = LoggerFactory.getLogger(DeviceStatusRepository.class);
    
	public DeviceStatusRepository(){
		setClazz(DeviceStatus.class);
	}
	
    public List<DeviceStatus> findByPeriod(LocalDateTime start, LocalDateTime end){
    	logger.info("start : " + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    	logger.info("end : " + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    	Query query = entityManager.createQuery(
    			"SELECT ds "
				+ "FROM DeviceStatus ds "
				+ "WHERE "
					+ "ds.loggedTime > :start "
				+ "AND "
					+ "ds.loggedTime <= :end");

  	  query.setParameter("start", start);
  	  query.setParameter("end", end);
  	  
  	  List<DeviceStatus> result = query.getResultList();

  	  logger.info("result size : " + result.size());

	  return result;
    }
}

//Hibernate: 
//    select
//        devicestat0_.statusCpuCode as statuscp1_2_5_,
//        devicestat0_.contextSwitchCount as contexts2_2_5_,
//        devicestat0_.deviceStatusCode as devicest8_2_5_,
//        devicestat0_.interruptCount as interrup3_2_5_,
//        devicestat0_.systemCallCount as systemca4_2_5_,
//        devicestat0_.timePercentIdle as timeperc5_2_5_,
//        devicestat0_.timePercentSystem as timeperc6_2_5_,
//        devicestat0_.timePercentUser as timeperc7_2_5_,
//        devicestat1_.deviceStatusCode as devicest1_1_0_,
//        devicestat1_.deviceCode as deviceco3_1_0_,
//        devicestat1_.loggedTime as loggedti2_1_0_,
//        device2_.deviceCode as deviceco1_0_1_,
//        device2_.deviceName as devicena2_0_1_,
//        devicestat3_.statusDiskCode as statusdi1_3_2_,
//        devicestat3_.deviceStatusCode as devicest8_3_2_,
//        devicestat3_.readBytes as readbyte2_3_2_,
//        devicestat3_.readCount as readcoun3_3_2_,
//        devicestat3_.readTime as readtime4_3_2_,
//        devicestat3_.writeBytes as writebyt5_3_2_,
//        devicestat3_.writeCount as writecou6_3_2_,
//        devicestat3_.writeTime as writetim7_3_2_,
//        devicestat4_.statusMemoryCode as statusme1_4_3_,
//        devicestat4_.capacityAvailable as capacity2_4_3_,
//        devicestat4_.capacityTotal as capacity3_4_3_,
//        devicestat4_.deviceStatusCode as devicest4_4_3_,
//        devicestat5_.statusNetworkCode as statusne1_5_4_,
//        devicestat5_.deviceStatusCode as devices10_5_4_,
//        devicestat5_.dropInCount as dropinco2_5_4_,
//        devicestat5_.dropOutCount as dropoutc3_5_4_,
//        devicestat5_.erorrOutCount as erorrout4_5_4_,
//        devicestat5_.errorInCount as errorinc5_5_4_,
//        devicestat5_.receivedBytes as received6_5_4_,
//        devicestat5_.receivedPacketCount as received7_5_4_,
//        devicestat5_.sentBytes as sentbyte8_5_4_,
//        devicestat5_.sentPacketCount as sentpack9_5_4_ 
//    from
//        DeviceStatusCpu devicestat0_ 
//    left outer join
//        DeviceStatus devicestat1_ 
//            on devicestat0_.deviceStatusCode=devicestat1_.deviceStatusCode 
//    left outer join
//        Device device2_ 
//            on devicestat1_.deviceCode=device2_.deviceCode 
//    left outer join
//        DeviceStatusDisk devicestat3_ 
//            on devicestat1_.deviceStatusCode=devicestat3_.deviceStatusCode 
//    left outer join
//        DeviceStatusMemory devicestat4_ 
//            on devicestat1_.deviceStatusCode=devicestat4_.deviceStatusCode 
//    left outer join
//        DeviceStatusNetwork devicestat5_ 
//            on devicestat1_.deviceStatusCode=devicestat5_.deviceStatusCode 
//    where
//        devicestat0_.deviceStatusCode=?

// 이런 식으로 들어가도록 하는게 목표
//where
//  devicestat0_.deviceStatusCode=?
// and 
//   devicestat0_.loggedTime > ?
// and 
//   devicestat0_.loggedTime <= ?

//https://stackoverflow.com/questions/42303746/jpql-for-one-to-many-relationship/42305536#42305536
//예제
//"SELECT di FROM Device di JOIN di.user u"
//+ "WHERE"
//+ 	"u.id=:userId"
//+ "and"
//+ 	"di.deviceName=:deviceName");