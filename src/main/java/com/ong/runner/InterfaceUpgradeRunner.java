package com.ong.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ong.interfaceupgrade.InterfaceUpgradeHolder;
import com.ong.xml.XMLHelper;

@Component
@Order(value=1)
public class InterfaceUpgradeRunner  implements CommandLineRunner{
	
	private static Logger logger = LoggerFactory.getLogger(InterfaceUpgradeRunner.class);
	
	public void run(String... args) throws Exception {
		logger.info("InterfaceUpgradeRunner start");
		InterfaceUpgradeHolder.setInterfaceMappers(XMLHelper.parseXMLMapper());
		logger.info("InterfaceUpgradeRunner finished");
	}

}
