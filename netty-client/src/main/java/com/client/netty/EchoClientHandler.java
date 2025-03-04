package com.client.netty;

import org.json.simple.JSONObject;

import com.information.get.GetBasicInformation;
import com.information.get.GetServerInformation;
import com.information.model.BasicInformation;
import com.information.model.ServerInformation;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	@SuppressWarnings("unchecked")
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		GetBasicInformation getBasicInforamtion = new GetBasicInformation();
		GetServerInformation getServerInformation = new GetServerInformation();

		BasicInformation basicInformation = getBasicInforamtion.execute();
		ServerInformation serverInformation = getServerInformation.execute();

		JSONObject serverInformationJsonObject = new JSONObject();
		JSONObject basicInformationJsonObject = new JSONObject();
		JSONObject totalJsonObject = new JSONObject();
		
		serverInformationJsonObject.put("osName", serverInformation.getOsName());
		serverInformationJsonObject.put("hostName", serverInformation.getHostName());
		serverInformationJsonObject.put("ipAddress", serverInformation.getIpAddress());
		
		if (Double.parseDouble(basicInformation.getFreeMemory()) < 2.50 && Double.parseDouble(basicInformation.getFreeMemory()) > 1.50) {
			serverInformationJsonObject.put("status", "warning");
		} else if (Double.parseDouble(basicInformation.getFreeMemory()) < 1.50) {
			serverInformationJsonObject.put("status", "danger");
		} else if (Double.parseDouble(basicInformation.getFreeDisk()) < 50.00 && Double.parseDouble(basicInformation.getFreeDisk()) > 30.00) {
			serverInformationJsonObject.put("status", "warning");
		} else if (Double.parseDouble(basicInformation.getFreeDisk()) < 30.00) {
			serverInformationJsonObject.put("status", "danger");
		} else if (Double.parseDouble(basicInformation.getIdleCpu().replace("%", "")) < 20.00 && Double.parseDouble(basicInformation.getIdleCpu().replace("%", "")) > 5.00) {
			serverInformationJsonObject.put("status", "warning");
		} else if (Double.parseDouble(basicInformation.getIdleCpu().replace("%", "")) < 5.00) {
			serverInformationJsonObject.put("status", "danger");
		} else {
			serverInformationJsonObject.put("status", "normal");
		}

		basicInformationJsonObject.put("totalMemory", basicInformation.getTotalMemory());
		basicInformationJsonObject.put("usedMemory", basicInformation.getUsedMemory());
		basicInformationJsonObject.put("freeMemory", basicInformation.getFreeMemory());

		basicInformationJsonObject.put("totalDisk", basicInformation.getTotalDisk());
		basicInformationJsonObject.put("usedDisk", basicInformation.getUsedDisk());
		basicInformationJsonObject.put("freeDisk", basicInformation.getFreeDisk());

		basicInformationJsonObject.put("totalCpu", basicInformation.getTotalCpu());
		basicInformationJsonObject.put("userCpu", basicInformation.getUserCpu());
		basicInformationJsonObject.put("systemCpu", basicInformation.getSystemCpu());
		basicInformationJsonObject.put("idleCpu", basicInformation.getIdleCpu());

		totalJsonObject.put("basicInformation", basicInformationJsonObject);
		totalJsonObject.put("serverInformation", serverInformationJsonObject);
		
		ByteBuf messageBuffer = Unpooled.buffer();
		messageBuffer.writeBytes(totalJsonObject.toJSONString().getBytes());
		
		System.out.println("netty client sended to netty server successfully.");
		System.out.println("status: " + serverInformationJsonObject.get("status"));
		ctx.writeAndFlush(messageBuffer);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
