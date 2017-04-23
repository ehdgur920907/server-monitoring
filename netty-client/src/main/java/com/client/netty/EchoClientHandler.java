package com.client.netty;

import org.json.simple.JSONObject;

import com.information.get.GetDisk;
import com.information.get.GetHostNameAndIpAddress;
import com.information.get.GetMemory;
import com.information.model.Disk;
import com.information.model.HostNameAndIpAddress;
import com.information.model.Memory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive");
		GetHostNameAndIpAddress getHostNameAndIpAddress = new GetHostNameAndIpAddress();
		GetMemory getMemory = new GetMemory();
		GetDisk getDisk = new GetDisk();

		HostNameAndIpAddress hostNameAndIpAddress = getHostNameAndIpAddress.execute();
		Disk disk = getDisk.execute();
		Memory memory = getMemory.execute();

		JSONObject hostNameAndIpAddressJsonObject = new JSONObject();
		JSONObject memoryJsonObject = new JSONObject();
		JSONObject diskJsonObject = new JSONObject();
		JSONObject totalJsonObject = new JSONObject();

		hostNameAndIpAddressJsonObject.put("hostName", hostNameAndIpAddress.getHostName());
		hostNameAndIpAddressJsonObject.put("ipAddress", hostNameAndIpAddress.getIpAddress());

		memoryJsonObject.put("totalMemory", memory.getTotalMemory());
		memoryJsonObject.put("usedMemory", memory.getUsedMemory());
		memoryJsonObject.put("freeMemory", memory.getFreeMemory());

		diskJsonObject.put("totalDisk", disk.getTotalDisk());
		diskJsonObject.put("usedDisk", disk.getUsedDisk());
		diskJsonObject.put("freeDisk", disk.getFreeDisk());

		totalJsonObject.put("memory", memoryJsonObject);
		totalJsonObject.put("disk", diskJsonObject);
		totalJsonObject.put("hostNameAndIpAddress", hostNameAndIpAddressJsonObject);

		System.out.println(totalJsonObject);

		ByteBuf messageBuffer = Unpooled.buffer();
		messageBuffer.writeBytes(totalJsonObject.toJSONString().getBytes());

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
