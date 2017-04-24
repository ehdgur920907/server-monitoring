package com.information.get;

import java.io.File;
import java.text.DecimalFormat;

import com.information.model.Disk;

public class GetDisk implements Get {
	@Override
	public Disk execute() {
		// String drive;
		Disk disk = new Disk();
		File[] roots = File.listRoots();

		for (File root : roots) {
			// drive = root.getAbsolutePath();
			disk.setTotalDisk(String.valueOf(String.format("%.2f", root.getTotalSpace() / Math.pow(1024, 3))));
			disk.setUsedDisk(String.valueOf(String.format("%.2f", root.getUsableSpace() / Math.pow(1024, 3))));
			disk.setFreeDisk(String.valueOf(String.format("%.2f", (root.getTotalSpace() / Math.pow(1024, 3)) - (root.getUsableSpace() / Math.pow(1024, 3)))));
		}
		return disk;
	}
}
