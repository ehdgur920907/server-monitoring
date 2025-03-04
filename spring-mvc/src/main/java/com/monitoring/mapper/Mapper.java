package com.monitoring.mapper;

import java.util.ArrayList;

import com.monitoring.dto.AdminInformationDto;
import com.monitoring.dto.BasicInformationDto;
import com.monitoring.dto.ServerInformationDto;
import com.monitoring.dto.UsedInformationDto;

public interface Mapper {
	public ArrayList<ServerInformationDto> selectServerInformationList();
	public ArrayList<BasicInformationDto> selectBasicInformation(String id);
	public ArrayList<UsedInformationDto> selectUsedInformation(String id);
	public ServerInformationDto selectServerInformation(String id);
	public AdminInformationDto selectAdminInformation(String id);
}
