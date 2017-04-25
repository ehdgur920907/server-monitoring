package com.information.get;

import java.io.File;

import com.information.model.BasicInformation;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class GetBasicInformation implements Get {
	@Override
	public BasicInformation execute() {
		BasicInformation basicInformation = new BasicInformation();
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		
		File[] roots = File.listRoots();

		for (File root : roots) {
			// drive = root.getAbsolutePath();
			basicInformation.setTotalDisk(String.valueOf(String.format("%.2f", root.getTotalSpace() / Math.pow(1024, 3))));
			basicInformation.setUsedDisk(String.valueOf(String.format("%.2f", root.getUsableSpace() / Math.pow(1024, 3))));
			basicInformation.setFreeDisk(String.valueOf(String.format("%.2f", (root.getTotalSpace() / Math.pow(1024, 3)) - (root.getUsableSpace() / Math.pow(1024, 3)))));
		}
		
		basicInformation.setTotalMemory(String.valueOf(String.format("%.2f", (hal.getMemory().getTotal() * 0.00000001))));
		basicInformation.setUsedMemory(String.valueOf(String.format("%.2f", (hal.getMemory().getAvailable() * 0.00000001))));
		basicInformation.setFreeMemory(String.valueOf(String.format("%.2f", (hal.getMemory().getTotal() * 0.00000001) - (hal.getMemory().getAvailable() * 0.00000001))));
	
		return basicInformation;
	}
}