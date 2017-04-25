package com.information.get;

import java.io.File;

import com.information.model.BasicInformation;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class GetBasicInformation implements Get {
	@Override
	public BasicInformation execute() {
		BasicInformation basicInformation = new BasicInformation();
		SystemInfo systemInfo = new SystemInfo();
		HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		
		File[] roots = File.listRoots();

		basicInformation.setTotalDisk(String.valueOf(String.format("%.2f", roots[0].getTotalSpace() / Math.pow(1024, 3))));
		basicInformation.setUsedDisk(String.valueOf(String.format("%.2f", roots[0].getUsableSpace() / Math.pow(1024, 3))));
		basicInformation.setFreeDisk(String.valueOf(String.format("%.2f", (roots[0].getTotalSpace() / Math.pow(1024, 3)) - (roots[0].getUsableSpace() / Math.pow(1024, 3)))));
		
		basicInformation.setTotalMemory(String.valueOf(String.format("%.2f", (hardwareAbstractionLayer.getMemory().getTotal() * 0.000000001))));
		basicInformation.setFreeMemory(String.valueOf(String.format("%.2f", (hardwareAbstractionLayer.getMemory().getAvailable() * 0.000000001))));
		basicInformation.setUsedMemory(String.valueOf(String.format("%.2f", (hardwareAbstractionLayer.getMemory().getTotal() * 0.000000001) - (hardwareAbstractionLayer.getMemory().getAvailable() * 0.000000001))));
		
		return basicInformation;
	}
}
